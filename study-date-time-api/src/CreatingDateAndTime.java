import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class CreatingDateAndTime {

    public static void main(String[] args) {

        /*Daha önce java'da birçok kez dateandtime ile ilgili api yazılmaya çalışmış.
        * JavaSE 8 ile gelen dateandtime api'si son çıkan tüm ihtiyaçları karşılayan api.
        * java.time.* diyerek eski time apisindeki sınıfları kullanabilirim. Ama java 8
        * ile gelen yeni apiyi kullanmakta fayda var. Yeni api ile genel olarak zamanla
        * ile ilgili eski timeapisinin karşılığının olmadığı classlara erişebiliyorum. partial,
        * deletion, period, interval gibi veya zamanda bir noktayı bir aralığı tanımlamak istediğimde
        * yeni api ile bunları tanımlayabiliyorum. date, time, instance, calendar gibi classlar var
        * ve tüm ihtiyaçları karşılıyor. Zamanla ile ilgili iso standartlarını yada farklı standartları
        * destekleyen bir api.*/

        /*İçerisinde gün ay yıl bilgisi var.*/
        LocalDate localDate;
        /*Saat bilgisi var.*/
        LocalTime localTime;
        /*Her ikisini de barındıran sınıfım var.*/
        LocalDateTime localDateTime;
        /*Zaman diliminin dahil olduğu sınıf.*/
        ZonedDateTime zonedDateTime;

        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(ZonedDateTime.now());

        var tr = new Locale("tr","TR");
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(tr);
        System.out.println(dtf.format(ZonedDateTime.now()));

        DateTimeFormatter dtf2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(Locale.ITALY);
        System.out.println(dtf2.format(ZonedDateTime.now()));

    }

}
