package com.shlagoverflow.api.util;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leboc Philippe.
 * Singleton
 */
public final class DataParser {

    private static final DataParser instance = new DataParser();

    private static Logger LOGGER = LoggerFactory.getLogger(DataParser.class);

    private List<String> questions = new ArrayList<>();

    public static DataParser getInstance() {
        return instance;
    }

    private DataParser() {
        BufferedReader buffer = null;
        FileReader reader = null;
        final JSONParser jsonParser = new JSONParser();

        try {
            reader = new FileReader("scripts/output.json");
            buffer = new BufferedReader(reader);
            String line;

            while((line = buffer.readLine()) != null) {
                // Parse this line to JSON
                final JSONObject json = (JSONObject) jsonParser.parse(line);
                final String l = (String) json.get("question");
                if(l != null) questions.add(l);
            }

        } catch (IOException | ParseException e) {
            LOGGER.error("Read Exception", e);
        } finally {
            try {
                if(buffer != null) buffer.close();
                if(reader != null) reader.close();
            } catch (IOException e) {
                LOGGER.error("Buffer close exception: ", e);
            }
        }
    }

    public List<String> getQuestions() {
        return this.questions;
    }

    public static void main(String... args) {
        getInstance().questions.forEach(LOGGER::info);
    }
}
