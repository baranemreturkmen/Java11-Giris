package com.example;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public class StudyFilterMapReduce {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numbers = List.of(4,8,15,16,23,42);

		/*Built in functional intterfaceler.-> Predicate, Function, BinaryOperator vs. bunlardan
		* bazilari. Kendi ihtiyaclarim icinde functional interfaceler olusturabilirim.*/

		Predicate<Integer> isEven = x -> x%2 == 0;

		/*
		Aslında yapı boyle ama biz kısa yazmayı tercih ediyoruz.
		Function<Integer,Integer> squared = (Integer y) -> {return y*y;};
		*/

		Function<Integer,Integer> squared = y -> y*y;
		BinaryOperator<Integer> accumulate = (acc,v) -> acc + v;

		/*filter bizden bir fonksiyon bekliyor. Fonksiyon bekledigini suradan anliyorum.
		 * Cunku icerisinde parametre olarak built-in bir interface var predicate.
		 * Functional bir interface. java.util.function paketinde yer aliyor. Generic
		 * metot herhangi bir tip alip boolean donecek. Bu sekilde filtreleme yapiyor.
		 * Map'de bizden bir fonksiyon bekliyor. map'e filtre sonucunda donen degerlere
		 * uygulamak istedigimiz fonksiyonu veriyoruz. sonrasında bu fonksiyondan donen
		 * degerleri tek bir degere indirgemek amaciyla reduce kullaniyoruz. reduce
		 * fonksiyonu da bizden bir fonksiyon bekliyor. Benden filter, map ve reduce'da
		 * fonksiyon bekledi. Ben bir degiskenin tipini bir fonksiyonun  giris parametresini
		 * donus tipini fonksiyon olarak ifade edebiliyorum ve bu fonksiyonları functional
		 * interfaceler ile ifade edebiliyoruz. Bu olaya da fonksiyonel programlama deniyor.*/

		int sum = numbers.stream().filter(isEven).map(squared).reduce(0,accumulate);

		System.out.println("sum = "+sum);

		/*Fonksiyonel programlamayi biraz da multi core programlamayi kullanabilme acisindan
		* kullanıyoruz. Fonksiyonel programlama yogun olarak stream api kullanmam lazim.
		* Stream api'da koleksiyonlar ile beraber calisiyor. Koleksiyonlar ise birden fazla
		* veriyi bellekte organize etmememi sagliyor. Bu verilerin miktari cok ise islemleri
		* paralellestirmem isimi hizlandiracaktir. Ama fonksiyonel programlamanin tek avantaji
		* bu degil. Geleneksel yontemler sadece cozum odakliydi. Fonksiyonel programlama ile koda
		* baktigimiz zaman programcinin amacinin ve cozumunun ne oldugu net bir sekilde okunabiliyor.
		* Daha okunabilir anlasilir kodlar yazabiliyoruz.*/

		/*Stream api ile uyguladigimiz paralellik data paralellizm cunku collection yapilari liste gibi
		* vs. yapilar uzurinde calisiyor.*/

		//Geleneksel yontemle ayni cozumu yapalim.
		sum = 0;
		for(int number: numbers){
			if(number%2 == 0){
				sum = sum + number * number;
			}
		}

		System.out.println("sum = "+sum);
	}


}
