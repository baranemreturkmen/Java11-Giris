package com.example;

import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.entity.Gender;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Exercise1 {

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("1","jack","bauer",1956, Gender.MALE, Department.HR,25_000),
                new Employee("2","kate","austen",1978, Gender.FEMALE, Department.FINANCE,35_000),
                new Employee("3","jin","kwon",1976, Gender.FEMALE, Department.MARKETING,45_000),
                new Employee("4","ben","linus",1954, Gender.MALE, Department.IT,55_000),
                new Employee("5","sun","kwon",1973, Gender.MALE, Department.SALES,15_000),
                new Employee("6","james","sawyer",1980, Gender.MALE, Department.IT,27_000)
        );
        //Calisanlarin maaslarinin toplamini istiyorum.

        /*jack -> kate -> jin -> ben -> sun -> james -----> employees.stream()
          25000 -> 35000 -> 45000 -> 55000 -> 15000 -> 27000 -------> .map(Employee::getSalary)
          25000 + 35000 + 45000 + 55000 + 15000 + 27000 -----> .reduce(0.0,Double::sum)
          accumulator -> 202000
          (0.0,25000) -> 25000
          (25000,35000) -> 60000
          (60000,45000) -> 105000
          ..... -> reduce() -> 202000
        * */

        //Method reference kullandim :: ilk tercih etmem gereken bu. Eger hazir metod bulamiyorsam lambda expression yazarim.
        double totalSalary = employees.stream().map(Employee::getSalary)
                .reduce(0.0, Double::sum);

        //Lambda expression kullandim ->
        totalSalary = employees.stream().map(employee -> employee.getSalary())
                .reduce(0.0, (sum,salary) -> sum + salary);

        /*
        * Stream API Methods:
        * i. intermediary methods: filter, map, distinct, limit, sorted, boxed, ...
        * ii. terminal methods: forEach, reduce, sum, min, max, count, collect, findFirst, findAny, ...
        * Ben istedigim kadar intermediary metod kullanabilirim. Ama mutlaka sonunda bir tane terminal metod
        * olmali neden?
        *
        * Lazy Evaluation: Bu yapi soyle calismiyor. Once bir stream aciliyor sonra map sonra reduce falan.
        * Bu sekilde degil. Boyle olsaydi yavas calisirdi. Soyle calisiyor aslinda terminal metod tum bu sureci
        * baslatan tetikleyen metod. En sona terminal metod yazmazsak eger surec baslamiyor calismiyor. Bu sekilde
        * tek bir tarama yapip tum islemi gerceklestirebiliyor. Ama diger turlu her metod icin tek tek tarama yapacakti
        * ve surec yavaslayacakti. Stream api ile yapilan isi terminal metodla yapma durumuna bu duruma lazy evaluation
        * deniyor. Daha onceki cagrilarda not aliyor. Map'i cagirdim mi cagirdim  su fonksiyomu kullanarak cagirdim vs.
        * Yemek siparisi gibi. Her seyi tum intermediary metodlari kenara not aliyor. En son terminal metodu gorunce tamam
        * deyip sureci baslatiyor. En bastan sureci baslatmiyor artik.   */

        System.out.println("Total salary is "+totalSalary+" \u20BA");

        //IT bolumunde ki calisanlarin toplamini istiyorum.

        //Lambda expression
        long count = employees.stream().filter(employee -> employee.getDepartment()==Department.IT).count();

        //Method Reference
        count = employees.stream().map(Employee::getDepartment)
                //.filter(department -> Department.IT.equals(department)
                .filter(Department.IT::equals).count();

        /*count hazir bir metod count olmasiydi bu sorunu nasil cozerdim?
        * Department enum 1 ITye karsilik geliyor. reduce ile de topluyorum.
        * .map(department -> 1).reduce(0,Integer::sum); count()'a karsilik geldi.*/
        count = employees.stream().map(Employee::getDepartment)
                .filter(Department.IT::equals).map(department -> 1).reduce(0,Integer::sum);
        System.out.println("# of employees in IT department is "+count);

        /*map ile farkı su filter'in. map'de n tane eleman girdiyse n tane cikar. filtreleme yapmaz.
         filter'da n tane girer n veya n-x tane cikar adi ustunde zaten filtreleme yapiyor.
         */

        //Tum departmanlarda ki calisanlarin sayilarini ayri ayri bulalim.

        /*
        * Key -> Value
        * Department.IT -> {ben, james}
        * Department.SALES -> {sun}
        * Department.FINANCE -> {kate}
        * Department.MARKETING -> {jin}
        * Department.HR -> {jack}
        * */

        Map<Department, List<Employee>> departmentMap = employees.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println(departmentMap);

        /*foreach() Consumer tipinde donus yaptigi icin Consumer kullandik.
          map'e ait interface aslinda Entry. Map'in kendisi de interface.
        * */
        Consumer<Map.Entry<Department,List<Employee>>> printEntry = entry -> System.out.println(entry.getKey()+": "+entry.getValue());

        /*printEntry tek basina anlamsiz cunku kendisi bir referans aslinda,
          kendisi sadece lambda expression. Bu lambda expression'a ait degerlere
          entrySet() ve forEach sayesinde ulasiyorum. entrySet() Map'e ait keyleri
          grupluyor zaten bu gruplama isini yapmazsam eger departmentMap'den sonra
          direk forEach() kullanamiyorum.
          */
        System.out.println(printEntry);
        departmentMap.entrySet().forEach(printEntry);

        /*Burada aradigimiz sey departmana gore gruplama yaptiktan sonra o departmana ait
        * calisanlarin bilgileri degil, sadece departmanlarda calisan sayisini istiyoruz.
        * Bundan dolayi Collectors.counting() ikinci parametre olarak verildi.*/
        Map<Department, Long> departmentMap2 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));

        Consumer<Map.Entry<Department,Long>> printEntry2 = entry -> System.out.println(entry.getKey()+": "+entry.getValue());

        departmentMap2.entrySet().forEach(printEntry2);

        //yapi capability olarak .Net linq'ya karsilik geliyor.

        //Calisanlari maaslarina gore buyukten kucuge dogru siralayalim.

        //Birsey demezsem eger kucukten buyuge dogru siralar o yuzden revesed kullandim.
        employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).forEach(System.out::println);

        //En yuksek maasi kim aliyor.
        employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).findFirst().ifPresent(System.out::println);

        //Daha kisa yolu da var.
        employees.stream().max(Comparator.comparing(Employee::getSalary)).ifPresent(System.out::println);

        //En dusuk maasi alani bulalim.
        employees.stream().min(Comparator.comparing(Employee::getSalary)).ifPresent(System.out::println);

        //Erkekler ve kadinlar diye gruplayalim ve her iki grubun en yuksek maasllisini bulalim.
        /*Burada forEach oncesinde collect ile gruplama yapildigi icin forEach 2 deger almasi gerektigini biliyor.
        * BiConsumer aliyor burada parametre olarak dolayisiyla ben 2 deger verip forEach icerisinde dondurebiliyorum.
        * Ilk deger key -> gender ikinci deger ise value -> yani employee'nin kendisi. value bu arada optional yani
        * gruplama asamasinda hic kadin yoksa mesela null donup hata vermek yerine optional donuyor ve hata almiyoruz.*/
        Comparator<Employee> compareBySalary = Comparator.comparing(Employee::getSalary);
        employees.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.maxBy(compareBySalary)))
        .forEach((gender,emp) -> System.out.println(gender+" :"+emp.get()));

        //2 adet veri ile gruplama yapinca forEach BiConsumer aldi parametre alarak daha fazlasi olabilir mi?
    }



}
