import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class StudyCollections {

    public static void main(String[] args) {

        /*Amac bellekte ki verileri organize etmek. Bu organizasyon icin de farkli yapilar var.
        * Bu yapilar bize collection api tarafindan sunuluyor. Farkli turde yapilar. 3 temel gruba ayriliyor.
        * Baskalari da var ama daha cok bu 3 grup kullaniliyor.
        * 1.List
        * 2.Set
        * 3.Map*/

        //1.List: Duplicates , ordered
        List<String> names = new ArrayList<>();
        names.add("jack");
        names.add("kate");
        names.add("james");
        names.add("ben");
        names.add("james");
        names.add(0,"jin");
        names.add("sun");
        System.out.println(names.get(0));
        System.out.println(names.get(2));
        System.out.println(names);
        names.sort(String::compareTo);
        System.out.println(names);

        //There are two implementations: ArrayList, LinkedList
        //ArrayList -> Array

        //Sort metodu java8 ile beraber gelmis. Oncesinde Arrays.sort yada Collections.sort kullaniliyormus.
        //Collections.sort(names);

        /*List bir interface bu interface'in 2 implementasyonu var.
          ArrayList -> Arka tarafta array uzerinde organize ediyor. ->Random access performansi cok yuksek.
          Ama yeni eleman ekledigimde ekledigim yerden sonraki tum elemanlarin konumu 1 kaydiriyor. ArrayList kalabalik
          ise bu cok masrafli. Sadece eleman ekledigimde de oteleme olmuyor silme isleminde de oteleme gerceklesecek.
        * LinkedList -> Her dugum kendinden sonraki ve onceki dugumu gosterecek sekilde bir baglanti kuruyor.
        -> LinkedList'de aralara eleman ekleyip cikarmak ucuz cunku baglantilari koparip tekrar olusturuyor ama
           rastgele erisim performansi dusuk cunku herhangi bir elemana gitmek istedigimde ilk elemandan itibaren
           tum elemanlari asil ulasmak istedigim elemana ulasana kadar ziyaret ediyorum. Bir elemana ulasmak istedigimde
           maliyeti arraylist'e gore daha fazla oluyor. List interface'inin implementasyonu oldugu icin ArrayList ve
           LinkedList, 2'sini de kodda degisiklik yapmadan birbirinin yerine kullanabilirim.(polymorphism, open-closed principle)
           ArrayList ve LinkedList'de eleman eklerken spesifik bir indis vermezsem eger elemani ilk bos goze ekleyecektir.
            */

        /*ArrayList arka tarafta array yapisini kullaniyor. Array'i dinamik hale getirip kullaniyor.
        * Yani Array 10 elemanli mesela 11. elemani eklemeye calistiginda gidip bellekte yer aciyor vs.
        * Kapasite arttirmanin maliyeti var ama dinamik bir arrray elde ediyoruz. Bunun algoritmasi kodu vardi.
        *  Arka tarafta duz array'den faydalanip ayni zamanda genericlerden faydalaniyordu.
        * */

        /*LinkedList baglantili liste aslinda yani bir dugum var o dugumde veriyi sakliyorum ama o dugum ayni zamanda
        * kendinden sonraki verinin adresini gosteriyor. Yani kendinden sonraki dugumun referansini tasiyor ayni
        * zamanda. Ayni zamanda kendinden onceki dugumun referansini da tutuyor. Bundan dolayi zaten rastgele erisim
        * performansi dusuk. m. elemana ulasmak icin m-1 tane eleman ziyaret etmek gerekiyor. Ama bu yapidan dolayi
        * eleman ekleme cikarma isleri maliyetsiz. Yeni eleman ekleyecegin zaman araya bir dugum atiyorsun o dugumden
        * dugum artik yeni dugumu gosterecek. Yeni dugumde kendisinden sonraki dugumu gosterecek artik. Kendisinden
        * sonraki dugumde artik yeni dugumu gosterecek.*/

        //Cogu zaman ArrayList daha basarili performans sergiledigi icin ArrayList tercih ediliyor.

        int[] numbers = {4,8,15,16,23,42};
        //LinkedList -> [4] -> [8] -> [15]
        //               /\     |
        //                |_____|
        System.out.println(numbers.length);
        System.out.println(numbers[3]);

        //2.Set: Distinct, unordered -> HashSet, LinkedHashSet, TreeSet(3 imp. var set'in.)
        Set<String> set = new HashSet<>();
        set.add("jack");
        set.add("kate");
        set.add("james");
        set.add("ben");
        set.add("kate");
        set.add("jin");
        set.add("sun");
        set.add("kate");
        set.add("ali");
        set.add("veli");
        set.add("abc");
        System.out.println(set);
        //Error: set is unordered -> you can not ask for indexed elements
        //System.out.println(set.get(2));
        //Error: Set does not have sort method: set.sort()

        /*Set icin unordered dedik ama birsekilde sirali geldi nasil oldu bu?
        * Set'de aslinda bir interface ve farkli implementasyonlari var.
        * HashSet eleman siralamasi kaotik, rastgele yani. Yeni eleman ekledigim zaman onceki elemanlarin sirasi da
        * degisiyor(Ben de degismedi???). Arka tarafta ArrayList var bir elemanin hangi goze yazilacagini soyleyen sey burada ki hashcode.
        * Object sınıfından gelen hashcode diye bir metod var , hashfunction ile hashlenen degere geri donemem
        * bundan dolayi oneway function diyoruz. Encryption tekrar decryption edilebiliyor bundan dolayi hashlemek ile
        * sifrelemek farkli seyler. Sifrelemek simetrik bir islem. Hash'de mevcut durumun ozeti cikariliyor gibi dusunebilirim.
        * Ama sikistirma algoritmasi degil sadece dosyanin kimligini ifade ediyor bundan dolayi zaten ne verirsen ver hep ayni
        * ufak buyuklukte bir cikti veriyor. (256 byte)
        * Burada da biz bu hash degerini arrayde hangi goze koyalim bunun kararini vermek icin kullaniyoruz. Hash degerleri
        * kaotiktir. 1 TB dosyanin hash degerini elde ettim. 256 byte. Bir biti degistirdim. Bu 256 byte'i hash algoritmasindan
        * gecerirsem bambaska bir 256 byte karsima cikar. Bellekte olusturulan hashset'i 2 defa cagirdigim zaman ama o siralama
        * degismez cunku zaten bellekte olusmustu hashset'e ekleme cikarma vs. yapmadim sonucta.
        *  */

        /*Set'de eleman var mi sorusuna en hizli yaniti hashset veriyor. Cogunlukla hashset kullanilmasi tavsiye ediliyor
        * bundan dolayi.*/

        System.out.println(set.contains("jack"));
        System.out.println(names.contains("jack"));

        /*Listede bu durum maliyetli. Tek tek listede ki tum elemanlari aradigi elemani bulana kadar yoklayarak bakiyor. Aradigi
        eleman  listenin sonlarinda ise bu durum oldukca maliyetli oluyor. Linear search yapiyor. HashSet'de durum boyle degil.
        Elemanlari eklerken objenin nesnenin ürettiği hashcode'nu kullanarak bir yere yaziyor. Var mi diye sordugunda string'in yine
        hashcode'unu aliyor gidip orada var mi diye bakiyor nereye bakacagini biliyor. Var mi sorusunun yanitini bundan dolayi en iyi
        hashset veriyor.*/

        Set<String> linkedSet = new LinkedHashSet<>();
        linkedSet.add("jack");
        linkedSet.add("kate");
        linkedSet.add("james");
        linkedSet.add("ben");
        linkedSet.add("kate");
        linkedSet.add("jin");
        linkedSet.add("sun");
        linkedSet.add("kate");
        linkedSet.add("ali");
        linkedSet.add("veli");
        linkedSet.add("abc");
        System.out.println(linkedSet);
        /*LinkedHashSet ekleme sirasini koruyor. Elamanlarin sirasi onemliyse linkedhashset kullanmaliyim. Ekleme sirasina gore
        * yapiyor bunu.*/

        Set<String> treeSet = new TreeSet<>();
        treeSet.add("jack");
        treeSet.add("kate");
        treeSet.add("james");
        treeSet.add("ben");
        treeSet.add("kate");
        treeSet.add("jin");
        treeSet.add("sun");
        treeSet.add("kate");
        treeSet.add("ali");
        treeSet.add("veli");
        treeSet.add("abc");
        System.out.println(treeSet);
        /*TreeSet alfabetik olarak sıralama yapiyor. Elemanlari alfabetik olarak sirali olarak sakladigi icin eleman eklemeler
        * ve silmeler biraz daha yavas. TreeSet (Red-Black Tree/Balanced Tree olarak geciyor. Dengeli bir sekilde elemanlari
        * sakliyor)*/

        /*Set'i alfabetik sekilde siraliyorum. Peki ben bu siralamayi tersten yapmak istersem ne yapmaliyim? Lambda expression
        * kullanabilirim.*/

        Comparator<String> dictionaryOrderDesc = (u,v) -> v.compareTo(u);

        //HashSet<>(dictionaryOrderDesc); Duz HashSet bunu veremiyorum. Cunku onun icin bu sekilde bir implemantasyon yok.
        //Olmamasi da normal aslinda. Cunku HashSet icin zaten bir siralama algoritmasi yok.
        Set<String> setReverse = new TreeSet<>(dictionaryOrderDesc);
        setReverse.add("jack");
        setReverse.add("kate");
        setReverse.add("james");
        setReverse.add("ben");
        setReverse.add("kate");
        setReverse.add("jin");
        setReverse.add("sun");
        setReverse.add("kate");
        setReverse.add("ali");
        setReverse.add("veli");
        setReverse.add("abc");
        System.out.println(setReverse);

        /*Siralamayi kendimiz karistiralim.*/

        Comparator<String> dictionaryRandom = (u,v) -> ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE,Integer.MAX_VALUE);
        Set<String> setRandom = new TreeSet<>(dictionaryRandom);
        setRandom.add("jack");
        setRandom.add("kate");
        setRandom.add("james");
        setRandom.add("ben");
        setRandom.add("kate");
        setRandom.add("jin");
        setRandom.add("sun");
        setRandom.add("kate");
        setRandom.add("ali");
        setRandom.add("veli");
        setRandom.add("abc");
        System.out.println(setRandom);

        /*Tabi yukarida isleri karistirinca set'in yapisini algoritmasini bozmus olduk. Ilk eklenen kate ile 2. eklenen kate'i artik
        * esit olarak gormuyor. Esit gordugu zaman 0 donuyordu. Her birini 0 dondurecek sekilde lambda expression'i yazalim.*/

        Comparator<String> dictionaryAlwaysEqual = (u,v) -> 0;

        /*Tum elemanlari farkli olmalarina ragmen esit olarak gordugu icin geriye sadece set'e eklenen ilk eleman kalacak.*/

        Set<String> setAlwaysEqual = new TreeSet<>(dictionaryAlwaysEqual);
        setAlwaysEqual.add("jack");
        setAlwaysEqual.add("kate");
        setAlwaysEqual.add("james");
        setAlwaysEqual.add("ben");
        setAlwaysEqual.add("kate");
        setAlwaysEqual.add("jin");
        setAlwaysEqual.add("sun");
        setAlwaysEqual.add("kate");
        setAlwaysEqual.add("ali");
        setAlwaysEqual.add("veli");
        setAlwaysEqual.add("abc");
        System.out.println(setAlwaysEqual);

        //Map'in list ve set'den farki 2'li olarak saklamasi. Key,Value 2'lileri olarak sakliyoruz.

        Map<String,Integer> areaCodes = new HashMap<>();
        areaCodes.put("Ankara",312);
        areaCodes.put("Istambul-Avrupa",212);
        areaCodes.put("Istanbul-Anadolu",216);
        System.out.println(areaCodes.get("Istanbul-Anadolu"));
        /*Tam tersi ile aradigimi bulmak istersem eger yani key ile value buluyorum. Value ile key'i bulmak istersem
          linear-search -> tum elemanlara gozlere bakmam lazim. Ama bu yavas calisir. 
        */

        for (var entry: areaCodes.entrySet()
             ) {
            if(entry.getValue()==312){
                System.out.println(entry.getKey());
            }
        }

        /*Bunun yerine daha hizli sonuc elde etmek icin value ile key yerlerini degistirip yeni bir hashmap yaratip
        * bu hashmap uzerinden aradigimi bulmaya calisabilirim.*/

        Map<Integer,String> codeAreas = new HashMap<>();
        codeAreas.put(312,"Ankara");
        codeAreas.put(216,"Istambul-Avrupa");
        codeAreas.put(212,"Istambul-Anadolu");
        System.out.println(312);

        /*Bir key var. Birden fazla value tutsun istiyorum. Buna multikey yapisi deniyor. Java bunu dogrudan desteklemiyor
        * ama cozumu var.*/

        Map<String,List<Integer>> multikeyHashMap = new HashMap<>();
        multikeyHashMap.put("Ankara", List.of(312));
        multikeyHashMap.put("Istanbul", List.of(212,216));

        /*Bu tarz durumlarda kodla cozmek istemezsek eger cozum icin github guava veya eclipse collection api'a bakabiliriz.
        * Low latency dusuk gecikme sureli uygulamalar yaziyorsak eclipse collection faydali. Yine guava'da bu tarz durumlar
        * icin degil sadece ayni zamanda farki ihtiyaclar icin hashing caching gibi durumlar icin faydali.
        * Hashing -> Hashing, farklı büyüklükteki girdilerden sabit büyüklükte bir çıktı yaratma sürecine verilen isimdir.
        * Bu işlem, hash fonksiyonları olarak bilinen matematiksel formüllerin (hashing algoritmaları olarak uygulanır)
        * kullanımıyla yapılır.
        * Caching -> Caching sık kullanılan dataları kaydetme tekniğine verilen isimdir. Kaydetme işlemi uygulamayı host
        * eden sunucunun ram belleğinde(In Memory Caching) ve harici bir caching sunusunda(Distributed Caching) gerçekleştirilebilir.
          Cacheleme, kullanıcılara pozitif yönde bir uygulama deneyimi sağlar. Çok sık erişilmeyen dataları cachelemek gereksiz bir
          kaynak kullanımına sebeb olacaktır. Çok sık değişen dataları ise cachelememek gerekir. Çünkü istek yapan kullanıcıya yanlış
          * data göndermiş oluruz.*/


    }

}
