package com.pritle.assignment1.domain;

/**
 * © 2015 Pritle Holding B.V. - All Rights Reserved
 * <p>
 * See LICENSE file or http://pritle.com for further details
 *
 * @author Timofey Tsymlov <dev@pritle.com>
 * @version 01/02/2017
 */
public class QuoteCollection {

    private String name;
    private String symbols;

    public QuoteCollection() {
    }

    public QuoteCollection(String name, String symbols) {
        this.name = name;
        this.symbols = symbols;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbols() {
        return symbols;
    }

    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }
}
