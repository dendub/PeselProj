import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.naming.Name;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


public  class Main {
    public static List<People> cityNameLastPesel = new ArrayList<>(); // creating list

    public static void main(String[] args) throws SchedulerException {

        JobDetail job1 = JobBuilder.newJob(Lectures.class).build();

        Trigger t2 = TriggerBuilder.newTrigger().withIdentity("Cron trigger").withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")).build();

        Scheduler sch1 = StdSchedulerFactory.getDefaultScheduler();

        sch1.start();
        sch1.scheduleJob(job1, t2);



        JobDetail job = JobBuilder.newJob(writeInfo.class).build();

        Trigger t1 = TriggerBuilder.newTrigger().withIdentity("Cron ").withSchedule(CronScheduleBuilder.cronSchedule("0/30 0/1 * 1/1 * ? *")).build();

        Scheduler sch = StdSchedulerFactory.getDefaultScheduler();

        sch.start();
        sch.scheduleJob(job, t1);

        for(int j = 0; j < 100000; j++ ) {
            System.out.println("Type your city and your personal data:");
            Scanner sc = new Scanner(System.in);
            String city = sc.nextLine();
            String NameLastPesel = sc.nextLine();
            String pesel = NameLastPesel.replaceAll("[^0-9]", "");
            String nameSurname = NameLastPesel.replaceAll("[0-9]", "");

            int[] convertPesel = new int[pesel.length()];

            for (int i = 0; i < pesel.length(); i++) {
                convertPesel[i] = Character.digit(pesel.charAt(i), 10);
            }
            if (convertPesel.length != 11) {
                System.out.println("WRONG PESEL! YOU DUMB! NOT ENOUGH INTEGERS");

            } else {
                int check = 0;
                check = 1 * convertPesel[0] + 3 * convertPesel[1] + 7 * convertPesel[2] + 9 * convertPesel[3] + 1 * convertPesel[4] + 3 * convertPesel[5] + 7 * convertPesel[6] + 9 * convertPesel[7] + 1 * convertPesel[8] + 3 * convertPesel[9];
                check = check % 10;
                check = 10 - check;
                if (check != convertPesel[10]) {
                    System.out.println("WRONG PESEL! YOU DUMB! PUT ANOTHER DIGITS NEXT TIME");
                } else {
                    People dannie = new People(city, nameSurname, pesel);
                    for (People p:cityNameLastPesel) {
                        if (p.pesel.equals(pesel)) {
                            cityNameLastPesel.remove(p);
                            break;
                        }
                    }
                    cityNameLastPesel.add(dannie);

                }
            }
        }
    }
}