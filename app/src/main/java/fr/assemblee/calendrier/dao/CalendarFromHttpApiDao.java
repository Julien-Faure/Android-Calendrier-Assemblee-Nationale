package fr.assemblee.calendrier.dao;

import org.vertx.java.core.json.impl.Json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import fr.assemblee.calendrier.entities.Calendar;

public class CalendarFromHttpApiDao implements CalendarDao {

    private String apiUrl;

    public CalendarFromHttpApiDao(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public Calendar getCalendar() throws IOException {
        StringBuilder sb = new StringBuilder();

        URL url = new URL(apiUrl);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }

        in.close();

        Calendar calendar;

        try {
            calendar = Json.decodeValue(sb.toString(), Calendar.class);
        }catch (Exception e){
            throw new IOException("Unable to parse JSON.");
        }

        return calendar;
    }
}
