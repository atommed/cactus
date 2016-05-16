import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 15.05.2016.
 */
public class DateHandler {
    private String textDate;
    private Date postDate;
    private Date newDate;
    private String correctDate;
    private String time;

    public DateHandler(String textDate, long seconds){
        this.textDate = textDate;
        this.postDate = new Date(seconds*1000);
        this.newDate = new Date (seconds*1000);
        this.time = "00:00";
    }
    public void Parse(){


        if (this.textDate.contains("сегодня") || this.textDate.contains("Сегодня") || this.textDate.contains("завтра") || this.textDate.contains("Завтра") || this.textDate.contains("мая") || this.textDate.contains("Мая") || this.textDate.contains("июня")){
            int pos = textDate.indexOf(":");
            if (textDate.contains(":")) time = textDate.substring(pos-2,pos+3);
            SimpleDateFormat ft =
                    new SimpleDateFormat ("yyyy-MM-dd");
            if (this.textDate.contains("сегодня") || this.textDate.contains("Сегодня")){
                correctDate = ft.format(postDate);

            }
            else if (this.textDate.contains("завтра") || this.textDate.contains("Завтра")){
                newDate = this.addDays(postDate,1);
                correctDate = ft.format(newDate);
            }
            else if (this.textDate.contains("мая")){
                int mPos = textDate.indexOf("м");
                String days = textDate.substring(0,mPos);

                if (days.contains("-")) {
                    String day = days.substring(0,days.indexOf("-"));
                    SimpleDateFormat f =
                            new SimpleDateFormat ("dd");


                    int dCount = Integer.parseInt(day);
                    int dCurrCount = Integer.parseInt(f.format(postDate));

                    newDate = this.addDays(postDate,dCount-dCurrCount);
                    correctDate = ft.format(newDate);

                }
                else if (this.textDate.contains("Мая")) {
                    int mmPos = textDate.indexOf("М");
                    String ddays = textDate.substring(0, mmPos);

                    if (ddays.contains("-")) {
                        String day = ddays.substring(0, ddays.indexOf("-"));
                        SimpleDateFormat f =
                                new SimpleDateFormat("dd");


                        int dCount = Integer.parseInt(day);
                        int dCurrCount = Integer.parseInt(f.format(postDate));

                        newDate = this.addDays(postDate, dCount - dCurrCount);
                        correctDate = ft.format(newDate);

                    }
                }
                else {
                    SimpleDateFormat f =
                            new SimpleDateFormat ("dd");
                    //System.out.println(days.trim());
                      days = days.trim();
                    int dCount = Integer.parseInt(days);
                   int dCurrCount = Integer.parseInt(f.format(postDate));

                    newDate = this.addDays(postDate,dCount-dCurrCount);
                    correctDate = ft.format(newDate);
                }



            }
        }



    }

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
    public String getDate(){
        return this.correctDate;
    }
    public String getTime(){
        return this.time;
    }

    public static void main(String argv[]){
        String test = "14-15 мая 23:00";
        DateHandler date = new DateHandler(test,1462982189);
        date.Parse();
        System.out.println(date.getDate()+ " " + date.getTime());
    }
}
