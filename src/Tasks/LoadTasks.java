package Tasks;
import GUI.mainGUI;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadTasks {
    public static String formatDate(String date){
        date = date.split(" ")[0].replace('-','/');
        date = date.split("/")[2] + "/" +date.split("/")[1] +"/" + date.split("/")[0];
        return date;
    }

    public static void loadCheckboxes(JPanel panel, JFrame frame, String username) throws SQLException {
        ResultSet rs = TasksDatabase.loadTasks(username);
        while((rs!=null) && (rs.next())){
            JCheckBox checkbox = new JCheckBox();
            String formattedDate = formatDate(rs.getString(2));
            String task = rs.getString(1)+" date: "+ formattedDate;
            mainGUI.curTasks.put(formattedDate, rs.getString(1));
            checkbox.setText(task);
            panel.add(checkbox);
            CheckCheckbox.checkCheckbox(checkbox, frame);
        }
    }
}
