package com.example.testtask.controller;

import com.example.testtask.dto.SocksDTO;
import com.example.testtask.entity.ComparisonOperator;
import com.example.testtask.service.SocksService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/socks")
public class SocksController {
    private SocksService service;

    @PostMapping("/income")
    public ResponseEntity addSocks(@RequestBody SocksDTO socksDto) {
        try {
            if (socksDto == null || socksDto.getColor().equals("") || socksDto.getColor() == null ||
                    socksDto.getCottonPart() < 0 || socksDto.getCottonPart() > 100 || socksDto.getQuantity() <= 0) {

            }
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }


        if (service.addSocks(socksDto)) {
            return ResponseEntity.ok().build();
        } else return ResponseEntity.internalServerError().build();
    }

    @PostMapping("/outcome")
    public ResponseEntity deleteSocks(@RequestBody SocksDTO socksDto) {
        if (socksDto == null || socksDto.getCottonPart() < 0 || socksDto.getCottonPart() > 100
                || socksDto.getQuantity() < 0 || service.getSocks(socksDto).getQuantity() - socksDto.getQuantity() < 0) {
            return ResponseEntity.badRequest().build();
        }

        if (service.deleteSocks(socksDto)) {
            return ResponseEntity.ok().build();
        } else return ResponseEntity.internalServerError().build();
    }

    @GetMapping
    public ResponseEntity getSocks(@RequestParam String color, @RequestParam ComparisonOperator comparisonOperator,
                                   @RequestParam int cottonPart) {
        if (color == null || comparisonOperator == null || cottonPart < 0) {
            return ResponseEntity.badRequest().build();
        }

        int quantity = service.getSocksQuantity(color, comparisonOperator, cottonPart);

        if (quantity < 0) {
            return ResponseEntity.internalServerError().build();
        } else return ResponseEntity.ok(quantity);
    }
}
