package com.pluralsight.springbootcrudwebapp.services;

import com.pluralsight.springbootcrudwebapp.models.Position;
import com.pluralsight.springbootcrudwebapp.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;

    public List<Position> getAllPromotion() {
        return positionRepository.findAll();
    }
}
