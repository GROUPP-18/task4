 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private PanelManager panelManager;

    public MainFrame() {
        setTitle("Swing Application with Menu and Panels");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelManager = new PanelManager();


        add(panelManager);


        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");

        JMenuItem homeItem = new JMenuItem("Home");
        JMenuItem aboutItem = new JMenuItem("About");
        JMenuItem exitItem = new JMenuItem("Exit");


        homeItem.addActionListener(e -> panelManager.showPanel("HOME"));
        aboutItem.addActionListener(e -> panelManager.showPanel("ABOUT"));
        exitItem.addActionListener(e -> System.exit(0));

        menu.add(homeItem);
        menu.add(aboutItem);
        menu.addSeparator();
        menu.add(exitItem);
    }
}