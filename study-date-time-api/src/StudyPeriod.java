import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

public class StudyPeriod {

    public static void main(String[] args) {

        var annualy = Period.ofYears(1);
        var quarterly = Period.ofMonths(3);
        var everyYearAndAWeek = Period.of(1,0,7);//years, months, days

        LocalDate date = LocalDate.of(2020,7,11);
        LocalTime time = LocalTime.of(6,15);
        LocalDateTime ldt = LocalDateTime.of(date,time);
        System.out.println(ldt);
        ldt = ldt.plus(quarterly);
        System.out.println(ldt);
        ldt = ldt.plus(annualy);
        System.out.println(ldt);
        ldt = ldt.plus(everyYearAndAWeek);
        System.out.println(ldt);

        /*Periodlar zincir oluşturmama gerek kalmadan doğrudan belli başlı araklıklar dahilinde
        * tarihi elde etmemi sağlayacak. Çok sık kullanılan dönemsel işler olduğu
        * zaman period kullanmak mantıklı. Zaman için period yok. Çünkü period döenmsel işlerde
        * işime yarıyor date için faydalı ama time için anlamsız. Zaman için Period gibi ayrı
        * Duration sınıfı var.*/
    }

}
