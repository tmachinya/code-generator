package com.pm.codegenfarm.repository;

import com.pm.codegenfarm.entity.CropInputReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropInputReferenceRepository extends JpaRepository<CropInputReference, Long> {
}
