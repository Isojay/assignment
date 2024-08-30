package com.assignment.Repository;

import com.assignment.Entity.SerialNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerialNumberRepository extends JpaRepository<SerialNumber, String> {
}

