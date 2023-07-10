package com.zero.stock.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @GetMapping("/autocomplete")
    public ResponseEntity<?> autocomplete(@RequestParam String keyword){
        return null;
    }

    @GetMapping //회사 조회
    public ResponseEntity<?> searchCompany(){
        return null;
    }

    @PostMapping //회사 저장
    public ResponseEntity<?> addCompany(){
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCompany(){
        return null;
    }
}
