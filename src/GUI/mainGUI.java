package GUI;

import Login.Login_in;
import Login.TextFile;
import Tasks.*;
import javafx.util.Pair;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class mainGUI {
    private static String taskText;
    public static HashMap<String, String> curTasks = new HashMap<String,String>();
    public static void main(String[] args) {gui("test");}
    public static boolean isDatePast(String date) {
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inputDate = LocalDate.parse(date, dtf);
        return inputDate.isBefore(localDate);
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
    public static JFrame createFrame(String username){
        JFrame frame = new JFrame("Logged in as " + username);
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setResizable(false);
        return frame;
    }
    public static void addCalendar(JFrame frame){
        Calendar.createCalendar(frame);
    }
    public static JMenuBar topMenu(JFrame frame, JPanel toDoBox){
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Account");
        m1.addSeparator();
        mb.add(m1);
        JMenuItem logout = new JMenuItem("Log Out");
        JMenuItem sort = new JMenuItem("Sort tasks");
        m1.add(logout);
        m1.add(sort);
        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortTasks(toDoBox,frame);
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                TextFile.WriteToFile("False","");
                Login_in.login();
            }
        });
        return mb;
    }
    public static void sortTasks(JPanel toDoBox, JFrame frame){
        toDoBox.removeAll();
        String[] taskDates = curTasks.keySet().toArray(new String[0]);
        sortDate.mergeSort(taskDates, curTasks.size());
        for (int i = 0; i < curTasks.size(); i++) {
            JCheckBox checkBox = new JCheckBox(curTasks.get(taskDates[i]) + " date: " + taskDates[i]);
            toDoBox.add(checkBox);
            CheckCheckbox.checkCheckbox(checkBox, frame);
        }
        frame.repaint();
    }
    public static JPanel bottomPanel(JFrame frame,JPanel panel2, String username, JPanel toDoBox){
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("enter task");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton sendTask = new JButton("Send");
        JButton sendDate = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(sendTask);
        panel.add(sendDate);
        panel.add(reset);
        tf.setVisible(false);
        reset.setVisible(false);
        sendTask.setVisible(false);
        sendDate.setVisible(false);
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
        ActionListener al2 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (checkIfDateIsValid(tf.getText())) {
                    if(!isDatePast(tf.getText())){
                        tasks task = new tasks(taskText, tf.getText());
                        curTasks.put(tf.getText(),taskText);
                        JCheckBox checkbox = new JCheckBox(taskText + " date: " + tf.getText());
                        CheckCheckbox.checkCheckbox(checkbox,frame);
                        toDoBox.add(checkbox);
                        sortTasks(toDoBox, frame);
                        TasksDatabase.tasksIntoDatabase(taskText, task, username);
                        tf.setText("");
                        label.setText("enter task");
                        sendTask.setVisible(true);
                        sendDate.setVisible(false);
                    }else{
                            JOptionPane.showMessageDialog(frame, "Date is in the past");
                    }
                }else{JOptionPane.showMessageDialog(frame, "Date is not valid");}
                }

        };
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                taskText = tf.getText();
                tf.setText("");
                label.setText("enter date [dd/mm/yyyy}");
                sendTask.setVisible(false);
                sendDate.setVisible(true);

            }
        };
        sendDate.addActionListener(al2);
        sendTask.addActionListener(al);
        return panel;
    }
    public static Pair<JPanel, JPanel> toDo(JFrame frame, String username){
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setPreferredSize(new Dimension(frame.getWidth()/4,501));
        JLabel title = new JLabel("      Current Tasks:       ");
        panel2.add(title);
        panel2.setBackground(Color.decode("#00ffff"));
        panel2.setSize(frame.getWidth()/4, frame.getHeight());
        panel2.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.MAGENTA,2,true),"TO DO LIST"));
        JPanel todoBox = new JPanel();
        todoBox.setPreferredSize(new Dimension(frame.getWidth(),850));
        todoBox.setLayout(new BoxLayout(todoBox, BoxLayout.PAGE_AXIS));
        JScrollPane scroller = new JScrollPane(todoBox, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setPreferredSize(new Dimension(frame.getWidth()/4 - 50,900));
        panel2.add(scroller);
        todoBox.setVisible(true);
        scroller.setVisible(true);
        try{
            LoadTasks.loadCheckboxes(todoBox,frame,username);}catch(Exception e){
            System.out.println(e);
        }
        return new Pair<JPanel, JPanel>(panel2, todoBox);
    }

    public static void gui(String username) {
        //Creating the Frame
        JFrame frame = createFrame(username);
        //adding the calendar
        addCalendar(frame);
        //to do list
        Pair<JPanel, JPanel> panel2_toDoBox = toDo(frame, username);
        //Creating the panel at bottom and adding components
        //top menu
        JMenuBar mb = topMenu(frame, panel2_toDoBox.getValue());
        JPanel panel = bottomPanel(frame, panel2_toDoBox.getKey(), username,panel2_toDoBox.getValue());
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.EAST, panel2_toDoBox.getKey());
        frame.setVisible(true);

        }

}

