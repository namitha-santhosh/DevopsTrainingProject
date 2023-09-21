package com.pluralsight.springbootcrudwebapp.repositories;

import com.pluralsight.springbootcrudwebapp.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position,Long> {
}
