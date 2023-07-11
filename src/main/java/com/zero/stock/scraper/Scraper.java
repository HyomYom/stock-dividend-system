package com.zero.stock.scraper;

import com.zero.stock.model.Company;
import com.zero.stock.model.ScrapedResult;

public interface Scraper {
    Company scrapCompanyByTicker(String ticker);
    ScrapedResult scrap(Company company);
}
