import com.sun.org.apache.bcel.internal.generic.JsrInstruction;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class GUi {
    private static String text;

    public static void main(String[] args) {
        gui();
    }
    private static boolean checkIfDateIsValid(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try {
            format.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    public static void gui() {
        //Creating the Frame
        JFrame frame = new JFrame("P.I.S.S");
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);


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
        JLabel label = new JLabel("enter task");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton send2 = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(send2);
        panel.add(reset);
        tf.setVisible(false);
        reset.setVisible(false);
        send.setVisible(false);
        send2.setVisible(false);
        label.setVisible(false);
        JButton newToDo = new JButton("new task");
        panel.add(newToDo);


        //to do list
        JPanel panel2 = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel2, BoxLayout.Y_AXIS);
        panel2.setLayout(boxlayout);
        JLabel title = new JLabel("      Current Tasks:       ");
        panel2.add(title);
        panel2.setBackground(Color.decode("#00ffff"));
        panel2.setSize(frame.getWidth()/4, frame.getHeight());
        panel2.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.MAGENTA,2,true),"TO DO LIST"));


        //action listeners!!!!
        newToDo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tf.setVisible(true);
                reset.setVisible(true);
                send.setVisible(true);
                label.setVisible(true);
                newToDo.setVisible(false);
            }
        });
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
        send2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if ((checkIfDateIsValid(tf.getText()))){
                    text = text + " date: " + tf.getText();
                    tasks task = new tasks(tf.getText(), tf.getText());
                    task.printDate();
                    System.out.println(task.getDate());
                    JCheckBox x = new JCheckBox();
                    x.setText(text);
                    panel2.add(x);
                    panel2.add(Box.createVerticalGlue());
                    tf.setText("");
                    label.setText("enter task");
                    send.setVisible(true);
                    send2.setVisible(false);
                    x.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent itemEvent) {
                            if(itemEvent.getStateChange()==1){
                                JDialog d = new JDialog(frame, "Confirm", true);
                                JButton yer = new JButton("Confirm");
                                JButton naw = new JButton("naw");
                                yer.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        x.setVisible(false);
                                        d.setVisible(false);
                                    }
                                });
                                naw.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent actionEvent) {
                                    d.setVisible(false);
                                    }
                                });
                                d.setLayout(new FlowLayout());
                                d.setBounds((frame.getWidth()/2)-150,(frame.getHeight()/2)-50,300,100);
                                d.add(new Label("Are you sure this task is fully complete?"));
                                d.add(yer);
                                d.add(naw);
                                d.setVisible(true);
                            }
                        }
                    });
                }
                else{
                    JOptionPane.showMessageDialog(frame, "not correct format");
                }
            }
        });
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                text = tf.getText();
                panel2.add(Box.createVerticalGlue());
                tf.setText("");
                label.setText("enter date [dd/mm/yyyy}");
                send.setVisible(false);
                send2.setVisible(true);

            }
        });




        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.WEST, mb2);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.EAST, panel2);
        frame.setVisible(true);

        }
    }

