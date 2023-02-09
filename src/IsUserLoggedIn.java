public class IsUserLoggedIn {
    public static void main(String[] args) {
        if (Login_in.loggedIn){
            GUi.gui(Login_in.username);
        }
        else{
            Login_in.login();
        }
    }
}
