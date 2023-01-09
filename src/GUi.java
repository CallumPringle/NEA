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
    public static String formatDate(String date){
        date = date.split(" ")[0].replace('-','/');
        date = date.split("/")[2] + "/" +date.split("/")[1] +"/" + date.split("/")[0];
        return date;
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
    public static Boolean checkIfTimeIsValid(String time){
        String[] splitTime = time.split(":");
        return Integer.parseInt(splitTime[0]) <= 23 && Integer.parseInt(splitTime[1]) <= 59 && Integer.parseInt(splitTime[0]) >= 0 && Integer.parseInt(splitTime[1]) >= 0;
    }
    public static void loadCheckboxes(JPanel panel, JFrame frame) throws SQLException {
        ResultSet rs = TasksDatabase.loadTasks();
        while((rs!=null) && (rs.next())){
            JCheckBox x = new JCheckBox();
            String formattedDate = formatDate(rs.getString(2));
            x.setText(rs.getString(1)+" date: "+ formattedDate);
            panel.add(x);
            checkCheckbox(x, frame);
        }
    }


    public static void gui(String username) {
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


        //to do list
        JPanel panel2 = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel2, BoxLayout.Y_AXIS);
        panel2.setLayout(boxlayout);
        JLabel title = new JLabel("      Current Tasks:       ");
        panel2.add(title);
        panel2.setBackground(Color.decode("#00ffff"));
        panel2.setSize(frame.getWidth()/4, frame.getHeight());
        panel2.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.MAGENTA,2,true),"TO DO LIST"));
        try{loadCheckboxes(panel2,frame);}catch(Exception e){
            System.out.println(e);
        }

        //action listeners!!!!
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
        sendTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(checkIfTimeIsValid(tf.getText())){
                    text = text + " time: " + tf.getText();
                    JCheckBox x = new JCheckBox();
                    x.setText(text);
                    panel2.add(x);
                    panel2.add(Box.createVerticalGlue());
                    checkCheckbox(x, frame);
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
        sendDate.addActionListener(new ActionListener() {
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
        });

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
        tf.addActionListener(al);
        sendTask.addActionListener(al);





        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.WEST, mb2);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.EAST, panel2);
        frame.setVisible(true);

        }

    private static void checkCheckbox(JCheckBox x, JFrame frame) {
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
                            TasksDatabase.deleteTask(x.getText().split(" date:")[0]);
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
}

