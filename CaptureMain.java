import java.util.*;
import java.awt.*;
import javax.swing.*;

public class CaptureMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        
        GameManager game = new GameManager();
        
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
