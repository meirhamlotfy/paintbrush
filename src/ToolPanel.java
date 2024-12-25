
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

class ToolPanel extends JPanel {
    public ToolPanel(DrawingPanel drawingPanel) {
        setLayout(new FlowLayout());

      
        String[] tools = {"Pencil", "Line", "Oval", "Rectangle", "Triangle", "Eraser"};
        for (String tool : tools) {
            JButton button = new JButton(tool);
            button.addActionListener(e -> drawingPanel.setSelectedTool(tool));
            add(button);
        }

      
        JCheckBox fillCheckbox = new JCheckBox("Fill");
        fillCheckbox.addActionListener(e -> drawingPanel.setFilled(fillCheckbox.isSelected()));
        JCheckBox dottedCheckbox = new JCheckBox("Dotted");
        dottedCheckbox.addActionListener(e -> drawingPanel.setDotted(dottedCheckbox.isSelected()));
        add(fillCheckbox);
        add(dottedCheckbox);

     
        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> drawingPanel.undo());
        add(undoButton);

    
        JButton redoButton = new JButton("Redo");
        redoButton.addActionListener(e -> drawingPanel.redo());
        add(redoButton);

   
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> drawingPanel.clear());
        add(clearButton);
    }
}
