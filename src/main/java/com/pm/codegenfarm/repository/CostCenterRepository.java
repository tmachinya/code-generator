package com.pm.codegenfarm.repository;

import com.pm.codegenfarm.entity.CostCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostCenterRepository extends JpaRepository<CostCenter, Long> {
}
