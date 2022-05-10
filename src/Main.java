import java.util.Calendar;
public class Main {

    public static void main(String[] args) {
// creating Calendar object
        Calendar calendar = Calendar.getInstance();

        // Demonstrate Calendar's get()method
        System.out.println("Current Calendar's Year: " + calendar.get(Calendar.YEAR));
        System.out.println("Current Calendar's Month: " + calendar.get(Calendar.MONTH));
        System.out.println("Current Calendar's Day: " + calendar.get(Calendar.DATE));
        System.out.println("Current HOUR: " + calendar.get(Calendar.HOUR));
        System.out.println("Current MINUTE: " + calendar.get(Calendar.MINUTE));
        System.out.println("Current SECOND: " + calendar.get(Calendar.SECOND));    }
}
