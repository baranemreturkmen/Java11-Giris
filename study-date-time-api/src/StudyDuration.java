import java.time.Duration;
import java.time.LocalTime;

public class StudyDuration {

    public static void main(String[] args) {
        //Period'da yıldan başlayıp güne kadar düşüyordu. Duration'da günden başlayıp nano saniyeye kadar düşüyor.

        var daily = Duration.ofDays(1);
        var hourly = Duration.ofHours(1);
        LocalTime time = LocalTime.of(9,5);
        System.out.println(time);
        time = time.plus(daily).plus(hourly);
        System.out.println(time);

    }

}
