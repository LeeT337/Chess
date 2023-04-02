import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

public class Frame extends JFrame implements MouseListener, MouseMotionListener{

    JLabel label;
    GamePanel panel;

    Frame() throws IOException {
            panel = new GamePanel();
            this.add(panel);

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setTitle("Chess");
            this.setSize(815, 825);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);

            this.setLocationRelativeTo(null);
            this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

        panel.mousePressed(e.getY(), e.getX());

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        panel.mouseReleased(e.getY(), e.getX());

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void mouseDragged(MouseEvent e) {
        panel.mouseDragged(e.getY(), e.getX());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
