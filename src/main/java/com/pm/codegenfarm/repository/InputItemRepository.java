package com.pm.codegenfarm.repository;

import com.pm.codegenfarm.entity.InputItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputItemRepository extends JpaRepository<InputItem, Long> {
}
