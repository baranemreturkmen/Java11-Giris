import java.math.BigDecimal;

public class StudyFloatingPointTypes {

    public static void main(String[] args) {
        float f = 3.1415F; //4 byte
        double g = 2.0; //8 byte
        g= g -1.10;
        System.out.println(String.format("%16.9f",g));//noktadan önce 16 basamak noktadan sonra 9 basamak yazacak.
        //if(g==0.9) hiç gelmez aman dikkat!!!

        f =  1_000_000_000;
        f = f + 50;
        System.out.println(String.format("%16.3f",f));
        /*Hatalı sonuçlarla karşılaşıyoruz neden? 0-1 arası sonsuz sayı sonsuz ihtimal var. Bu sayıları göstermek için
        * 64 bit 2 üzeri 64 tane sayıyı göstermek istiyorum. Bundan dolayı hatasız gösterim yapmak kaçınılmaz floating
        * point sayılarda. Bu hatanın çalışma şekli küçük sayılar için küçük büyük sayılar için büyük hatalar şeklinde.
        * Büyük hatalar olması gereken değere göre yine küçük kalıyor bu arada. Hata scale edilmiş. IEEE-764 bu standarta
        * göre belirlenmiş işlemci bu şekilde çalışıyor. Bu tarz hatalardan kaçmak için BigDecimal kullanmak lazım.*/

        BigDecimal bd = BigDecimal.valueOf(1_000_000_000.);
        bd = bd.add(BigDecimal.valueOf(50));
        System.out.println(bd);

        double q = 1.0/0;
        System.out.println(q);//Infinity
        double p = -1.0/0;
        System.out.println(p);//-Infinity
        double k = 0.0/0.0;
        System.out.println(k);//NaN
        k++;
        System.out.println(k);//NaN
        System.out.println(k==Double.NaN);//false
        System.out.println(k==k);//false

        //O zaman bir sayının NaN olup olmadığını nasıl anlarım?
        System.out.println((k==k)==false);//true vermeli. NaN değilse false verecektir.
        System.out.println(Double.isNaN(k));//yukarıda ki aynı mantıkla yazmışlar bunu da zaten.
        /*NaN işlemci açısından özel bir durum. Ortaya çıktığı zaman işlemci içerisinde NaN özel bir değerle temsil ediliyor.
          işlemci içerisiden ki floating point NaN ne olduğunu biliyor. 1/0 0/0 gibi durumlarda hesaplama işine girmiyor. Hemen
          NaN temsil eden sayıyı koyuyor sonuca. Karşılaştırmada da bakıyor değişken içerisinde NaN karşı gelen sayı var mı?
          varsa ona göre davranıyor. NaN,-Infinite,Infinite işlemci içerisinde özel değerleri var. Bu 3 değer özel olarak kodlanmış.
         */

        /*Özellikle test yazarken kayan noktalı sayılarla işlemler varsa dikkat et eşitlik durumları hata payları vs. vs.*/

        /*BigDecimal çok daha yavaş çalışıyor ama düzgün sonuç veriyor.*/
    }

}
