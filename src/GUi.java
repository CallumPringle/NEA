import com.sun.org.apache.bcel.internal.generic.JsrInstruction;
import javafx.stage.Screen;

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

public class GUi {
    public static void main(String[] args) {
        //Creating the Frame
        JFrame frame = new JFrame("P.I.S.S");
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setMinimumSize(new Dimension(800,800));

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
        JMenuItem epicsex = new JMenuItem("cope");
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m2.add(epicsex);
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
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);



        //to do list
        JPanel panel2 = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel2, BoxLayout.Y_AXIS);
        panel2.setLayout(boxlayout);
        JLabel title = new JLabel("      Current Tasks:       ");
        title.setForeground(Color.GREEN);
        title.setFont(new Font("Serif", Font.BOLD, frame.getWidth()/50));
        panel2.add(title);
        panel2.setBackground(Color.blue);
        panel2.setSize(frame.getWidth()/4, frame.getHeight());
        panel2.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.MAGENTA,2,true),"TO DO LIST"));



        //action listeners!!!!
        send.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                System.out.println( keyEvent.getKeyChar());
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
        epicsex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(frame, "cope and seethe");
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tf.setText("");
            }
        });
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JCheckBox x = new JCheckBox(tf.getText());
                panel2.add(x);
                panel2.add(Box.createVerticalGlue());
                tf.setText("");
                frame.setVisible(false);
                frame.setVisible(true);
            }
        });



        //Adding Components to the frame
        frame.getContentPane().add(BorderLayout.WEST, mb2);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.EAST, panel2);
        frame.setVisible(true);
        while(true){
                title.setFont(new Font("Serif", Font.BOLD, frame.getWidth()/50));
            while(true){
                if(frame.getWidth()/30<30){
                    title.setFont(new Font("Serif", Font.BOLD, frame.getWidth()/50));
                }
                else{break;}
            }

        }


        }
    }

