import java.time.ZoneId;

public class StudyZone {

    public static void main(String[] args) {

        ZoneId.getAvailableZoneIds().stream()
                .forEach(System.out::println);

        System.out.println("-------------------------");

        //Asyadaki zonelara bakalım.
        ZoneId.getAvailableZoneIds().stream()
                .filter(zone -> zone.contains("Asia"))
                .sorted()
                .forEach(System.out::println);
    }

}
