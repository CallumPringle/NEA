import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GUi {
    private static String text;

    public static void main(String[] args) {gui("test");}

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
    public static Boolean checkIfTimeIsValid(String time){
        String[] splitTime = time.split(":");
        return Integer.parseInt(splitTime[0]) <= 23 && Integer.parseInt(splitTime[1]) <= 59 && Integer.parseInt(splitTime[0]) >= 0 && Integer.parseInt(splitTime[1]) >= 0;
    }
    public static JFrame createFrame(){
        JFrame frame = new JFrame("P.I.S.S");
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        return frame;
    }
    public static JMenuBar topMenu(JFrame frame){
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        m1.addSeparator();
        mb.add(m1);
        mb.add(m2);
        JMenuItem menuItem = new JMenuItem("cope");
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m2.add(menuItem);
        m1.add(m11);
        m1.add(m22);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(frame, "cope and seethe");
            }
        });return mb;
    }
    public static JMenuBar leftMenubar(){
        JMenuBar mb2 = new JMenuBar();
        JMenu m4325 = new JMenu("sussy");
        mb2.add(m4325);
        m4325.addSeparator();
        return mb2;
    }
    public static JPanel bottomPanel(JFrame frame,JPanel panel2, String username){
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("enter task");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton sendTask = new JButton("Send");
        JButton sendDate = new JButton("Send");
        JButton sendTime = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(sendTask);
        panel.add(sendDate);
        panel.add(sendTime);
        panel.add(reset);
        tf.setVisible(false);
        reset.setVisible(false);
        sendTask.setVisible(false);
        sendDate.setVisible(false);
        sendTime.setVisible(false);
        label.setVisible(false);
        JButton newToDo = new JButton("new task");
        panel.add(newToDo);
        newToDo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tf.setVisible(true);
                reset.setVisible(true);
                sendTask.setVisible(true);
                label.setVisible(true);
                newToDo.setVisible(false);
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tf.setText("");
            }
        });
        sendTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(checkIfTimeIsValid(tf.getText())){
                    text = text + " time: " + tf.getText();
                    JCheckBox x = new JCheckBox();
                    x.setText(text);
                    panel2.add(x);
                    panel2.add(Box.createVerticalGlue());
                    CheckCheckbox.checkCheckbox(x, frame);
                    tf.setText("");
                    label.setText("enter task");
                    sendTime.setVisible(false);
                    sendTask.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(frame, "not correct format");
                }
            }
        });
        ActionListener al2 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if ((checkIfDateIsValid(tf.getText()))){
                    tasks task = new tasks(tf.getText(), tf.getText());
                    TasksDatabase.tasksIntoDatabase(text,task,username);
                    text = text + " date: " + tf.getText();
                    task.printDate();
                    System.out.println(task.getDate());
                    tf.setText("");
                    label.setText("enter time (hh:mm)");
                    sendTime.setVisible(true);
                    sendDate.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(frame, "not correct format");
                }
            }
        };
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                text = tf.getText();
                panel2.add(Box.createVerticalGlue());
                tf.setText("");
                label.setText("enter date [dd/mm/yyyy}");
                sendTask.setVisible(false);
                sendDate.setVisible(true);

            }
        };
        sendDate.addActionListener(al2);
        tf.addActionListener(al);
        sendTask.addActionListener(al);
        return panel;
    }
    public static JPanel toDo(JFrame frame,String username){
        JPanel panel2 = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel2, BoxLayout.Y_AXIS);
        panel2.setLayout(boxlayout);
        JLabel title = new JLabel("      Current Tasks:       ");
        panel2.add(title);
        panel2.setBackground(Color.decode("#00ffff"));
        panel2.setSize(frame.getWidth()/4, frame.getHeight());
        panel2.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.MAGENTA,2,true),"TO DO LIST"));
        try{LoadTasks.loadCheckboxes(panel2,frame,username);}catch(Exception e){
            System.out.println(e);
        }
        return panel2;
    }

    public static void gui(String username) {
        //Creating the Frame
        JFrame frame = createFrame();
        //top menu
        JMenuBar mb = topMenu(frame);
        //left menubar
        JMenuBar mb2 = leftMenubar();
        //to do list
        JPanel panel2 = toDo(frame, username);
        //Creating the panel at bottom and adding components
        JPanel panel = bottomPanel(frame, panel2, username);
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.WEST, mb2);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.EAST, panel2);
        frame.setVisible(true);

        }

}

