package com.smartcity.air;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MesureRepository extends JpaRepository<MesureEntity, Long> {
    Optional<MesureEntity> findTopByZoneContainingIgnoreCaseOrderByIdDesc(String zone);
}