package com.pritle.assignment1.dao;

import com.pritle.assignment1.domain.QuoteCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * © 2017 Pritle Holding B.V. - All Rights Reserved
 * See LICENSE file or http://pritle.com for further details
 *
 * @author Timofey Tsymlov <dev@pritle.com>
 * @version 01/02/2017
 */
@Repository
public class QuoteCollectionDao {

    private static final Logger log = LoggerFactory.getLogger(QuoteCollectionDao.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public QuoteCollection find(String name) {
        List<QuoteCollection> collections = jdbcTemplate.query(
                "SELECT name, symbols FROM QuoteCollection WHERE name = ?",
                (rs, column) -> new QuoteCollection(
                        rs.getString("name"),
                        rs.getString("symbols")),
                name
        );
        return collections.isEmpty() ? null : collections.get(0);
    }

    public void insert(QuoteCollection quoteCollection) {
        jdbcTemplate.update(
                "INSERT INTO QuoteCollection(name, symbols) VALUES (?, ?)",
                quoteCollection.getName(), quoteCollection.getSymbols()
        );
    }

    public void update(QuoteCollection quoteCollection) {
        jdbcTemplate.update(
                "UPDATE QuoteCollection SET symbols = ? WHERE name = ?",
                quoteCollection.getSymbols(), quoteCollection.getName()
        );
    }
}
