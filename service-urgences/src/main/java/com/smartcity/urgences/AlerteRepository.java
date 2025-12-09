package com.smartcity.urgences;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlerteRepository extends JpaRepository<AlerteEntity, String> {
    List<AlerteEntity> findByZoneContainingIgnoreCase(String zone);
}