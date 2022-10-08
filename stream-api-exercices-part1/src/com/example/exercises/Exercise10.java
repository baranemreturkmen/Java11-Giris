package com.example.exercises;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.Country;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise10 {
	private static final WorldDao worldDao = InMemoryWorldDao.getInstance();
		
	public static void main(String[] args) {
		// Find the richest country of each continent with respect to their GNP (Gross National Product) values.
		//Dünyanın en zengin ülkesini bulmaya çalışıyoruz.
		worldDao.findAllCountries().stream()
				.max(comparing(Country::getGnp))
				.ifPresent(System.out::println);
		//optional döndüğü için ifPresent kullandım. Null dönme optional dön demiştik benim stream'inde hiç eleman yoksa.

		System.out.println("------------------------------");

		//En yüksek nüfuslu ülke.
		worldDao.findAllCountries().stream()
				.max(comparing(Country::getPopulation))
				.ifPresent(System.out::println);

		System.out.println("------------------------------");

		//Asya kıtasındaki ülkeleri bul. Ve nüfusu büyükten küçüğe sırala.
		worldDao.findAllCountries().stream()
				.filter(country -> country.getContinent().equals("Asia"))
				.collect(toList())
				.stream().sorted(comparing(Country::getPopulation).reversed())
				.forEach(System.out::println);

		System.out.println("------------------------------");

		/*max, ifPresent, filter, sorted, foreach, collect vs. bunlar benden function interface bekliyor.
		* En basitinden Comparator bir function interface*/

		Comparator<Country> compareByPopulation = comparing(Country::getPopulation);
		Consumer<Country> printCountry = System.out::println;
		Predicate<Country> asian = country -> "Asia".equals(country.getContinent());
		var compareByPopulationDesc = compareByPopulation.reversed();

		var allCountries = worldDao.findAllCountries().stream()
				.filter(asian)
				.collect(toList());

				allCountries.stream().sorted(compareByPopulationDesc)
				.forEach(printCountry);

		/*Comparator javada functional programmingden önce de vardı(Java 8'den önceden de vardı.).
		* İçerisinde compare diye abstract bir metod var.
		* Kısmi sıralama yapıyor mesela ilk parametre ikinci parametreden önce dönüyorsa pozitif dönüyor, tam tersi
		* durumda negatif dönüyor. Herhangi 2 elemanın önceliğini ve sonralığını tanımlamaya partial order kısmi
		* sıralama diyoruz. Sıralama yapmak isteyen algoritmaların partial order'a ihtiyacı var.*/

		/*Consumer Java 8 ile geldi built in function interfacelerden biri.
		* Aynı şekilde Predicate built in function interface.*/

		/*Bu şekilde daha okunabilir yaptık.*/

		/*toList statik bir metod sürekli Collectordan çekmek yerine import edebilirim.*/

		/*comparing statik bir metod Comparatordan sürekli çekmek yerine import edebilirim.*/

		/*Java 10'da gelen yeni bir özellik bir değikenin tipini vermek zorunda değilim. Zaten compiler
		* sağ tarafta değişkenin tipini anlayabiliyor(var kullanımı).*/

		/*Java statik type bir dil. Değişkenin metodun dönüş tipinin vs. compiler herşeyin type'ını bilmek istiyor.
		* Compiler yazılan koddan değişkenin type'ını vs. çıkarabilir. Bu durumda fonksiyonel programlamayı daha
		* rahat uygulamamıza sebeb oluyor.*/
	}

}
