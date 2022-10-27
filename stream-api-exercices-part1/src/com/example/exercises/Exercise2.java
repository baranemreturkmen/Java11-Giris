package com.example.exercises;

import com.example.dao.CountryDao;
import com.example.dao.InMemoryWorldDao;
import com.example.domain.Country;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise2 {
	private static final CountryDao countryDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Find the most populated country of each continent

		var mostPopulatedCityOfEachContinent =
				countryDao.findAllCountries()
						  .stream()
						  .collect(Collectors.groupingBy(Country::getContinent
								,Collectors.maxBy(Comparator.comparing(Country::getPopulation))));

		//Yukarıda bir liste dönmüyor maxBy ile reduce ediyorum. Sadece en buyuk olani bana ver diyorum.

		mostPopulatedCityOfEachContinent
				.forEach((continent, country)
						-> country
						.ifPresent(ctry -> System.out.println(continent+": "+ctry.getName())));

		//Yada direk tüm bilgileri sayısal olarak görmek istersem böyle de yazdırabilirim yukarıda lambda expressionla uğraşmadan
		System.err.println(mostPopulatedCityOfEachContinent);

		System.out.println("------------------------------");

		//Kıtalardaki tüm ülkeleri yazdıralım. Kıtalardaki ülkeler liste olarak dönecek. Herbir liste elemanı ülkeye ait bilgileri içinde bulunduracak.

		//???Burada neden sadece ülkeye ait name ve population dönüyor listelerin içerisinde ülkeye ait bir sürü özellik var neden böyle anlamadım???
		countryDao.findAllCountries()
				.stream()
				.collect(Collectors.groupingBy(Country::getContinent))
				.forEach((continent, country)
						-> System.out.println(continent+": "+country));

		//Tüm asya ülkelerini bul.
		var asianCountries = countryDao
				.findAllCountries()
				.stream()
				.filter(country -> "Asian".equals(country.getContinent()))
				//.toList() özel bir reduce işlemi Java16 ile geldi.
				.collect(Collectors.toList());

		/*Java8 geldi tüm sorunları functional programming ile çözmeliyim diye birşey yok. Bazı durumlarda klasik for
		* döngüsüyle iflerle sorunları daha kolay çözebilirim. Java'da fonksiyonel proglamlamanın diğer programlama
		* dillerine göre bir farkı var o da şu tamamen multi core programlama yapabilirim. Multi core'da thread programlamanın
		* karmaşıklığı ile uğraşmadan problemi çözebiliriz. Hiç paralel programlama bilmeden multi core çalışabiliyorum stream api
		* bize bu konuda çözüm sunabiliyor. Bundan dolayı çözümlerimi klasik for loop ile yaparken paralelleştirme bu kadar kolay
		* olmayacak. Tabi stream api paralelleştirme türü olarak data paralellizm ise bir çözüm sunuyor. Farklı paralelleştirme
		* yöntemleri var. Elimde bir collection api varsa collection api'deki veriler varsa problemim data paralellizm türüne
		* giren bir problemdir. Onun dışında başka task paralellizm vs. gibi farklı paralellizm türleri var. Collection api,
		* stream api bize data paralellizm sağlıyor. Bunun alt yapısını sunuyor. Alt yapısı Java7'de geldi. ForkJoinPool dediğimiz
		* Java7 ile gelen alt yapı sayesinde parallel programlamayı stream api ile kolay bir şekilde yapabiliyoruz.*/
	}

}