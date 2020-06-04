package salmo.SalmoWebsite;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;


public class Days {
    private String[] days = new String[7];

    public String[] getDaysOfTheWeek() {
        LocalDate currDate = LocalDate.now();
        DayOfWeek currDay = currDate.getDayOfWeek();
        if (currDay == DayOfWeek.MONDAY) {
            for (int i = 0; i < 7; i++) {
                days[i] = "" + currDate.plusDays(i);
            }
        } else if (currDay == DayOfWeek.SUNDAY) {

            for (int i = 6; i >= 0; i--) {
                days[i] = "" + currDate.minusDays(i);
            }
        } else {
            DayOfWeek currDay2 = currDay;
            LocalDate currDate2 = currDate;
            days[currDay2.getValue() - 1] = "" + currDate;
           
            while (currDay2 != DayOfWeek.SUNDAY) {
                currDay2 = currDay2.minus(1);
                currDate2 = currDate2.minusDays(1);
                days[currDay2.getValue() - 1] = "" + currDate2;
            }
            currDate2 = currDate;
            currDay2 = currDay;
            while (currDay2 != DayOfWeek.SUNDAY) {
                currDay2 = currDay2.plus(1);
                currDate2 = currDate2.plusDays(1);
               
                days[currDay2.getValue() - 1] = "" + currDate2;
            }

        }
        return days;
    }

    public static void main(String[] args) {

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        Days d = new Days();
        String[] days = d.getDaysOfTheWeek();
        for (int i = 0; i < 7; i++) {
            System.out.println(days[i]);
        }
    }
}
