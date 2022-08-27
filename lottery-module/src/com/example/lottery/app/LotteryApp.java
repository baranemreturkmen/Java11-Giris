package com.example.lottery.app;

import com.example.lottery.service.business.StandartLotteryService;
import com.example.random.service.RandomNumberService;

import java.util.ServiceLoader;
//import com.example.random.service.business.FastRandomNumberService;

public class LotteryApp {

    public static void main(String[] args) {
        StandartLotteryService lotteryService = new StandartLotteryService();

        /*Ben 2 modül arasında sadece interface import ve export etmesini istiyorum bu şekilde 2 modül arasında ki
        * bağımlılığın gevşek olmasını sağlıyorum. Çünkü interface içerisinde hiçbir implementation bilgisi yok.
        * Ben direk 2 implementasyonu servisi açmak istemiyorum sadece interface'i açmak istiyorum. Bu durumda
        * RandomNumberModule içerisinde provides anahtar kelimesi ile interface'imi açıyorum ve 2 adet implementasyonu
        * olduğunu belirtiyorum. lottery module'de de uses anahtar kelimesi ile RandomNumberModule'de declare ettiğim
        * interface'i veriyorum. Bu şekilde hiç bir zaman FastRandomNumberService veya başka bir implementasyonu burada
        * import etmek zorunda kalmayacağım.
        * */

        /*FastNumberModule'u kullanmak istiyorum nasıl kullanacağım? Service Loader Java6'da geldi.
         *9'da revize edildi bu sorunu çözebilmek için. RandomNumberService implementasyonlarını bu şekilde
         *kullanabileceğim. Fakat bunu lazy bir şekilde yapıyor.*/

        /*Modül requires ile hangi modüle depends ettiğini söylüyor. Diğer modül açmak istediklerini exports ile
         açıyor. Bunu interfaceler aracılığı ile yapıyoruz.*/

        ServiceLoader<RandomNumberService> randomNumberServices = ServiceLoader.load(RandomNumberService.class);

        /*Her ne kadar implementasyonları tam anlamıyla import edemesem de(compile time) run time'da kullanıyorum.
        * Böylelikle modül seviyesinde encapsulation'ı gerçekleştirebiliyorum.*/

        for (RandomNumberService rns: randomNumberServices){
            System.out.println(rns.getClass());
            lotteryService.setRandomNumberService(rns);
            lotteryService.draw(70,6,1).forEach(System.out::println);
        }

        //Tek bir implementasyon için yazıldı.
        //RandomNumberService randomNumberService = randomNumberServices.findFirst().get();
        //new FastRandomNumberService();
        //Aşağıdaki seti unutursan eğer StandartLotteryService içerisinde yapman gereken setter injection'ı unutuyorsun!!!
        //lotteryService.setRandomNumberService(randomNumberService);

        //lotteryService.draw(70,6,10).forEach(System.out::println);

        /*Java module sistemi ile amaçlanan şey Jar dosyası oluştururken modül yapısı sayesinde JVM dosyanın
        * hangi modüle depend ettiğini ve içerisinde hangi modül olduğunu vs. görebiliyor.
        * Ve ihtiyacı olan modülleri çalışma esnasında gidip de başka bir yerde aramıyor çünkü her Jar dosyasında
        * module info var. Daha önce modül yapısı olmadan önce şunu yapıyordu bir sınıf import edildiğinde veya
        * kullanılmaya çalışıldığında classpath'de yer alan tüm Jar dosyaları içerisinde ki tüm paketlere bakıyordu.
        * Tüm paketlerde ki sınıflarda arıyordu. Yada söylenilen paketlerde ki tüm sınıflarda arıyordu. Module yapısı
        * ile birlikte A module'une depend ediyorum diyelim ki A module'nun hangi Jar dosyasında olduğunu biliyor. Jar
        * dosyalarının module olarak karşılıkları var ben Jar dosyamda bir module'e depend ettiğimi soyluyorsam gidip
        * o module var mı yok mu direk bakıyor tüm paketlerin yada belirli paketlerin içerisinde arama işlemini
        * gerçekleştirmiyor.Jar dosyasının ismi önemli değil dosya içerisinde ki modul isimleri onemli(depend ettiği moduller
        * modulun dışarıya açtığı paketler, bu paketlerde ki implementationlar vs.) uygulama çalıştığı zaman tüm moduller
        * arasında ki bağımlıkları ortaya çıkarıyor ve eğer bir module'u bulamazsa(JVM) hata veriyor ve uygulama çalışmıyor.
        * JVM artık neyi nerede araması gerektiğini biliyor. Eskiden uygulamanın belli bir noktasına gelindiğinde import edilen
        * class'ın(ilk defa kullanıyoruz bu class'ı) Jar dosyası bulunamadığı zaman ClassNotFoundException hatası veriyordu.
        * Artık moduller arasında ki bağımlılıkları ifade edebildiğin için bu bağımlılıkların sağlanıp sağlanamadığını
        * araştırıyor. Bir dependency ağacı çıkarıyor. Sınıflar arasında ki kalıtım ilişkisi gibi en başta eksik varsa hiç
        * çalıştırmıyor. 2. faydası tekrar kullanılabilirlik, modulu alıp sınırını çizebiliyorum o modulu alıp başka bir
        * uygulamada kullanmaya kalktığımda o modulun başka hangi modullere bağımlı olduğunu biliyorum bağımlı olduğu modulleri
        * de alıyorum. 3. olarak modulun artık boundaries'ı var modulun artık neye erişip erişmeyeceğini de biliyorum.
        * Encapsulation'ı sınıftan module seviyesine yukseltmiş durumdayız. Jar'ı eskiden buildpath'e classpath'e koyduğumuzda
        * içinden herşeye erişebiliyorduk. Module yapısıyla sadece bana izin verilen export edilen servisleri kullanabiliyorum.
        * Oluşabilecek hataları engelliyor değişimi yönetmeyi kolaylaştırıyor. Module yapısı performansdan ziyade operasyonel.
        * Yönetimi kolaylaştırdı. Engellediği hatalar runtime'da ortaya çıkan sıkıntılı hatalardı. Sınıflar içerisinde private
        * yapıp encapsule etme olayını paket bazında sağlıyor aslında. */
    }
}
