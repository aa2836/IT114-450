package Project.client.views;


import javax.swing.*;
import java.awt.*;
import Project.server.ServerThread;

public class UserListRenderer extends DefaultListCellRenderer {
    private ServerThread lastSender;

    public void setLastSender(ServerThread sender) {
        lastSender = sender;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        if (value instanceof ServerThread) {
            ServerThread user = (ServerThread) value;
            String username = user.getClientName();
            boolean isMuted = user.isMuted();

            JLabel label = (JLabel) super.getListCellRendererComponent(list, username, index, isSelected, cellHasFocus);

            if (isMuted) {
                label.setForeground(Color.RED);
            } else {
                label.setForeground(Color.BLACK);
            }

            if (user == lastSender) {
                label.setFont(label.getFont().deriveFont(Font.BOLD));
            } else {
                label.setFont(label.getFont().deriveFont(Font.PLAIN));
            }

            return label;
        }

        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }
}
