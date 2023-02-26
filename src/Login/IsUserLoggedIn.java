package Login;

import GUI.mainGUI;

import java.util.Objects;

public class IsUserLoggedIn {
    public static void main(String[] args) {
        TextFile.CreateFile();
        String values = TextFile.ReadFile();
        assert values != null;
        String[] splitValues = values.split(",");
        if(Objects.equals(splitValues[0], "True")){
            mainGUI.gui(splitValues[1]);
        }
        else{
            Login_in.login();
        }
    }
}
