import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUi {
    public static float thing = 0;
    public static void main(String[] args) {
        //Creating the Frame
        JFrame frame = new JFrame("P.I.S.S");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        //labeltest
        /*JLabel testLabel = new JLabel("sfdgs");
        frame.getContentPane().add(BorderLayout.CENTER, testLabel);*/

        //top menu
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        m1.addSeparator();
        mb.add(m1);
        mb.add(m2);
        JMenuItem epicSex = new JMenuItem("cope");
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m2.add(epicSex);
        m1.add(m11);
        m1.add(m22);

        //menubar along left side
        JMenuBar mb2 = new JMenuBar();
        JMenu m4325 = new JMenu("sussy");
        mb2.add(m4325);
        m4325.addSeparator();

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(20); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Text Area at the Center
        //JTextArea ta = new JTextArea();


        //panel
        JPanel panel2 = new JPanel();
        panel2.setBounds(40,80,200,200);
        panel2.setBackground(Color.blue);
        JButton b1=new JButton("Button 1");
        b1.setBounds(50,100,80,30);
        b1.setBackground(Color.yellow);
        JButton b2=new JButton("Button 2");
        b2.setBounds(100,100,80,30);
        b2.setLocation(frame.getWidth()/2,10);
        b2.setBackground(Color.green);
        panel2.add(b1); panel2.add(b2);
        panel2.setLayout(null);
        frame.add(panel2);

        //action listeners!!!!
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tf.setText("");b2.setLocation(frame.getWidth()/2,10);
            }
        });
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JCheckBox x = new JCheckBox(tf.getText());
                if (thing == 0){x.setBounds(frame.getWidth()/2,frame.getHeight()/2,100,2);frame.add(x);}
                else{
                    JCheckBox y = new JCheckBox(tf.getText());
                    y.setBounds(frame.getWidth()/2,frame.getHeight()-10,100,2);
                    frame.add(y);
                }
                thing = x.getAlignmentX();
                System.out.println(thing);
                tf.setText("");
                frame.setVisible(false);
                frame.setVisible(true);
            }
        });
        /*epicSex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ta.setText("cope cope cope cope cope cope cope cope cope cope cope cope cope cope cope cope");
            }
        });*/

        //Adding Components to the frame.
        //frame.getContentPane().add(BorderLayout.EAST, panel2);
        frame.getContentPane().add(BorderLayout.WEST, mb2);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        //frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }
}
