package com.zero.stock.scraper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScraperApplication {

    public static void main(String[] args) {
        String url = "https://finance.yahoo.com/quote/O/history?period1=86400&period2=1688952677&interval=1mo";

        try {
            URL yahooFinance = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) yahooFinance.openConnection();

            // Handle redirects manually
            int status = connection.getResponseCode();
            if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM || status == HttpURLConnection.HTTP_SEE_OTHER) {
                String newUrl = connection.getHeaderField("Location");
                connection = (HttpURLConnection) new URL(newUrl).openConnection();
            }

            // Read the content from the connection
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Perform data extraction and processing using the response
            // ...
            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}