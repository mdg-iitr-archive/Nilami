package com.crazyheads.nilami;

public class ProductListItem {

    private String product_name;
    private String min_price;
    private String auction_date;

    public ProductListItem(String product_name, String min_price, String auction_date) {
        this.product_name = product_name;
        this.min_price = min_price;
        this.auction_date = auction_date;
    }

    public String getMin_price() {
        return min_price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getAuction_date() {
        return auction_date;
    }
}
