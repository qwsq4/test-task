package com.example.testtask.service;

import com.example.testtask.dto.SocksDTO;
import com.example.testtask.entity.ComparisonOperator;
import com.example.testtask.entity.Socks;
import com.example.testtask.mapper.SocksMapper;
import com.example.testtask.repository.SocksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SocksService {
    private SocksRepository repository;
    private SocksMapper mapper;

    public int getSocks(String color, ComparisonOperator comparisonOperator, int cottonPart) {
        try {
            switch (comparisonOperator) {
                case EQUAL:
                    Socks equalCottonSocks = repository.findByColorAndCottonPart(color, cottonPart);
                    if (equalCottonSocks != null) {
                        return equalCottonSocks.getQuantity();
                    } else return 0;
                case LESS_THAN:
                    List<Socks> lessThanCottonSocks = repository.findAllByColorAndCottonPartBefore(color, cottonPart);
                    return lessThanCottonSocks.stream().mapToInt(Socks::getQuantity).sum();
                case MORE_THAN:
                    List<Socks> moreThanCottonSocks = repository.findAllByColorAndCottonPartAfter(color, cottonPart);
                    return moreThanCottonSocks.stream().mapToInt(Socks::getQuantity).sum();
            }
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public boolean addSocks(SocksDTO socksDTO) {
        try {
            Socks socks = repository.findByColorAndCottonPart(socksDTO.getColor(), socksDTO.getCottonPart());
            if (socks != null) {
                int newQuantity = socks.getQuantity() + socksDTO.getQuantity();
                socks.setQuantity(newQuantity);
                repository.save(socks);
                return true;
            } else {
                Socks newSocks = mapper.toEntity(socksDTO);
                repository.save(newSocks);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteSocks(SocksDTO socksDTO) {
        try {
            Socks socks = repository.findByColorAndCottonPart(socksDTO.getColor(), socksDTO.getCottonPart());
            int newQuantity = socks.getQuantity() - socksDTO.getQuantity();
            socks.setQuantity(newQuantity);
            repository.save(socks);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
