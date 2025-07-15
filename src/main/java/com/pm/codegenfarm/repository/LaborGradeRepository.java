package com.pm.codegenfarm.repository;

import com.pm.codegenfarm.entity.LaborGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaborGradeRepository extends JpaRepository<LaborGrade, Long> {
}
