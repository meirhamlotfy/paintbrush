
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;


class ColorPanel extends JPanel {
    public ColorPanel(DrawingPanel drawingPanel) {
        setLayout(new BorderLayout());

        JButton colorButton = new JButton("Pick Color");
        colorButton.addActionListener(e -> {
            Color color = JColorChooser.showDialog(null, "Choose a Color", Color.BLACK);
            if (color != null) {
                drawingPanel.setSelectedColor(color);
            }
        });
        add(colorButton, BorderLayout.NORTH);
    }
}
