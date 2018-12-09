import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;



public class writeInfo implements  org.quartz.Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Written into the file");
        List<String> cities = new ArrayList<>();
        for (People p: Main.cityNameLastPesel) { //adding into cities cities from cityNameLastPesel
            cities.add(p.city);
        }
        Set<String> sorted = new LinkedHashSet<>(cities);
        cities = new ArrayList<>(sorted);
        Collections.sort(cities);
        try(FileWriter writer = new FileWriter("INFO.txt", false)) {
            for (String c : cities) {  //writting all elements from cities
                writer.write(c + "\n");
                for (People p : Main.cityNameLastPesel) { //writting other elements and compating cities
                    if (p.city.equals(c)) {
                        String text = (p.nameLast + " " + p.pesel + "\n");
                        writer.write(text);
                        writer.flush();
                    }
                }
            }
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
