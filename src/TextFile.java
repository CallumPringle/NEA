import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TextFile {
        public static void CreateFile(){
            try {
                File myObj = new File("filename.txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        public static void WriteToFile(String loggedIn, String username){
            try {
                FileWriter myWriter = new FileWriter("filename.txt");
                myWriter.write(loggedIn + "," + username);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        public static String ReadFile() {
            try {
                File myObj = new File("filename.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    return myReader.nextLine();
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            return null;
        }}


