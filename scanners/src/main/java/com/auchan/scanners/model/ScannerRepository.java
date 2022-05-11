package com.auchan.scanners.model;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScannerRepository {
    List<Scanner> findAll();
    Optional<Scanner> findById(Integer number);
    List<Scanner> findByInService(@Param("state") boolean inService);
    boolean existsById(int id);
    Scanner save(Scanner entity);
}
