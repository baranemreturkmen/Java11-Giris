import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class StudyIntegralTypes {

    public static void main(String[] args) {
        byte b = 127; // 1 Byte -> signed int -> [-128...127]
        byte c = 127;
        //Bu arada Java'da unsigned diye birşey yok herşey işaretli.
        System.out.println("b="+b);
        //Implicit Type Casting
        b++;//Burada görünmeyen bir type casting var. b = (byte) (b+1) veya b+=2 için de
        c+=2;
        System.out.println("b="+b);//Runtime'da hata fırlatmıyor -128'e dönüyor.
        System.out.println("c="+c);/*-127 oldu. Javadan bağımsız. İşlemci bu şekilde çalışıyor.
                                      İşlemcide taşma diye birşey yok her zaman karşılığı var birşeylerin.*/

        /*0111 1111
        * 0000 0001
        * +_________
        *  1000 0000 -> - sayı tümleyen al -> 0111 1111 -> -127
        *  1000 0000'da en soldaki sayının 2'lik sistemde - olduğunu ve tümleyen alman gerektiğini söyler.
        * 1111 1111 -> 0000 0000 -> -1 oluyor.*/

        short s = Short.MAX_VALUE;
        System.out.println("s="+s);// 2 Byte, [-32768...32767]
        s++;
        System.out.println("s="+s);

        int i = Integer.MAX_VALUE; //4 Byte
        long l = 108; //8 Byte
        l = 12345 + 5432L; //Neden L'ye ihtiyacım var?
        l=i+1;//Toplamayı integer'a göre yapar. ınt sınırları dışına çıkarım -32... birşey gelir. i+1L olmalı.

        //4.30 Exception Başlangıç + Enum ve Polymorphism(eklenecek...)

        byte x=3, y=5, z;
        //z = x+y; neden hata verdi? + işareti her zaman int sonuç üretiyor. int 4 byte. byte'a atamazsın.
        //Explicit Type Casting
        z = (byte) (x+y);

        l = Long.MAX_VALUE;
        System.out.println("l=" +l);
        l++;
        System.out.println("l=" +l);

        //BigInteger > Long
        //BigInteger immutable class
        BigInteger bi = BigInteger.valueOf(Long.MAX_VALUE);
        System.out.println("bi= "+bi);
        bi = bi.add(BigInteger.ONE);
        /*Değişikliği görmek için atama yapmak gerekli. Tek başına yeni bir BigInteger dönüyor bi.add. Değişmezliği yıkmak adına
        * atama yaptığını düşün. Değişen şey ilk 48. satırda atama yapılan bi değil. Aslında değişmesi lazım ama immutable
        * olduğu için add metoduyla değişmiyor. Değişikliğin yansıması adına değişen bi.add'i ilk declare edilen bi'ye atıyorum.*/
        System.out.println("bi= "+bi);

        //String immutable class
        String jack = "Jack";
        jack = jack.toUpperCase();
        System.out.println(jack);

        /*Wrapper Classes -> her bir primitive tip için karşılık gelen bir class var.
        * int -> Integer
        * byte -> Byte
        * short -> Long
        * char -> Character
        * boolean -> Boolean
        * float -> Float
        * double -> Double
        * Wrapper classes are immutable. Neden kullanıyoruz wrapper classları? Generic tipler primitive olamaz.
        * List<Integer> list
        * Wrapper classlar, primitive tipler class olmadığı için sınıfın getirdiği kazanımların hiç birini kullanamıyoruz.
        * Wrapper class içerisinde metod vs. olmuyor. Avantajı oop primitive tipler ile buluşturması. Dezavantajı ise
        * int: 4 byte 1m -> 4mb, Integer: -> Object Header -> 12b + 4b -> 16b 1m -> 16mb (12mb overhead)*/

        ThreadLocalRandom.current()
                .ints(1,60)//Stream'in performans için int akması lazım. Ama listeye alırken int veremeyiz.
                .distinct()
                .limit(6)
                .sorted()
                .boxed()//Ne iş bu? Neyi paketliyoruz? stream'de ki int'leri box'lıyoruz. Paketliyoruz. int -> Integer
                .collect(Collectors.toList())
                .forEach(System.out::println);

        int [] array1 = {1,2,3,4,5};
        Integer [] array2 = {1,2,3,4,5}; //Daha maliyetli.

        Integer u = 42;//Arkada compiler tarafında nasıl çalışıyor -> Integer.valueOf(42) -> Java SE 5: Auto Boxing
        int v = u;//u.intValue() -> Unboxing -> Integer -> int(int üzerinden ilerledi ama mesela floatValue vs. tüm primitive typelar için var bu durum.)
        Integer w = 108;
        u = v +w;//Integer.valueOf(u.intValue() + w.intValue())

        //int r = 1/0 -> hata verir. ArithmeticException. Kayan noktalı sayılarda böyle değil Infinity oluyor.
    }

}
