import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class CreatingSpecificDateAndTime {

    public static void main(String[] args) {

        var birtDate1 = LocalDate.of(1973, Month.JULY,11);
        var birthDate2 = LocalDate.of(1973,7,11);
        var time1 = LocalTime.of(9,5);
        var time2 = LocalTime.of(9,5,30);
        var time3 = LocalTime.of(9,5,30,200);//4. parametre nano second.

        //Neden constructor üzerinden zaman yaratamıyorum??? new LocalDate tarzında? Sebebi şu
        //LocalDate.of(2020, Month.FEBRUARY, 30);

        /*Böyle bir tarih yok. Constructor'ın bir problemi var validation. Validation için çok geç
        * biryer. Sen nesneyi yaratmaya başladın ama bir baktın constructor parametreleri geçersiz.
        * Exception fırlatabilir miyiz? Evet ama bu hem maliyetli bir durum hem de nesne yaratırken süreci
        * durduyorsunuz. O yüzden bu nesne yaratma işini constructor dışında of metoduna vermişler o nesneyi
        * içeride yaratıyor. Yaratırkende kendi içinde validasyon yapıyor. Yani yukarıdaki kod hata verecek.*/

    }

}
