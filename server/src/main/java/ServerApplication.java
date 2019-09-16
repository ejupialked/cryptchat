import server.Server;
import ui.ServerUI;

import javax.swing.*;


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

        Server server = new Server();
        SwingUtilities.invokeLater(() -> new ServerUI(server));
    }
}