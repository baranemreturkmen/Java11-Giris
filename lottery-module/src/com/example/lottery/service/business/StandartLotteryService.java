package com.example.lottery.service.business;

import com.example.lottery.service.LotteryService;
import com.example.random.service.RandomNumberService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StandartLotteryService implements LotteryService {

    /*Her bir proje modüldür. Ve bu modül SRP'den dolayı nasıl rastgele sayı üretmesi gerektiğini bilmiyor.
    * RandomNumberModule'den yardım alması gerekiyor.*/

    /*Default'da modül içerisinde ki herşeyi gizlediği(encapsulation) için dependency'e kullanmak istediğim modülü eklemem
    * importlamam yetmiyor. RandomNumberModule'de RandomNumberService'in içinde olduğu paketi açmam gerekiyor bu sorunu
    *  aşabilmek için buna da export işlemi deniyor.*/

    /*Yani bir görmesi normal build path'e ekleme işlemi var bir de modülü görmesi için export ediyoruz. Tabi lottery
    * modülde Random modülde export edilen modülü requires diye ekliyoruz.*/

    private RandomNumberService randomNumberService;

    public void setRandomNumberService(RandomNumberService randomNumberService) {
        this.randomNumberService = randomNumberService;
    }

    @Override
    public List<Integer> draw(int max, int length) {
        return IntStream.generate(() -> randomNumberService.generate(1,max))
                .distinct()
                .limit(length)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public List<List<Integer>> draw(int max, int length, int row) {
        return IntStream.range(0,row).mapToObj(i -> this.draw(max, length))
                .collect(Collectors.toList());
    }

}
