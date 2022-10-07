package com.example.banking.domain;

public class InsufficientBalanceException extends Exception {

    //Exception üyeleri değişmemeli best practice bu o yüzden final tanımladık.
    private final double deficit;

    public InsufficientBalanceException(String message, double deficit) {
        super(message);
        this.deficit = deficit;
    }
    /*Sadece açıklayan yapılar olmalı. Hatayı çözme işine girmemeli. Sadece içerisinde veriyi barından bir sınıf olmalı
    * başka işlere girmemeli.*/
    public double getDeficit() {
        return deficit;
    }
}
