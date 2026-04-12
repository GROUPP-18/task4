import javax.swing.*;
import java.awt.*;

public class PanelManager extends JPanel {
    private CardLayout cardLayout;

    public PanelManager() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        JPanel homePanel = new JPanel();
        homePanel.setBackground(Color.CYAN);
        homePanel.add(new JLabel("Welcome to Home Panel"));

        JPanel aboutPanel = new JPanel();
        aboutPanel.setBackground(Color.LIGHT_GRAY);
        aboutPanel.add(new JLabel("About This Application"));

        add(homePanel, "HOME");
        add(aboutPanel, "ABOUT");

        cardLayout.show(this, "HOME");
    }

    public void showPanel(String name) {
        cardLayout.show(this, name);
    }
}