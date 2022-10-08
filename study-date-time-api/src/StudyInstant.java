import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;

public class StudyInstant {

    public static void main(String[] args) {
        //Instant bize zamandaki bir anı veriyor. Bunu 2 zaman aralığı arası ölçmek için kullanabilirim.

        /*Nasıl ki date ve time'da zamanı ve tarihi ileri geri alabiliyorduk instant'da da aynı şekilde
          yapabilirim. Bunu daha çok bir fonksiyonun içeride geçirdiği zamanı ölçmek için kullanabilirim
          çünkü nano saniye kadar hassasiyetli ölçümler yapabiliyorum.*/

        var now = ZonedDateTime.now();
        var t1 = now.toInstant();
        System.out.println(t1);
        var hourly = Duration.ofHours(1);
        var t2 = now.toInstant().plus(hourly);
        System.out.println(t2);

        //Instant'in parse etme yeteniği var. Özellikle csv dosyasından zaman ile ilgili bilgi çekerken işime yarıyor.

        var t3 = Instant.parse("2018-07-18T17:15:00Z");
        System.out.println(t3);

        //Parsedan kasıt içeride yılı ayı ve günü ayrıştırması. Ben artık ayrı ayrı ayı ve günü elde edebilirim.

        //2 zaman aralığını ölçebilirim. Parametre olarak instant verebiliyorum.
        System.out.println(Duration.between(t1,t2));

        //Özet geçmek gerekirse
        /*LocalDate, LocalTime, LocalDateTime, ZonedDateTime
        * Date, Time, Her ikisi birden veya zaman bölgelerini de dahil ederek çalışabiliyorum.*/

        /*Period, Duration -> Zamanda ileri geri gitme
        * Period -> Date ile, Duration -> Time ile kullanılıyor.*/

        /*Instant -> Zamanda belli bir anı tanımlamak ve aradaki süreyi ölçmek için kullanılıyor.*/
    }

}
