package com.pritle.assignment1.domain;

import java.math.BigDecimal;

/**
 * © 2015 Pritle Holding B.V. - All Rights Reserved
 * <p>
 * See LICENSE file or http://pritle.com for further details
 *
 * @author Timofey Tsymlov <dev@pritle.com>
 * @version 01/02/2017
 */
public class Quote {

    private String symbol;
    private BigDecimal price;

    public Quote() {
    }

    public Quote(String symbol, BigDecimal price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
