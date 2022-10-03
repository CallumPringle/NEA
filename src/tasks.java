public class tasks {
    public String date;
    public String day;
    public String month;
    public String year;

    public tasks(String text) {
        String[] triPoloski  = text.split("/");
        this.day = triPoloski[0];
        this.month = triPoloski[1];
        this.year = triPoloski[2];
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void printDate(){
        System.out.println(this.day + this.month + this.year);
    }
    public void splitDate(String date){
        String[] triPoloski  = this.date.split("/");
    }
}
