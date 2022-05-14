package com.example.banking.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/*Test raporuna isimlendirme yapıyorum default olarak class ve metod ismini 
  Veriyor junit*/
@DisplayName("Functional test for Account class.")
class AccountTest {
	
	@DisplayName("Creates an account object successfuly.")
	@Test
	void createAccount() {
		/* Testin 4 aşaması var.
		 * 1. Test Setup/Fixture'ını oluşturma.
		 * 2. Call exercise method -> Test ettigim metodu calistirmaca
		 * 3. Verification -> Dogru calisti mi?
		 * 4. Teardown -> Birinci hamlede yaptigimiz seyin etkisini ortadan
		 * kaldirmak istiyoruz.
		 * */
		
		/*1. ve 2. -> CreateAccount testi nesneyi duzgun yapilandiryor muyum
		  testi oldugu icin 1 ve 2 ayni adimda.*/
		Account acc = new Account("tr1",10_000);
		
		//3.
		assertEquals("tr1",acc.getIban());
		assertEquals(10_000,acc.getBalance());
		
		/*4. -> Bu ornek icin yapilacak birsey yok. Test senaryosunda dosya acma
		 * gibi bir durum olsaydi mesela dosya kapatma isini burada yapacaktik.*/
				
		
	}
	
	@DisplayName("Creates an account object successfuly with parametric test.")
	@ParameterizedTest //-> Anatasyon birer metadata olarak dusunebilirsin.
					   //Bir nevi post-it aslinda junit okurken bu metodu parametrik test yapacagini biliyor mesela.
					   //Declarative programming. Java 5 ile gelmis. Loosly coupling uygulama ile framework arasinda ki
					   //gevsek bagi anatasyonlar ile sagliyoruz. junit 4 ile gelmis junit'e.
	@CsvFileSource(resources = "/accounts.csv")
	void createAccount2(String iban,double balance) {
		
		/*1. ve 2. -> CreateAccount testi nesneyi duzgun yapilandiryor muyum
		  testi oldugu icin 1 ve 2 ayni adimda.*/
		Account acc = new Account(iban,balance);
		
		//3.
		assertEquals(iban,acc.getIban());
		assertEquals(balance,acc.getBalance());
		
		/*4. -> Bu ornek icin yapilacak birsey yok. Test senaryosunda dosya acma
		 * gibi bir durum olsaydi mesela dosya kapatma isini burada yapacaktik.*/
				
		/* Burada createAccount2 3 defa calisacak cunku 3 tane satir vardi
		 * benim csv dosyamda. Burada metoda gecilen parametre sirasi ile
		 * csv dosyasinda ki parametre sirasi ayni olmali. Yoksa yanlis 
		 * mapleme yaparsin hata alirsin. Tabi csv dosyasinin hepsini 
		 * kullanmak zorunda degilim ama siralama onemli.*/
	}
	
	@Test
	@DisplayName("Withdraw negative amount should return false")
	void withdrawNegativeAmountShouldReturnFalse() {
		Account account = new Account("tr1",10_000);
		
		/*gercekten negatif deger cekebiliyor muyuz? false firlatacak mi?*/
		assertFalse(account.withdraw(-1.)); 
		
		/*false firlatsa bile ne olur ne olmaz diye para cekmis mi diye yine 
		 * kontrol ediyorum. Test bu sekilde daha duzgun.*/
		assertEquals(10_000.,account.getBalance());
	}
	
	@Test
	@DisplayName("Withdraw amount greater than balance should return false")
	void withdrawOverBalanceShouldRetunFalse() {
		Account account = new Account("tr1",10_000);
		assertFalse(account.withdraw(10_001)); 
		assertEquals(10_000.,account.getBalance());
	}
	
	@Test
	@DisplayName("Withdraw all balance should return true")
	void withdrawAllBalanceShouldRetunTrue() {
		Account account = new Account("tr1",10_000);
		assertTrue(account.withdraw(10_000)); 
		assertEquals(0.,account.getBalance());
	}
	
	@Test
	@DisplayName("Deposit with negative amount should return false")
	void depositeveNegativeAmountShouldRetunFalse() {
		Account account = new Account("tr1",10_000);
		assertFalse(account.deposit(-1)); 
		assertEquals(10_000,account.getBalance());
	}
	
	@Test
	@DisplayName("Deposit with negative amount should return true")
	void depositeveNegativeAmountShouldRetunTrue() {
		Account account = new Account("tr1",10_000);
		assertTrue(account.deposit(1)); 
		assertEquals(10_001,account.getBalance());
	}
	
	@Test
	@DisplayName("toString() should contain Account")
	void toStringShouldContainAccount() {
		Account account = new Account("tr1",10_000);
		assertTrue(account.toString().contains("Account")); 
	}

}
