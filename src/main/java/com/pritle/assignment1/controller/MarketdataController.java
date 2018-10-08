package com.pritle.assignment1.controller;

import com.pritle.assignment1.dao.QuoteCollectionDao;
import com.pritle.assignment1.dao.QuoteDao;
import com.pritle.assignment1.domain.Quote;
import com.pritle.assignment1.domain.QuoteCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * © 2017 Pritle Holding B.V. - All Rights Reserved
 * See LICENSE file or http://pritle.com for further details
 *
 * @author Timofey Tsymlov <dev@pritle.com>
 * @version 01/02/2017
 */
@RestController
@RequestMapping(produces = "application/json; charset=UTF-8")
public class MarketdataController {

    private static final Logger log = LoggerFactory.getLogger(MarketdataController.class);

    @Autowired
    private QuoteDao quoteDao;

    @Autowired
    private QuoteCollectionDao quoteCollectionDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @RequestMapping(value = "/quote/{symbol}", method = GET)
    public ResponseEntity<?> getQoute(@PathVariable String symbol) {
        log.debug(">>>>>> getQuote");
        Quote quote = quoteDao.find(symbol);
        if (quote != null) {
            return ResponseEntity.ok(quote);
        } else {
            return new ResponseEntity<>("{}", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/collection/{name}", method = POST)
    public void createOrUpdateCollection(@PathVariable String name, @RequestParam String symbols) {
        log.debug(">>>>>> createOrUpdateCollection");
        QuoteCollection quoteCollection = quoteCollectionDao.find(name);
        if (quoteCollection == null) {
            quoteCollectionDao.insert(new QuoteCollection(name, symbols));
        } else {
            quoteCollection.setSymbols(symbols);
            quoteCollectionDao.update(quoteCollection);
        }
        log.debug("<<<<<< createOrUpdateCollection");
    }

    @RequestMapping(value = "/collection/{name}", method = GET)
    public ResponseEntity<?> getQuotesFromCollection(@PathVariable String name) {
        log.debug(">>>>>> getQuotesFromCollection");
        QuoteCollection quoteCollection = quoteCollectionDao.find(name);
        if (quoteCollection == null) {
            return new ResponseEntity<>("[]", HttpStatus.NOT_FOUND);
        } else {
            log.info("Getting quotes for {}", quoteCollection.getSymbols());
            List<Quote> quotes = quoteDao.list(Arrays.asList(quoteCollection.getSymbols().split(",")));
            return ResponseEntity.ok(quotes);
        }
    }


}
