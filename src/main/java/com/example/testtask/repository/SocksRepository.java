package com.example.testtask.repository;

import com.example.testtask.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocksRepository extends JpaRepository<Socks, Long> {
    Socks findByColorAndCottonPart(String color, int cottonPart);
    List<Socks> findAllByColorAndCottonPartAfter(String color, int cottonPart);
    List<Socks> findAllByColorAndCottonPartBefore(String color, int cottonPart);
}
