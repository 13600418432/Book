package com.zqx.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();

    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            getItems().put(cartItem.getId(),cartItem);
        }
        else {
            item.setCount(item.getCount()+1);
            item.setPriceTotal(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public void deleteItem(Integer id){
        items.remove(id);
    }

    public void clear(){
        items.clear();
    }

    public void updateCount(Integer id,Integer count){
        CartItem item = items.get(id);
        if (item != null) {
            item.setCount(count);
            item.setPriceTotal(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getPriceTotal());
        }
        return totalPrice;
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalPrice=" + getTotalPrice() +
                ", totalCount=" + getTotalCount() +
                ", items=" + items +
                '}';
    }
}
