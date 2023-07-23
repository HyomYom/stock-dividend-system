package com.zero.stock.web;

import com.zero.stock.model.Company;
import com.zero.stock.model.constants.CacheKey;
import com.zero.stock.persist.entity.CompanyEntity;
import com.zero.stock.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/company", consumes = {"text/plain; charset=UTF-8", "application/*; charset=UTF-8"})
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    private final CacheManager redisCacheManager;

    @GetMapping("/autocomplete")
    public ResponseEntity<?> autocomplete(@RequestParam String keyword) {
//        var result = companyService.autoComplete(keyword);
        var result = companyService.getCompanyNamesByKeyword(keyword); // DB조회법

        return ResponseEntity.ok(result);
    }

    @GetMapping //회사 조회
    @PreAuthorize("hasRole('READ')")
    public ResponseEntity<?> searchCompany(final Pageable pageable) {
        Page<CompanyEntity> allCompany = companyService.getAllCompany(pageable);

        return ResponseEntity.ok(allCompany);
    }

    /**
     * 회사 및 배당금 정보 추가
     * @param request
     * @return
     */
    @PostMapping //회사 저장
    @PreAuthorize("hasRole('WRITE')")
    public ResponseEntity<?> addCompany(@RequestBody Company request) {
        String ticker = request.getTicker().trim();

        if (ObjectUtils.isEmpty(ticker)) {
            throw new RuntimeException("ticker is empty");

        }
            Company company = this.companyService.save(ticker);
            companyService.addAutocompleteKeyword(company.getName()); //트라이 저장법
        return ResponseEntity.ok(company);
    }

    @DeleteMapping("/{ticker}")
    @PreAuthorize("hasRole('WRITE')")
    public ResponseEntity<?> deleteCompany(@PathVariable String ticker) {
        String companyName = this.companyService.deleteCompany(ticker);
        this.clearFinanceCache(companyName);

        return ResponseEntity.ok(companyName);
    }

    public void clearFinanceCache(String companyName){
        this.redisCacheManager.getCache(CacheKey.KEY_FINANCE).evict(companyName);
    }
}
