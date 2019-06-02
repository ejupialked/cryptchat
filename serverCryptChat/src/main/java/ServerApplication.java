import javax.swing.*;

public class ServerApplication {

    public static void main(String[] argv) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ReflectiveOperationException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Server server = new Server();
        SwingUtilities.invokeLater(() -> new ServerUI(server));
    }
}