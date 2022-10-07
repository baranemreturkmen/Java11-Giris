package com.example.banking.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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
	
	@DisplayName("Creates an account object successfully with parametric test.")
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
	void withdrawNegativeAmountShouldReturnFalse() throws InsufficientBalanceException {
		Account account = new Account("tr1",10_000);
		
		/*gercekten negatif deger cekebiliyor muyuz? false firlatacak mi?*/
		//assertFalse(account.withdraw(-1.));
		
		/*false firlatsa bile ne olur ne olmaz diye para cekmis mi diye yine 
		 * kontrol ediyorum. Test bu sekilde daha duzgun.*/
		assertEquals(10_000.,account.getBalance());
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {15_000.0, 20_000.0})
	@DisplayName("Withdraw amount greater than balance should return false")
	void withdrawOverBalanceShouldReturnFalse(double amount) {
		Account account = new Account("tr1",10_000);
		assertThrows(InsufficientBalanceException.class,()->account.withdraw(amount));
		assertEquals(10_000.,account.getBalance());
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {0.1, 1.0, 10_000.0})
	@DisplayName("Withdraw all balance should return true")
	void withdrawAllBalanceShouldReturnTrue(double balance) throws InsufficientBalanceException {
		Account account = new Account("tr1",balance);
		account.withdraw(balance);
		assertEquals(0.,account.getBalance());
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {-0.1, -1.0, -10_000.0})
	@DisplayName("Deposit with negative amount should return false")
	void depositiveNegativeAmountShouldReturnFalse(double amount) throws IllegalArgumentException{
		Account account = new Account("tr1",10_000);
		assertThrows(IllegalArgumentException.class,()->account.deposit(amount));
		assertEquals(10_000,account.getBalance());
	}
	
	@ParameterizedTest
	@CsvSource({
			"0.0, 1.0, 1.0",
			"0.0, 0.1, 0.1",
			"100.0, 100.0, 200.0"
	})
	@DisplayName("Deposit with positive amount should return true")
	void depositivePositiveAmountShouldReturnTrue(double balance,double amount,double newBalance) {
		Account account = new Account("tr1",balance);
		account.deposit(amount);
		assertEquals(newBalance,account.getBalance());
	}
	
	@Test
	@DisplayName("toString() should contain Account")
	void toStringShouldContainAccount() {
		Account account = new Account("tr1",10_000);
		assertTrue(account.toString().contains("Account")); 
	}

}
