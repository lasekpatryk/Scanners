package com.auchan.scanners.controller;

import com.auchan.scanners.model.Scanner;
import com.auchan.scanners.model.ScannerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ScannerController{
    private static final Logger logger = LoggerFactory.getLogger(ScannerController.class);
    private final ScannerRepository repository;

    public ScannerController(ScannerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/scanners")
    ResponseEntity<List<Scanner>> findAll(){
        return ResponseEntity.ok(repository.findAll());
    }
    @GetMapping("/scanners/{id}")
    ResponseEntity<?> findById(@PathVariable int id){
        if (repository.existsById(id)){
            return ResponseEntity.ok(repository.findById(id));

        } else {
            logger.warn("Brak skanera o podanym numerze");
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/scanners/service/{param}")
    ResponseEntity<List<Scanner>> findByInService(@PathVariable boolean param){
        return ResponseEntity.ok(repository.findByInService(param));
    }
    @PatchMapping("/scanners/{id}")
    ResponseEntity<?>updateScanner(@PathVariable int id, @Valid Scanner toUpdate){
        if (repository.existsById(id)){
            if(!repository.findById(id).get().isInService()){
                Scanner skaner = toUpdate;
                skaner.setTimeOfLogin(LocalDateTime.now());
                skaner.setId(id);
                skaner.setScannerNumber(id);
                repository.save(skaner);
                return ResponseEntity.noContent().build();
            } else {
                logger.info("Urządzenie w serwisie");
                return ResponseEntity.badRequest().build();
            }

        } else {
            return ResponseEntity.notFound().build();
        }

    }



}
