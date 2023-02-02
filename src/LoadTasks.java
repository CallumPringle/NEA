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
            JCheckBox x = new JCheckBox();
            String formattedDate = formatDate(rs.getString(2));
            x.setText(rs.getString(1)+" date: "+ formattedDate);
            panel.add(x);
            GUi.checkCheckbox(x, frame);
        }
    }
}
