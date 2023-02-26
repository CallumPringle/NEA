package Login;

import GUI.mainGUI;
import Tasks.TasksDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

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
        JPasswordField pWordtf = new JPasswordField(20);
        JLabel password = new JLabel("Password:");
        pWordtf.setBounds(70,50,300,20);
        password.setBounds(0,pWordtf.getY()-5,100,30);
        frame.add(password);
        frame.add(pWordtf);

        JButton registerB = new JButton("Register");
        registerB.setBounds((frame.getWidth()/2), 75, 100,30);
        frame.add(registerB);
        registerB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                register.rigster();
            }
        });

        //submit
        JButton button = new JButton("Submit");
        button.setBounds((frame.getWidth()/2)-100, 75, 75,30);
        frame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                try{
                    ResultSet rs = TasksDatabase.retrieveUsername();
                while((rs!=null) && (rs.next())) {
                            if(Objects.equals(rs.getString(1), tf.getText())){
                                ResultSet pw = TasksDatabase.retrievePassword(tf.getText());
                                pw.next();
                                if(Objects.equals(pw.getString(1), register.createHash(pWordtf.getText()))){
                                    frame.setVisible(false);
                                    TextFile.WriteToFile("True",tf.getText());
                                    mainGUI.gui(tf.getText());
                                }
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                }
            }
        });


        frame.setLayout(null);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((size.width/2-(frame.getWidth()/2)),(size.height/2-(frame.getHeight()/2)));
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        login();
    }

}
