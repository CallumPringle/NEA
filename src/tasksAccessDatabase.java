import java.sql.*;
public class tasksAccessDatabase {
    public static void tasksIntoDatabase(String task){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://X:\\computer science\\java projects\\NEA\\src\\Tasks.accdb");//Establishing Connection
            PreparedStatement pstmt = conn.prepareStatement("insert into tasksTable (ID,test) values(null,'"+task+"')");
            System.out.println("Connected Successfully");

            //String insrtTask = "insert into tasksTable (ID,test) values(null,task);";
            //System.out.println("The SQL statement is: " + insrtTask + "\n"); // Echo For debugging
            pstmt.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        tasksIntoDatabase("semen");





        /*try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn= DriverManager.getConnection("jdbc:ucanaccess://X:\\computer science\\java projects\\NEA\\src\\Tasks.accdb");//Establishing Connection
            Statement stmt = conn.createStatement();
            System.out.println("Connected Successfully");
            String strSelect = "select test from tasksTable";
            System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
            ResultSet rset = stmt.executeQuery(strSelect);
            System.out.println("The records selected are:");
            int rowCount = 0;
            while(rset.next()) {   // Repeatedly process each row
                String test = rset.getString("test");  // retrieve a 'String'-cell in the row
                System.out.println(test);
                ++rowCount;
            }
            System.out.println("Total number of records = " + rowCount);
        }catch(Exception e){
            System.out.println("Error in connection" + e);

        }*/
}}
