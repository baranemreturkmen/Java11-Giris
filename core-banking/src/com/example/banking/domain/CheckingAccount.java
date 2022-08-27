package com.example.banking.domain;

//Account -> Super class/Base Class
//CheckingAccount -> Sub-class/Derived Class
public class CheckingAccount extends Account {

	private double overdraftAmount;

	public CheckingAccount(String iban, double balance, double overdraftAmount) {
		super(iban, balance);
		this.overdraftAmount = overdraftAmount;
	}

	public double getOverdraftAmount() {
		return overdraftAmount;
	}

	public void setOverdraftAmount(double overdraftAmount) {
		this.overdraftAmount = overdraftAmount;
	}

	/*
	 * extend ettigim class'in alanlari private ise eger o class'a ait alanlara
	 * sub-class uzerinden de ulasamam. Ulasmak istiyorsam eger o alanlari protected
	 * yapmaliyim. public yaparsam her yerden erisim. Ama private yaparsam ilgili alana
	 * base ve sub-class'lar uzerinden erisemem.
	 */

	@Override
	public boolean withdraw(double amount) {
		// TODO Auto-generated method stub
		//validation
		if(amount <= 0.0) return false;
		//business rule
		if(amount > (balance + overdraftAmount)) return false;
		//business logic
		balance = balance - amount;
		return true;
	}

	/*
	 * Method overloading: Sınıf icerisinde ki metodlar arasinda gerceklesiyor.
	 * Method overridig: Aralarinda kalitim iliskisi olan metodlar arasinda
	 * gerceklesiyor. Signature ayni olacak.
	 */

	/*
	 * CheckingAccount Account'u, Account ise Object sinifini extend ediyor.
	 * Dolayisiyla kalitim hiyerarsinde CheckingAccount Object'den gelen toString
	 * vs. diger metodlara sahip. Javada bir class sadece bir class'i extend
	 * edebilir diye baska bir class'i extend eden bir class object class'inin
	 * saglamis oldugu metodlardan faydalanamiyor diye birsey soz konusu degil.
	 */

	/*
	 * Kalitim ile Account'un sahip oldugu alanlar ve metodlar CheckingAccount'a
	 * aktariliyor. Kalitimla aktarilmayan tek sey constructor'dir. Cunku
	 * constructorlar ozel metodlardir sinifin uyelerini initialize ederler.
	 * Account'un constructor'i yalnizca Account'a ait alanlari initialize edebilir.
	 */
}
