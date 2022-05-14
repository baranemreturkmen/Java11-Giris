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

	public boolean deposit(double amount) {
		// validation
		if (amount <= 0.0)
			return false;
		this.balance = this.balance + amount;
		return true;
	}

	public boolean withdraw(double amount) {
		// validation
		if (amount <= 0.0)
			return false;
		// business rule
		if (amount > this.balance)
			return false;
		this.balance = this.balance - amount;
		return true;
	}

	@Override
	public String toString() {
		return "Account [iban=" + iban + ", balance=" + balance + "]";
	}

	// getterları secmenin kısa yolu alt + g

}
