package com.zero.stock.persist;


import com.zero.stock.persist.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository <CompanyEntity, Long>{
    boolean existsByTicker(String ticker);

    Optional<CompanyEntity>findByName(String name); // Optional -> null point 방지
    Page<CompanyEntity> findByNameStartingWithIgnoreCase(String s, Pageable pageable);

}
