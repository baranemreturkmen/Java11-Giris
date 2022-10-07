package com.example.banking.domain;

public class Account {
	// Ben neye baktıgımda account'u tek turlu tanımlarım tekilligi saglarim.
	private final String iban;
	protected double balance;

	/*
	 * private, protected -> information hiding principle double balance -> default,
	 * default permission, package private package private deniyor cunku ayni
	 * pakette ki classlar default uyeye erisebiliyor. 4 tane access modifier var.
	 * public, private, protected, default.
	 */

	/*
	 * 2 class dusunelim. Bu 2 class arasinda kalitim iliskisi yok. Ama ayni
	 * paketteler. 1. class'in protected olan alani 2. class'da erisilebilir
	 * durumdadir cunku 2 class ayni pakette ise protected alanlar default olarak
	 * davranirlar. ->ONEMLI BILGI
	 */

	public Account(String iban, double balance) {
		this.iban = iban;
		this.balance = balance;
	}

	public String getIban() {
		return iban;
	}

	public double getBalance() {
		return balance;
	}

	// business methods

	/*Metod başarısız olduğunda return dönmek yanlış bir pratik. Ne dönüyor false dönüyor.(boolean -> double)
	* Başarısız olduğunda başarısızlığını açıklayan bir exception nesnesi ile metod dönsün istiyoruz.
	* Bir metoddan 2 şekilde çıkabiliriz. return ile -> başarılı durum ve throw ile -> başarısız durum.
	* throw ile döndüğüm nesne de hatayı açıklayan exception nesnesi.*/
	public final double deposit(double amount) {
		// validation
		//IllegalArgumentException java içerisinde yer alan Exception class'ına bağlı built-in bir exception nesnesi.
		if (amount <= 0.0) throw new IllegalArgumentException("Amount can not be negative!");

		//business logic
		this.balance = this.balance + amount;
		return this.balance;
	}
	/*Javada exception sınıflarını 2'ye ayırıyoruz.
	* 1. Unchecked Exceptions: RunTime Exception-> Bunları declare etmek zorunda değiliz.
	* Bir exception RuntimeException'ı extend ediyorsa Runtime Exception'dır.
	* 2. Checked Exceptions: Business Exception -> extends Exception(Runtime'ı extend etmez.)
	* Checked Exception varsa metodumda onu metodun imzasında throws ile declare etmem lazım.
	* , ile ayırarak birden fazla BusinessException'ı handle edebilirim.
	* Runtime exceptionlar tip 1 hatalara giriyor. Yani biz kötü kod yazdığımız için ortaya çıkan hatalar.
	* İdeal bir uygulamanın hiç bir zaman Runtime exception fırlatmaması gerekir. Dolayısıyla Runtime exceptionlara
	* biz bug gözüyle bakıyoruz. Runtime exceptionların ardında ki hataların bulunup giderilmesi gerekiyor. Bundan
	* dolayı metodlar Runtime exceptionları declare etmezler çünkü aslında fırlatmıyor olmaları gerekir. Bir metod
	* ancak ve ancak BusinessException, işin doğasında olan hataları engellenemeyecek hataları fırlatmalı. Çağıran taraf
	* bu hatayla karşılaştığı zaman bu hatayı handle edebilecek yapıya sahip olmalı.*/

	/*Metodun ilk halinde başarılı veya başarısız olma durumuna göre true veya false dönen yapıda
	* dönüş tipine göre bu metodun kullanıldığı yerde bir sürü if olacaktı. İşte true döndü bunu yap,
	* false döndü şunu yap tarzında ama artık hata fırlattığımız bu yapıda buna gerek kalmıyor çünkü
	* ben duruma göre ilgili hatayı asıl metodumun içerisinde kullanıcıya bildiriyorum. Bu şekilde kodu happy
	* path'e göre mutlu sonla bitecek şekilde yani bu metodu kullanacağım yapıda hiçbir adımda hata olmayacakmış
	* gibi kodumu yazıcam ve herhangi bir hata oluştuğu zaman o yapıda hatanın türüne hatayı handle eden try-catch
	* yapısını kullanacağız.*/
	public double withdraw(double amount) throws InsufficientBalanceException{
		// validation
		if (amount <= 0.0) throw new IllegalArgumentException("Amount can not be negative!");
		// business rule
		/*Business Exception -> Java içerisinde bulunan built-in bir exception nesnesi ile halledemem bu hata
		* benim iş kuralıma özel bir hata.*/
		if (amount > this.balance) throw new InsufficientBalanceException("Amount can not be larger than balance!"
				,amount-balance); //Açık miktarını da verdim amount-balance ile.
		// business logic
		this.balance = this.balance - amount;
		return this.balance;
	}

	@Override
	public String toString() {
		return "Account [iban=" + iban + ", balance=" + balance + "]";
	}

	// getterları secmenin kısa yolu alt + g

}
