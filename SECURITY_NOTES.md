# Security Notes

I built CryptChat in high school for a project on Computer Security, while I was learning
programming. It demonstrates a Diffie-Hellman + AES handshake over TCP sockets and it
*works*, but it is **not secure by modern standards**.

This is a self-audit of my own old code: what's wrong, how bad it is, and how I'd fix it
today. The original code is kept unchanged on purpose — the point is to show the gap
between "it runs" and "it's secure", not to hide it.

## Summary

| # | Finding | Severity |
|---|---------|----------|
| 1 | Key exchange is unauthenticated (man-in-the-middle) | High |
| 2 | AES runs in ECB mode, with no integrity check | High |
| 3 | Private and session keys are written to the logs | High |
| 4 | No transport-layer security (plain TCP, no TLS) | Medium |
| 5 | AES key is taken raw from the DH secret (no KDF) | Medium |
| 6 | Password code path uses the raw string bytes as the key | Low |
| 7 | Minor smells (`ObjectInputStream`, Xerces Base64) | Info |

## Findings

### 1. Unauthenticated key exchange — man-in-the-middle · High
*`dh/KeyExchange.java` (client and server), `server/ClientHandler.java`, `ClientApplication.java`*

Public keys are exchanged over a plain socket with no signatures or fingerprint
verification, so neither side can prove the key it received belongs to the other side.
This is textbook Diffie-Hellman: safe against a passive eavesdropper, but an active
attacker on the network path can hand each side its *own* key, sit in the middle, and
read and rewrite every message transparently.

**Impact:** the entire "secure chat" guarantee collapses against an active attacker.
**Fix:** authenticate the exchange — sign the public keys, or verify an out-of-band
fingerprint — or run the whole session inside TLS.

### 2. AES in ECB mode, with no integrity check · High
*`utils/CryptChatUtils.java` (client and server) — `Cipher.getInstance("AES")`*

With no mode specified, the JCA defaults to `AES/ECB/PKCS5Padding`. ECB is not
semantically secure: identical plaintext blocks encrypt to identical ciphertext blocks,
so structure and repetition in messages leak. There is also no IV and no authentication
(no GCM, no MAC), so ciphertext can be tampered with undetectably.

**Impact:** message content patterns leak, and tampering goes unnoticed.
**Fix:** use `AES/GCM/NoPadding` with a fresh random 12-byte nonce per message, which
provides both confidentiality and integrity.

### 3. Private and session keys are written to the logs · High
*`dh/KeyExchange.java` (server), `utils/CryptChatUtils.java` — `encrypt` / `decrypt`*

Private keys and the derived AES key are printed to stdout/stderr in several places.
One log line labelled as the public key actually prints the *private* key, and both
`encrypt` and `decrypt` print the AES key on every call.

**Impact:** anyone who can read the logs recovers the keys and can decrypt the traffic —
the encryption is only as private as the console output.
**Fix:** never log key material; remove the debug prints entirely.

### 4. No transport-layer security · Medium
*`server/Server.java`, `server/ClientHandler.java`, `ClientApplication.java`*

Communication runs over raw `Socket` / `ServerSocket` with no TLS, so the whole handshake
and all framing are visible on the wire. On its own this only exposes an eavesdropper to
ciphertext, but combined with finding #1 it is what makes the man-in-the-middle practical.

**Impact:** no confidentiality or authenticity at the transport layer; enables #1.
**Fix:** wrap the connection in TLS (`SSLSocket`) and let the application layer sit on top.

### 5. AES key taken raw from the DH secret · Medium
*`utils/CryptChatUtils.java` — `generateAESKey(byte[])`*

The AES key is simply the first 16 bytes of the raw Diffie-Hellman shared secret
(`new SecretKeySpec(secret, 0, 16, "AES")`). Raw DH output is not uniformly distributed
and should never be used directly as a symmetric key.

**Impact:** the key has less effective entropy than a proper 128-bit key.
**Fix:** run the shared secret through a KDF (HKDF, or at minimum SHA-256) and take the
key from that.

### 6. Password code path uses the raw string bytes as the key · Low
*`utils/CryptChatUtils.java` (client) — `encrypt(String, String)` / `decrypt(String, String)`*

These overloads feed `aesKey.getBytes()` straight into `SecretKeySpec` with no key
derivation and no length check, so the key is whatever bytes the passphrase happens to
encode.

**Impact:** weak, unpredictable keys; wrong-length input fails at runtime.
**Fix:** derive a fixed-length key from the passphrase with a password KDF (PBKDF2 or
Argon2) and a salt.

### 7. Minor smells · Info
*`server/ClientHandler.java`, `utils/CryptChatUtils.java` (server)*

`ObjectInputStream` is used but only ever reads `readUTF`/`writeUTF`, so there is no Java
deserialization-gadget exposure in practice — but it is an unnecessary risk surface, and
a `DataInputStream` would be the right tool. The server also depends on Xerces' internal
`Base64` class, an odd choice though not a vulnerability.

## What this project got right for a first attempt

Real Diffie-Hellman key agreement with 2048-bit parameters, a working AES layer, a clean
command-based client/server protocol, and a complete Android + Java socket implementation
end to end. The cryptographic *ideas* are sound; every failure above is in the details
that separate "it works" from "it's secure" — which is exactly the lesson.
