public class StudyChar {

    public static void main(String[] args) {

        char c = 'x';// 2 byte, unicode encoded.
        System.out.println(c);//x -> bellekte ascii değeri 120
        System.out.println(c+1);//121, + operandların tipi ne olursa olsun sonucu integer döner.
        System.out.println((char)(c+1));//y

        /*Klavyede olmayan sembolleri nasıl gösterebilirim? backslash u'dan sonra bundan sonraki değeri
          unicode tablosundan hexadecimal veriyorum.*/
        char t = '\u20BA';
        System.out.println(t);//Tl sembolü

        //Unicode tablosunda her karakter 2 byte değil bu arada çin alfabesi vs. 3 byte olabiliyor.
    }

}
