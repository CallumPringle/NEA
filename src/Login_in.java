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
        frame.setSize(400,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //username
        JTextField tf = new JTextField(20);
        JLabel username = new JLabel("Username:");
        tf.setBounds(70,25,300,20);
        username.setBounds(0,tf.getHeight(),100,30);
        frame.add(tf);
        frame.add(username);

        //password
        JTextField pWordtf = new JTextField(20);
        JLabel password = new JLabel("Password:");
        pWordtf.setBounds(70,50,300,20);
        password.setBounds(0,pWordtf.getY()-5,100,30);
        System.out.println(pWordtf.getHeight() + " user" + tf.getHeight());
        frame.add(password);
        frame.add(pWordtf);


        frame.setLayout(null);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((size.width/2-(frame.getWidth()/2)),(size.height/2-(frame.getHeight()/2)));
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        login();
    }
}
