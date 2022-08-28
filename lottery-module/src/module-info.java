import com.example.random.service.RandomNumberService;

module com.example.lottery {
    requires com.example.random;//Hangi modulü kullanıyor.
    uses RandomNumberService;//Hangi interface'i kullanıyor.

    /*Her modülün bir işi olsun istiyoruz. Single Responsibility Principle - SRP
    * Bu prensibi hem sınıf hem modül hem de uygulama seviyesinde mikroservis mimarisinde
    * her bir mikroservis ayrı bir process seviyesinde çalışsın istiyoruz. Her zaman bir
    * modülün arayüzünü, sorumluluğunu tanımlayacak bir interface olur.
    * */

    /*Burada birden fazla Interface'i açabilirdik.*/

}