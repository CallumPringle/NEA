import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class Login_in {
    public static void login() {
        JFrame frame = new JFrame("Login");
        frame.setSize(600,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextField tf = new JTextField(20);
        tf.setBounds(30,30,100,30);
        frame.add(tf);
        frame.setLayout(null);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((size.width/2-300),(size.height/2-150));
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        login();
    }
}
