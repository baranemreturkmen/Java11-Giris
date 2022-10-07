package com.example.banking.app;

import java.util.Optional;
import java.util.function.Consumer;

import com.example.banking.domain.Account;
//com.example.banking.domain.Customer -> Sınıfın ismi fully qualify name -> paket_ismi.sınıf_ismi
import com.example.banking.domain.Customer;
import com.example.banking.domain.InsufficientBalanceException;

//ctrl + shift + o
public class BankingApp {

	public static void main(String[] args) throws InsufficientBalanceException {
		//Problemi nasıl çözeceğimi bilmiyorsam try-cacth yöntemine başvurmamalıyım. Metod imzasında declare etmek yeterli.
		// TODO Auto-generated method stub
		/*
		 * Javada bir paketteki sınıfı başka bir pakette ki class kullanmak isterse onu
		 * mutlaka compiler'a tanıtması gerekir. SORU? Abi public değil miydi Customer
		 * neden buna ihtiyaç duyduk? O zaman burada public'in ne anlamı kaldı???
		 */
		Customer jack;
		jack = new Customer("11111111110", "Jack Bauer", 1956, "jack@example.com", "555 5555");

		// ctrl + space
		Customer meredith;
		meredith = new Customer(null, null, 0, null, null);

		// ctrl + shift + v class'ı paketler arası taşıma

		/*
		 * Yapma böyle şeyler sadece referansı yazar :) fully qualified name + referansı
		 * verir. Referanstan kasıt nesnenin bellekteki adresi.
		 */
		System.out.println(jack);

		// println otomatik içerisine yazdıgım seyleri toString() yapiyor.

		System.out.println(jack.toString());

		/*
		 * Tum classlar object sınıfından miras aldigi icin equals, toString, hashCode,
		 * getClass gibi metodlara sahipler. Bunlar object sınıfından geliyor.
		 */

		/*
		 * getClass ile jack referansının gosterdigi nesnenin tipini sorduk. kalıtım
		 * olsaydı eger yine tek bir sınıfı gosterecekti cunku bir referans tek bir
		 * tipten nesneyi gosterebilir. Daha fazlasının gosteremez.
		 */
		System.out.println(jack.getClass());
		jack.addAccount(new Account("tr1", 10_000));
		jack.addAccount(new Account("tr2", 20_000));
		jack.addAccount(new Account("tr3", 30_000));
		System.out.println("Number of accounts is: " + jack.getNumberOfAccounts());
		System.out.println(jack.getAccount(1));
		System.out.println(jack.getAccount("tr3"));

		/*
		 * jack.getAccount("tr4").withdraw(1_000); ->Null pointer exception! Bu hatadan
		 * kacmak icin ilgili metodun eger null donme gibi bir ihtimali var ise ilgili
		 * metodu java 8 ile gelen optional ile sarmallayalim.
		 */

		/*
		 * lambda expression. Java8 ile gelen fonksiyonel programlama yaptik. getAccount
		 * metodunu optional ile sarmaladigimiz icin sonucunda optional deger donuyor.
		 * Acccount varsa account yoksa optional empty donuyor. Dolayisiyla burada
		 * Account'a bagli withdraw'ı eskisi gibi getAccount uzerinden direk
		 * cagiramiyorum cunku eskisi gibi getAccount direk bir account nesnesi
		 * donmuyor, optional'a bagli bir account donuyor, bundan dolayi mainde de
		 * optional'a bagli bir metod uzerinden withdraw metodunu cagirma islemini
		 * yapmaliyim o metodda ifPresent metodu. Girdi olarak para cekilecek bir
		 * account aliyor. Fonksiyon govdesinde ise account.withdraw ile 1000 lira
		 * cekiyoruz. Yani withdraw metodunu lambda expression ile ifPresent metodu
		 * icerisine yerlestirmis oldum. Daha okunur ve daha safe bir kod yazdim.
		 */

		// ifPresent arkada nasil calisiyor???
		/*Normalde buradaki ifPresent içerisindeki yapı nasıldı?
		* ifPresent(account -> account.withdraw(1_000))
		* Neden böyle oldu? Çünkü lambda expression içerisinde checked exception fırlatamam.
		* Dolayısıyla try-catch ile surround edildi. Aynı durum bir alttaki withdraw1k'da da geçerli.
		* Yani ben lambda expression içerisinde business bir hata fırlatacak kod yazamam yazacaksam eğer try
		* catch içerisinde yazmam gerekiyor. Bu durumu bu bloğu bir statik metod içerisine alarak aşabilirim.*/
		jack.getAccount("tr4").ifPresent(account ->
			/*{
			try {
				account.withdraw(1_000);
			} catch (InsufficientBalanceException e) {
				throw new RuntimeException(e);
			}
		}*/
			withdraw(account,1_000)
		);
		Consumer<Account> withdraw1k = account -> {
			try {
				account.withdraw(1_000);
			} catch (InsufficientBalanceException e) {
				throw new RuntimeException(e);
			}
		};

		// Consumer<Account> printAccount = account -> System.out.println(account);
		/*
		 * 81. satiri asagidaki gibi yazabilirim. Cunku zaten Account parametresini ben
		 * Optional icerisinde de parametre olarak verebiliyorum(esitligin sol tarafi
		 * durumu destekliyor). Ama buradaki ayni haraketi 78. satir icin yapamam cunku
		 * withdraw icerisinde ki degeri verebildigim baska bir yer yok. Eger parametre 
		 * vermem gerekiyorsa :: method reference operatorunu kullanimiyorum.
		 */
		Consumer<Account> printAccount = System.out::println;
		jack.getAccount("tr2").ifPresent(withdraw1k.andThen(printAccount));

		// Herhangi bir ifadeyi degiskene atama kisayolu alt + shift + l
		// <? super ...> ne anlama geliyor?

		// 80. satir geleneksel yontem karsiligi
		Optional<Account> acc = jack.getAccount("tr2");
		if (acc.isPresent()) {
			acc.get().withdraw(1_000);
			System.out.println(acc.get());
		}

		System.out.println("Total balance: " + jack.getBalance());
		System.out.println("Total balance8: " + jack.getBalance8());
	}

	private static void withdraw(Account account,double amount) {
		try {
			account.withdraw(1_000);
		} catch (InsufficientBalanceException e) {
			throw new RuntimeException(e);
		}
	}

}
