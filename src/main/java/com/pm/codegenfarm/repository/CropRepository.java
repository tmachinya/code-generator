package com.pm.codegenfarm.repository;

import com.pm.codegenfarm.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
}
