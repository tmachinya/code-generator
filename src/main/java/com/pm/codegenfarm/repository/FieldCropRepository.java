package com.pm.codegenfarm.repository;

import com.pm.codegenfarm.entity.FieldCrop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldCropRepository extends JpaRepository<FieldCrop, Long> {
}
