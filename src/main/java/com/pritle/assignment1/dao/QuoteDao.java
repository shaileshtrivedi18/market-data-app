package com.pritle.assignment1.dao;

import com.pritle.assignment1.controller.MarketdataController;
import com.pritle.assignment1.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;
import static java.util.stream.Collectors.joining;

/**
 * © 2017 Pritle Holding B.V. - All Rights Reserved
 * See LICENSE file or http://pritle.com for further details
 *
 * @author Timofey Tsymlov <dev@pritle.com>
 * @version 01/02/2017
 */
@Component
public class QuoteDao {

    private static final String quotesUrl = "http://download.finance.yahoo.com/d/quotes.csv?s={symbolSeq}&f=sl1&e=.csv";
    private static final Logger log = LoggerFactory.getLogger(QuoteDao.class);

    @Autowired
    private RestTemplate restTemplate;

    public Quote find(String symbol) {
        List<Quote> quotes = list(singleton(symbol));
        return quotes.stream().findFirst().orElse(null);
    }

    public List<Quote> list(Collection<String> symbols) {
        if (symbols == null || symbols.isEmpty()) {
            return emptyList();
        }
        String symbolSeq = symbols.stream().collect(joining(","));
        log.debug("Getting quotes for: {}", symbolSeq);
        try {
            String csv = restTemplate.getForObject(quotesUrl, String.class, symbolSeq);
            log.info(csv);
            return Stream.of(csv.split("\n"))
                    .filter(line -> !line.endsWith("N/A")) //skip not found symbols
                    .map(line -> {
                        String[] parts = line.replace("\"", "").split(",");
                        String symbol = parts[0];
                        BigDecimal price = new BigDecimal(parts[1]);
                        return new Quote(symbol, price);
                    })
                    .collect(Collectors.toList());
        } catch (RestClientException re) {
            log.error(re.getMessage(), re);
            throw re;
        }
    }

}
