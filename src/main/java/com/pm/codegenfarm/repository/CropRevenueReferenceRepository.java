package com.pm.codegenfarm.repository;

import com.pm.codegenfarm.entity.CropRevenueReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRevenueReferenceRepository extends JpaRepository<CropRevenueReference, Long> {
}
