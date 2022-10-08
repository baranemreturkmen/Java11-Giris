import java.text.DecimalFormat;
import java.util.Locale;

public class StudyInternationalization {

    public static void main(String[] args) {

        //i18n -> internationalization
        String city = "izmir";
        System.out.println(city.toUpperCase());//IZMIR oldu bizim için anlamsız.
        Locale locale = new Locale("tr","TR");
        //ISO2, bir dili birden fazla ülke kullanabilir ve kendi içerisinde değişiklikler barındırabilir.
        System.out.println(city.toUpperCase(locale));//İZMİR

        double money = 123456.78;
        DecimalFormat df = (DecimalFormat) DecimalFormat.getCurrencyInstance(locale);
        System.out.println(df.format(money));//123.456,78

        DecimalFormat df2 = (DecimalFormat) DecimalFormat.getCurrencyInstance(Locale.US);
        System.out.println(df2.format(money));//123,456.78

        //Aynı internationalization durumu datetime için de geçerli.


    }

}
