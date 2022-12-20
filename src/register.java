import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class register {
    public static Boolean checkDupedUsername(String username) throws SQLException {
        ResultSet rs = TasksDatabase.retrieveUsername();
        rs.next();
        return Objects.equals(username, rs.getString(1));
        }


    public static void rigster(){
    JFrame frame = new JFrame("Register");
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

    //submit
    JButton button = new JButton("Submit");
        button.setBounds((frame.getWidth()/2)-50, 75, 100,30);
        frame.add(button);
        button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try{
            if(pWordtf.getText().length() < 8){
                JOptionPane.showMessageDialog(frame, "password must be at least 8 digits long");
                pWordtf.setText("");

            }
            else if(checkDupedUsername(tf.getText())){
                JOptionPane.showMessageDialog(frame,"username already exists");
            }
            else{
                TasksDatabase.registerUser(tf.getText(),pWordtf.getText());
                frame.setVisible(false);
                Login_in.login();
            }}catch(Exception e){
                System.out.println(e);
            }
        }
    });


        frame.setLayout(null);
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((size.width/2-(frame.getWidth()/2)),(size.height/2-(frame.getHeight()/2)));
        frame.setVisible(true);
}

    public static void main(String[] args) {
        rigster();
    }
}
