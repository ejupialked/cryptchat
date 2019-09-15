import javax.swing.*;
import java.net.InetAddress;

public class ServerApplication {

    public static void main(String[] argv) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        try {
            String hostName = InetAddress.getLocalHost().getHostName();

            System.out.println("HostName = " + hostName);

            System.out.println("HostAddressLocal = " +

                    InetAddress.getLocalHost().getHostAddress());
            InetAddress[] inetAddresses = InetAddress.getAllByName(hostName);
            for (InetAddress inetAddress : inetAddresses) {
                System.out.println("hostAddress = " + inetAddress.getHostAddress());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Server server = new Server();
        SwingUtilities.invokeLater(() -> new ServerUI(server));
    }
}