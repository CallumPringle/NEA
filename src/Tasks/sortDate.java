package Tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class sortDate {
    public static boolean isDateOrdered(String date1, String date2){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate formattedDate1 = LocalDate.parse(date1, dtf);
        LocalDate formattedDate2 = LocalDate.parse(date2, dtf);
        return formattedDate1.isBefore(formattedDate2);
    }
    public static void merge(String[] left_arr,String[] right_arr, String[] arr,int left_size, int right_size){

        int i=0,l=0,r = 0;
        //The while loops check the conditions for merging
        while(l<left_size && r<right_size){

            if(isDateOrdered(left_arr[l], right_arr[r])){
                arr[i++] = left_arr[l++];
            }
            else{
                arr[i++] = right_arr[r++];
            }
        }
        while(l<left_size){
            arr[i++] = left_arr[l++];
        }
        while(r<right_size){
            arr[i++] = right_arr[r++];
        }
    }

    public static void mergeSort(String [] arr, int len){
        if (len < 2){return;}

        int mid = len / 2;
        String [] left_arr = new String[mid];
        String [] right_arr = new String[len-mid];

        //Dividing array into two and copying into two separate arrays
        int k = 0;
        for(int i = 0;i<len;++i){
            if(i<mid){
                left_arr[i] = arr[i];
            }
            else{
                right_arr[k] = arr[i];
                k = k+1;
            }
        }
        // Recursively calling the function to divide the subarrays further
        mergeSort(left_arr,mid);
        mergeSort(right_arr,len-mid);
        // Calling the merge method on each subdivision
        merge(left_arr,right_arr,arr,mid,len-mid);
    }

   /* public static void main( String args[] ) {
        String [] array = {"31/12/2004","10/12/2004","01/02/2005","21/05/1998","20/01/2020", "31/12/2004"};
        mergeSort(array,array.length);
        for(int i =0; i< array.length;++i){
            System.out.print(array[i]+ " ");
        }
    }*/
}
