package com.example;

public class WhatIsFunctionalProgramming {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* Value -> Primitive Types -> int, double, float, ... */
		int x = 42;

		/* Class -> String -> Reference Variable */
		String y = "jack";

		/*
		 * Java8'den itibaren degiskeni fonksiyon olarak tanimlayabilirim. Bir
		 * fonksiyona parametre olarak fonksiyon gecebilirim. Bir fonksiyondan parametre
		 * olarak fonksiyon dondurebilirim. Kisacasi fonksiyonlari primitive tipler gibi
		 * class gibi fonksiyonu kullanabiliyorum. Farkli yontemler var.
		 */
		/*
		 * 1. Anonymous Class: Fun interface'ini implemente eden bir class. Bu class'a
		 * isim vermek istemiyorum cunku bir tane kopya kullanacagiz. Baska bir
		 * instance'ini yaratmayacagim. O zaman isim vermek yerine isimsiz bir class
		 * olusturup o classdan da nesne yaratiyoruz. Yani aslinda ortada bir isimsiz
		 * class var Fun interface'ini de implemente ediyor. Bu class'a isim vermiyoruz
		 * cunku bu class'dan sadece bir tane nesne yaratacagiz baska nesne
		 * yaratmayacagimiz. Yaratmis oldugumuz o bir nesne fonksiyonel interface'de ki
		 * metodu degiskene atamamiza yardimci olacak. Zaten sol tarafta degiskenin
		 * tipini FunctionalInterface olan Fun ile belirledim. Olay genel olarak bu.
		 */
		Fun z1 = new Fun() {

			@Override
			public int f(int x) {
				// TODO Auto-generated method stub
				return x * x * x;
			}

		};

		System.out.println(z1.f(2));

		/*
		 * Bir tane metod olduguna gore o metodun ismini vermeme gerek yok. Yukarida ki
		 * gibi bu kadar isleme de gerek yok ayni isi lambda expression ile yapabilirim.
		 * Lambda expression dedigimiz sey yukarida ki anonymous class'in kisa
		 * gosterimi. Solda fonksiyona ait aldigi parametreyi verdim. Sagda fonksiyon
		 * govdesini verdim.
		 */
		Fun z2 = (int u) -> {
			return u * u * u;
		};

		System.out.println(z2.f(3));

		/*
		 * Daha da yalin hale getirebilirim lambda expression'i. z3'de int'e gerek yok.
		 * z3 degiskeninin tipi zaten Fun ve bu interface'de tek bir metod var ve o
		 * metodun aldigi parametrenin tipi belli. z4'de metot tek parametreli bir metod
		 * ise () çiftine gerek yok. z5'de metot govdesinde sadece return ifadesi var
		 * ise eger {} çiftine gerek yok.
		 */
		Fun z3 = (u) -> {return u*u*u;};
		Fun z4 = u -> {return u*u*u;};
		Fun z5 = u -> u*u*u;//Tabi arkada anonymous class formatinda calisiyor.
		
		/*3. yontem method reference :: operatoru.*/
		Fun z6 = Gun::sun;//static metodu assing ettim degiskene.
		A a = new A();
		Fun z7 = a::run;//nesneye ait bir metodu assign ettim degiskene.
		
		/*Best practise var olan yazili bir metot varsa eger herhangi bir class'ta
		 * o metodu kullanmak. Yoksa da lambda expression kullanmak.i
		 * OOP temelinde class var.
		 * Fuctional Programming temelinde java icin functional interface var.*/
		
	}

}

/*3. yontem icin class olusturulmasi gerekiyor. Olusturulan classlarda ki metotlarda ki
 * signaturelar ile Fun fonksiyonel interface'inde ki metodun signature'i uyusmasi yeterli.*/
class A{
	public int run(int v) {
		return v*v;
	}
}

class Gun{
	public static int sun(int y) {
		return y*y*y*y;
	}
}

/*
 * Bir tane metod olur functional interfacelerde cunku compiler functional
 * interfacede ki metodun aldigi parametrelere ve donus tipine bakacak.
 */
@FunctionalInterface
interface Fun {
	int f(int x); // SAM: Single Abstract Method
}
