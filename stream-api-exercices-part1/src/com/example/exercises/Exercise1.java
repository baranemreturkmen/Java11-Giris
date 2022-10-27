package com.example.exercises;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.domain.Director;
import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise1 {
	private static final MovieService movieService = InMemoryMovieService.getInstance();

	public static void main(String[] args) {
		// Find the number of movies of each director
        final Collection<Movie> movies = movieService.findAllMovies();

		//Her bir yönetmenin kaç tane film çektiğini bulmak istiyoruz.
		var numOfMoviesOfEachDirector = //Biz var diyoruz ama Compiler Map<Director,Long> dönüyor.
		movies.stream()						//Stream<Movie>
				.map(Movie::getDirectors)   //Stream<List<Director>>
				.flatMap(List::stream) 		//Stream<Director> flatMap yapılacak bu işleme flattening deniyor. Elimizde listeler var onları yan yatırıcaz stream of director elde edicez.
				.collect(Collectors.groupingBy(Function.identity(),Collectors.counting())); //Doğrudan direktöre göre gruplama yapıcaz identity yeterli, her grup için sayıyı da reduce edicez.

		/*Identity kullanmamazın sebebi şu yönetmeni yönetmen yapan şey nesnenin bellekteki adresi mantığıyla yola çıkıyoruz.
		* Şöyle bir durum da olabilirdi */

		var numOfMoviesOfEachDirector2 = //Compiler Map<String,Long> dönüyor bu sefer.
				movies.stream()
						.map(Movie::getDirectors)
						.flatMap(List::stream)
						.collect(Collectors.groupingBy(d -> d.getName(),Collectors.counting()));

		/*Ama aynı isimde başka directorlar da olabilirdi ve işler burada çok karışacaktı. Ve tabi ki başka özelliklere
		* göre de gruplama yapabilirim.*/

		var numOfMoviesOfEachDirector3 = //Compiler Map<String,Long> dönüyor bu sefer.
				movies.stream()
						.map(Movie::getDirectors)
						.flatMap(List::stream)
						.collect(Collectors.groupingBy(d -> d.getImdb(),Collectors.counting()));

		//Veya identity kullanmak yerine d -> d yazabilirim ama çirkin bir kullanım.

		/*HashCode kullanabilirim ama gruplayınca integer değer dönecek yani gruplamış oldu ama kim o
		  dolayısıyla identity kullanarak doğrudan director'un kendisine göre gruplamak daha mantıklı. Burada key value
		  yapıyorum aslında key Director, value Long ve ben Director key'i üzerinden birçok bilgiye ulaşabilirim.*/

		//Yukarıdaki ilk çözümü daha da kısaltabilirim.

		var numOfMoviesOfEachDirector4 = //Compiler Map<Director,Long> dönüyor bu sefer.
				movies.stream()
						//.map(Movie::getDirectors)
						.flatMap(movie -> movie.getDirectors().stream())//Yukarıdakine göre tek bir metodda Stream<Director>'a geçiyor.
						.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

		//Statik import yapıp yine kodları kısaltabilirim.

		//.collect(groupingBy(identity(),counting())) şeklinde.

		//key ve value'yu forEach ile döndürüyorum. key -> Director, value -> count
		numOfMoviesOfEachDirector.forEach((director, count) -> System.out.println(director.getName()+": "+count));

		Function<Director,String> byName = Director::getName;
		//Function<String,Director> byImdb = u -> ...; bu yapı olsaydı eğer kodlarda aşağıdaki gibi andThen kullanımı yapabilirdik.
		//Gruplama yap isme göre sonrada Imdb'ye göre grupla tarzında.

		//Böyle de bir kullanım mevcut.
		Function<Director,Director> byDirector = d -> d;

		/*var numOfMoviesOfEachDirector5 =
				movies.stream()
						//.map(Movie::getDirectors)
						.flatMap(movie -> movie.getDirectors().stream())
						.collect(Collectors.groupingBy(byName.andThen(byImdb),Collectors.counting()));*/
	}

}
