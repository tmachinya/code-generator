package com.pm.codegenfarm.repository;

import com.pm.codegenfarm.entity.ActivityInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityInputRepository extends JpaRepository<ActivityInput, Long> {
}
