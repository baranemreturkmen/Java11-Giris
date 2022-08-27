package com.example.random.service.business;

import com.example.random.service.RandomNumberService;

import java.security.SecureRandom;

public class SecureNumberService implements RandomNumberService {

    /*Secure ile kast edilen cryptographic anlamda güçlü bir sayı üretici kullanacağımızı belirtiyor.*/

    private SecureRandom random = new SecureRandom();

    @Override
    public int generate(int begin, int end) {
        System.out.println("SecureNumberService::generate");
        return random.nextInt(end-begin+1) + begin;
    }
}
