import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;


public class PaintBrushApp extends JFrame {
    public PaintBrushApp() {
        setTitle(" Paint Brush");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

    
        DrawingPanel drawingPanel = new DrawingPanel();
        ToolPanel toolPanel = new ToolPanel(drawingPanel);
        ColorPanel colorPanel = new ColorPanel(drawingPanel);

    
        add(toolPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(colorPanel, BorderLayout.WEST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaintBrushApp().setVisible(true));
    }
}

