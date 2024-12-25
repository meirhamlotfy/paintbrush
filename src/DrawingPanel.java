
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JPanel;

class DrawingPanel extends JPanel {
    private String selectedTool = "Pencil";
    private Color selectedColor = Color.BLACK;
    private boolean isDotted = false;
    private boolean isFilled = false;
    private final ArrayList<Shape> shapes = new ArrayList<>();
    private final ArrayList<Shape> redoShapes = new ArrayList<>();
    private int startX, startY, endX, endY;

    public DrawingPanel() {
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                createShape();
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedTool.equals("Pencil") || selectedTool.equals("Eraser")) {
                    endX = e.getX();
                    endY = e.getY();
                    createShape();
                    startX = endX;
                    startY = endY;
                    repaint();
                }
            }
        });
    }

    private void createShape() {
        Shape shape = null;
        switch (selectedTool) {
            case "Line":
                shape = new Line2D.Float(startX, startY, endX, endY);
                break;
            case "Oval":
                shape = new Ellipse2D.Float(Math.min(startX, endX), Math.min(startY, endY),
                        Math.abs(endX - startX), Math.abs(endY - startY));
                break;
            case "Rectangle":
                shape = new Rectangle(Math.min(startX, endX), Math.min(startY, endY),
                        Math.abs(endX - startX), Math.abs(endY - startY));
                break;
            case "Triangle":
                int[] xPoints = {startX, endX, (startX + endX) / 2};
                int[] yPoints = {startY, startY, endY};
                shape = new Polygon(xPoints, yPoints, 3);
                break;
            case "Pencil":
                shape = new Line2D.Float(startX, startY, endX, endY);
                break;
            case "Eraser":
                shape = new Line2D.Float(startX, startY, endX, endY);
                selectedColor = Color.WHITE; // تعيين اللون الأبيض للممحاة
                break;
        }

        if (shape != null) {
            shapes.add(shape);
            redoShapes.clear(); // إفراغ قائمة الإعادة عند إضافة شكل جديد
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Shape shape : shapes) {
            if (selectedTool.equals("Eraser")) {
                g2.setColor(Color.WHITE); // تعيين اللون الأبيض للممحاة
            } else {
                g2.setColor(selectedColor);
            }

            if (isDotted) {
                float[] dash = {5f, 5f};
                g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1f, dash, 0f));
            } else {
                g2.setStroke(new BasicStroke(2));
            }

            if (isFilled && !(shape instanceof Line2D)) {
                g2.fill(shape);
            } else {
                g2.draw(shape);
            }
        }
    }

    // Setters for tool configurations
    public void setSelectedTool(String tool) {
        selectedTool = tool;
    }

    public void setSelectedColor(Color color) {
        selectedColor = color;
    }

    public void setDotted(boolean dotted) {
        isDotted = dotted;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public void undo() {
        if (!shapes.isEmpty()) {
            redoShapes.add(shapes.remove(shapes.size() - 1)); // نقل آخر عنصر إلى قائمة الإعادة
            repaint();
        }
    }

    public void redo() {
        if (!redoShapes.isEmpty()) {
            shapes.add(redoShapes.remove(redoShapes.size() - 1)); // استرجاع العنصر الأخير من قائمة الإعادة
            repaint();
        }
    }

    public void clear() {
        shapes.clear();
        redoShapes.clear();
        repaint();
    }
}
