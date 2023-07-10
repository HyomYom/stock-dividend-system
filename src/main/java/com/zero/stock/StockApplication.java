package com.zero.stock;


import com.zero.stock.model.Company;
import com.zero.stock.scraper.YahooFinanceScraper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class StockApplication {

	public static void main(String[] args) {
//		SpringApplication.run(StockApplication.class, args);

		YahooFinanceScraper scraper = new YahooFinanceScraper();
		var result = scraper.scrap(Company.builder().ticker("O").build());
		System.out.println(result);
	}

}
