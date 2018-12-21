package msk.android.academy.javatemplate.model;

import java.util.ArrayList;
import java.util.List;

public class DataProduct {
public static List<Product> generateProducts(){
    List<Product> listOfProducts = new ArrayList<>();
    listOfProducts.add(new Product("5901234123457","120","Coca-Cola"));
    listOfProducts.add(new Product("123456789104","110","Lipton"));
    listOfProducts.add(new Product("4607086410639","100","Sprite"));
    return  listOfProducts;
}


//    listOfProducts.
//        lostOfProducts
//
//        return news;
//    }
//
//    private static Date createDate(int year, int month, int date, int hrs, int min) {
//        return new GregorianCalendar(year, month - 1, date, hrs, min).getTime();
//    }
}
