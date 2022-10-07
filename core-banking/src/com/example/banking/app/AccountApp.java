package com.example.banking.app;

import com.example.banking.domain.Account;
import com.example.banking.domain.InsufficientBalanceException;

public class AccountApp {

	public static void main(String[] args) {
		/*Veya try cacth yerine metod imzasında throws InsufficientBalanceException kullanabilirim.*/
		// TODO Auto-generated method stub
		Account acc1 = new Account("tr1",100_000.0);
		try{
			/*Burası happy-path. Bu path'de herhangi bir nokta başarısız olursa bir exception fırlatılacak ve
			* bu exception try'ı izleyen catch'lerden birinde catch'e verdiğimiz hataya göre akış dahilinde
			* ilerleyecek.*/
			acc1.withdraw(75_000);
			acc1.deposit(10_000);
			acc1.withdraw(50_000);
		}
		catch(InsufficientBalanceException e){
			System.out.println(e.getMessage());
			System.out.println("Deficit: "+e.getDeficit());
			//throw e; //Hatayı fırlatırken de metod imzasında throws ile declaration yapıyoruz.
			/*Aslında problemi çözecek kodu catch içerisinde yazıyoruz. Her zaman problemi çözemeyiz. Bazen burada
			* yapıldığı hatayı loglarız ve çağıran hatayı throw ederiz. Buna da rethrow yapmak deniyor.
			* throw e -> rethrow
			* Pek mantıklı bir hareket değil çünkü uygulama sonlanıyor.*/
		}
		catch(IllegalArgumentException e){
			/*Bu catch'i yazmak zorunda değilim. Compiler Runtime exceptionlarımın catch edilip edilmediğini
			* kontrol etmiyor.*/
			System.out.println(e.getMessage());
		}

		System.out.println(acc1.getBalance());
	}

}
