package com.shlagoverflow.api.service;

import java.io.IOException;

import com.shlagoverflow.api.util.LuceneUtil;
import org.apache.lucene.queryparser.classic.ParseException;

public class HelloLucene {

    public static void main(String[] args) throws IOException, ParseException {
        LuceneUtil util = LuceneUtil.getInstance();

        util.indexTitle("Test de lucene performance");
        util.indexTitle("Test de performance");
        util.search("lucene").forEach(System.out::println);
    }
}