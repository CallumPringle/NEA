import org.hsqldb.persist.Log;

import java.util.Objects;

public class IsUserLoggedIn {
    public static void main(String[] args) {
        String values = TextFile.ReadFile();
        assert values != null;
        String[] splitValues = values.split(",");
        if(Objects.equals(splitValues[0], "True")){
            GUi.gui(splitValues[1]);
        }
        else{
            Login_in.login();
        }
    }
}
