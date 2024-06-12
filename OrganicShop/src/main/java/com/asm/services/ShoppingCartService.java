package com.asm.services;

import java.util.Collection;
import java.util.List;

import com.asm.entity.Products;

//public interface ShoppingCartService {
//    Products add(Integer productId);
//    void remove(Integer productId);
//    Products update(Integer productId, String action);
//    void clear();
//    Collection<Products> getItems();
//    int getCount();
//    double getAmount();
//}
public interface ShoppingCartService {
    void add(Integer productId);
    Products update(Integer productId, String action);
    void remove(Integer productId);
    void clear();
    List<Products> getItems();
    int getCount();
    double getAmount();
}