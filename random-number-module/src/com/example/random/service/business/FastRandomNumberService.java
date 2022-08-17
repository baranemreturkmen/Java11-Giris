package com.example.random.service.business;

import com.example.random.service.RandomNumberService;

import java.util.concurrent.ThreadLocalRandom;

public class FastRandomNumberService implements RandomNumberService {
    @Override
    public int generate(int begin, int end) {
        return ThreadLocalRandom.current().nextInt(begin,end);
    }
}
