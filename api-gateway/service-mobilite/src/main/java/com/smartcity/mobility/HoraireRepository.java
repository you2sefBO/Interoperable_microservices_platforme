package com.smartcity.mobility;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HoraireRepository extends JpaRepository<HoraireEntity, Integer> {
    List<HoraireEntity> findByLigneId(String ligneId);
}