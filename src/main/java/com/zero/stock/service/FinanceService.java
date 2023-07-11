package com.zero.stock.service;


import com.zero.stock.model.Company;
import com.zero.stock.model.Dividend;
import com.zero.stock.model.ScrapedResult;
import com.zero.stock.persist.CompanyRepository;
import com.zero.stock.persist.DividendRepository;
import com.zero.stock.persist.entity.CompanyEntity;
import com.zero.stock.persist.entity.DividendEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class FinanceService {
    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    public ScrapedResult getDividendByCompanyName(String companyName) {

        // 1. 회사명을 기준으로 회사 정보를 조회
        CompanyEntity company = companyRepository.findByName(companyName)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회사명입니다"));
        // 2. 조회된 회사 ID 로 배당금을 조회
        List<DividendEntity> dividendEntities = dividendRepository.findAllByCompanyId(company.getId());
        // 3. 결과 조합 후 반환

        List<Dividend> dividends = new ArrayList<>();

        return new ScrapedResult(
                Company.builder()
                        .ticker(company.getTicker())
                        .name(company.getName())
                        .build(),null
        );
    }
}
