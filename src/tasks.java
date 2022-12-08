public class tasks {
    public String date;
    public int day;
    public int month;
    public int year;

    public tasks(String text,String date) {
        this.date = date;
        String[] triPoloski  = text.split("/");
        this.day = Integer.parseInt(triPoloski[0]);
        this.month = Integer.parseInt(triPoloski[1]);
        this.year = Integer.parseInt(triPoloski[2]);
    }

    public String getDate() {
        return date;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void printDate(){
        System.out.println(this.day + "/" + this.month + "/" + this.year);
    }
    public void splitDate(String date){
        String[] triPoloski  = this.date.split("/");
    }
}
