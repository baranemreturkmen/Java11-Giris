package com.example.banking.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.ToDoubleFunction;

//Alt + Shift + S: Generate Source Code 
//Ctrl + Shift + f kodu formatla
//Ctrl + Shift + o importları toparlar. Gerekli olanlari ekler. Gereksizleri siler.

/*public class Customer extends Object gerçekte compile eden kod bu!
  Object sınıfı içerisinde built in metodlar var utility metodları
  toString()'de bu metodlardan biri.
 */
public class Customer {
	// Class -> i. attribute, ii. behaviour -> method
	// Musteriye ait duragan seyler
	private final String identity; // attribute/state/data
	private String fullName;
	private final int birthYear;
	private String email;
	private String sms;

	// One to one iliski oldu boyle. Ben one to many istiyorum. Bir customer birden
	// fazla account'a sahip olabilir.
	// private Account account;

	// One to many. Generics kullaniyorum. Java 5 ile geldi generics.
	// Sag tarafta ki class declare etme mecburiyeti java 7 ile beraber gitmis.
	private List<Account> accounts = new ArrayList<>();

	/*
	 * Bir attributte final tanımlandıysa eger o attiribute'u constructor'da
	 * inintialize ettikten sonra degistiremezsiniz. Kısaca sabit tanımlamis oldum
	 * aslinda.
	 */

	public Customer(String identity, String fullName, int birthYear, String email, String sms) {
		this.identity = identity;
		this.fullName = fullName;
		this.birthYear = birthYear;
		this.email = email;
		this.sms = sms;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public String getIdentity() {
		return identity;
	}

	public int getBirthYear() {
		return birthYear;
	}

	// Business metodlari ekliyorum.
	public void addAccount(Account account) {
		accounts.add(account);
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	// Method Overloading

	/*
	 * Optional wrapper class. Sonucu wrap ediyor. Sonucun tipini de Account olarak
	 * verdik.
	 */
	public Optional<Account> getAccount(int index) {
		return Optional.of(accounts.get(index));
	}

	// Method Overloading
	public Optional<Account> getAccount(String iban) {
		for (Account acc : accounts) { // for-each
			if (acc.getIban().equals(iban))
				return Optional.of(acc);
		}
		return Optional.empty();
	}

	/*
	 * Object'den gelen toString metodunun kendisi sadece bellekteki adresi bizwe
	 * veriyordu Biz bu durumu override ettik.
	 */
	@Override
	public String toString() {
		return "Customer [identity=" + identity + ", fullName=" + fullName + ", birthYear=" + birthYear + ", email="
				+ email + ", sms=" + sms + "]";
	}

	// Customer'a ait davranıslar var
	// Constructor -> method(ozel bir method) -> initializes attributes

	// Klasik yontemle yazdik.
	public double getBalance() {
		double balance = 0.0;
		for (Account account : accounts)
			balance = balance + account.getBalance();
		return balance;
	}

	// Java8 ile yazdik.
	public double getBalance8() {
		/*
		 * Stream'den hesap objeleri geliyor. Sonrasında bu objeleri Account'un
		 * getBalance metoduna mapliyorum. Java8 ile gelen stream api sayesinde mapleme
		 * ve filtreleme islemi yapiyorum. sum bir reduce islemi.
		 */
		ToDoubleFunction<Account> accountToBalance = Account::getBalance;
		return accounts.stream().mapToDouble(accountToBalance).sum();

		/*
		 * Account::getBalance = Account -> account.getBalance() gereksiz yere lambda
		 * expression yazmaktan kurtariyor bizi. Yukarida genel anlamda yapilan sey su
		 * stream api ile gelen Account objelerini mapToDouble ile double donusturuyorum
		 * bu islemi yapabilmek icinde Account sinifinin getBalance metodunu
		 * cagiriyorum. ::-> yeni bir operator method reference java8 ile beraber bir
		 * sinifin fonksiyonunu referans edebiliyorum.
		 */

		/*
		 * Yukarida mapToDouble dedik hem de getBalance double donmesine ragmen neden
		 * sadece map demedik? Cunku sum map icin kullanilamiyor. Ben sadece map dersem
		 * eger map'den donen objenin turu stream objesi olur. mapToDouble dersek eger
		 * mapToDouble donen objenin turu artik stream olmaz Double olur.
		 */

		/*
		 * Yukarida ki gibi yazmak yerine boyle de yapabiliriz. Soyle bir tane reduce
		 * fonksiyonu yazmak zorunda kaliyoruz. (s,b)->s+b
		 */
		// return accounts.stream().map(Account::getBalance).reduce(0.0,(s,b)->s+b);

		// Veya reduce ile hic fonksiyon yazmadan boyle de yapabilirim.
		// return accounts.stream().map(Account::getBalance).reduce(0.0,Double::sum);

		/*
		 * Genel hal aslinda filter->map->reduce filtre Account'u alir true donerse
		 * stream'den atmaya devam ediyor. Filtreden gecen Accountlari bakiye ile
		 * mapledim bakiyeye donusturdum. Sonra sonucu ben tek bir degere indirgemek
		 * istiyorum buna reduce fonksiyonu diyoruz. Burada reduce fonksiyonumuz sum
		 * fonksiyonu. 0.0 baslangic degeri, Double::sum = (a,balance) -> a+balance,
		 * a->accumulator toplayici (a,balance) -> a+balance kac tane account varsa
		 * filtreden gecen o kadar cagrilacak ilk cagrildiginda, a degeri 0 olacak,
		 * balance ilk account'un sahip oldugu bakiye olacak. 10_000 olsun ilk hesap. 2.
		 * cagrilmada a=0.0 + 10_000'den a'da artik 10_000 var. 2. hesapta 20_000 var.
		 * bu durumda a=10_000 + 20_000'den a'da 3. cagrilma basinda a 30_000 olacak.
		 * calisma sekli bu sekilde. Sonuc olarak tum hesaplara karsilik tek bir deger
		 * donmus oluyor.
		 */
		/*return accounts.stream().filter(account -> account.getBalance() > 0.0).map(Account::getBalance).reduce(0.0,
				Double::sum);*/
		
		/*map yerine mapToDouble deyince hazır fonksiyonum var zaten o da sum. Tabi bu sum'in
		  icerisinde ozel bir reduce fonksiyonu vardir.*/
		
		/*Yukarida ki cozumler tek bir thread uzerinden seri calisiyor. Bunun yerine 
		 * paralel calisabiliriz, multithread calisabiliriz fakat multithread uygulamasi
		 * zor ve karmasik ustelik hata cikarsa eger ortaya cikan hatayi bulmakta zor.
		 * ekleyecegim parallel ifadesi ile multithread'in bu zorluklarina girmeden bu cozumu
		 * paralel calistiriabilirim. Parallel calisma demek multicore calisma demek. Buna 
		 * vertical scaling de deniyor. 10 cekirdekten 100 cekirdege gectin mesela bilgi islem
		 * kapasitesinin artisindan faydalaniyor. Tabi parallel kullanima degecek kadar veri
		 * olmali milyonlarca veri olmasi lazim.*/
		
		/*return accounts.stream().parallel().filter(account -> account.getBalance() > 0.0).map(Account::getBalance).reduce(0.0,
		Double::sum);*/
	}
}

/*
 * class A{} class B{} class C{}
 */

/*
 * Javada dosya ismi ile class ismi aynı oluyor. Bir dosya içersinde birden
 * fazla class olabiliyor fakat public olan class dosya ismini verir. Bu
 * dosyanın ismi Customer.java. Birden fazla public class olursa zaten derleme
 * zamanında hata alırız!
 */
