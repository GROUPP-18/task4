import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.awt.*;

class MainFrameTest {

    private MainFrame frame;

    @BeforeEach
    void setUp() {
        frame = new MainFrame();
    }

    @AfterEach
    void tearDown() {
        if (frame != null) {
            frame.dispose();
        }
    }

    @Test
    void testFrameInitialization() {
        assertEquals("Swing Application with Menu and Panels", frame.getTitle());
        assertEquals(500, frame.getWidth());
        assertEquals(400, frame.getHeight());
    }

    @Test
    void testMenuBarExists() {
        JMenuBar menuBar = frame.getJMenuBar();
        assertNotNull(menuBar);

        JMenu menu = menuBar.getMenu(0);
        assertEquals("Menu", menu.getText());
        // Waxaan ka dhignay 4 si uu u tiriye Separator-ka
        assertEquals(4, menu.getItemCount(), "Menu-gu waa inuu leeyahay 4 shay (oo uu ku jiro Separator)");
    }

    @Test
    void testPanelManagerIsAdded() {
        Component[] components = frame.getContentPane().getComponents();
        boolean found = false;
        for (Component c : components) {
            if (c instanceof PanelManager) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }
}