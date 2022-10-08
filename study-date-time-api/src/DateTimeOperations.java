import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeOperations {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2020,2,27);
        System.out.println(date);
        date = date.plusDays(2);
        System.out.println(date);
        date = date.plusWeeks(2);
        System.out.println(date);
        date = date.plusMonths(2);
        System.out.println(date);
        date = date.plusYears(2);
        System.out.println(date);

        /*İlla plus olacak diye birşey yok minus'da olabilir.*/

        /*Benzer metodlar time'da da var. Saat dakika saniye bazlı.*/

        LocalTime time = LocalTime.of(9,5);
        System.out.println(time);
        time = time.minusMinutes(10);
        System.out.println(time);

        /*Api geliştirme tekniklerinde fluent api diye bir teknik var burada onu kullanmışlar.
        * fluent api -> method chaining*/

        LocalDateTime ldt = LocalDateTime.of(date,time);
        System.out.println(ldt);
        ldt = ldt.minusDays(1).minusHours(10).minusSeconds(30);
        System.out.println(ldt);
    }

}
