package com.auchan.scanners.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlScannerRepository extends ScannerRepository, JpaRepository<Scanner, Integer> {

}
