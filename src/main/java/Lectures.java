import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Locale;

public class Lectures implements org.quartz.Job {
    long millisec = 0;
    DateTime now = new DateTime();
    DateTime[] lecTime = {new DateTime().withHourOfDay(8).withMinuteOfHour(15), new DateTime().withHourOfDay(9).withMinuteOfHour(45), new DateTime().withHourOfDay(10).withMinuteOfHour(0),
            new DateTime().withHourOfDay(11).withMinuteOfHour(30), new DateTime().withHourOfDay(11).withMinuteOfHour(45), new DateTime().withHourOfDay(13).withMinuteOfHour(15), new DateTime().withHourOfDay(13).withMinuteOfHour(45),
            new DateTime().withHourOfDay(15).withMinuteOfHour(15), new DateTime().withHourOfDay(15).withMinuteOfHour(30),
            new DateTime().withHourOfDay(17).withMinuteOfHour(0), new DateTime().withHourOfDay(17).withMinuteOfHour(15), new DateTime().withHourOfDay(17).withMinuteOfHour(45), new DateTime().withHourOfDay(19).withMinuteOfHour(00),
    };

    public void execute(JobExecutionContext context) throws JobExecutionException {
            DateTime now = new DateTime();
            boolean breakLecture = false;
            now.plusHours(1);
            for (int i = 0; i < lecTime.length; i++) {
                millisec = lecTime[i].getMillis() - now.getMillis();
                if (millisec > 0) {
                    if (i % 2 == 0) {
                        breakLecture = true;
                    } else {
                        breakLecture = false;
                    }
                    break;
                }
            }
            if (breakLecture) {
                System.out.println(String.format(Locale.getDefault(), "%02d", millisec / 1000 / 60) + " minutes till the end of lecture ");
            } else {
                System.out.println(String.format(Locale.getDefault(), "%02d", millisec / 1000 / 60) + " minutes till the end of break ");
            }
    }
}
