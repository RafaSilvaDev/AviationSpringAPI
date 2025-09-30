package com.project.airconsultant.repository;

import com.project.airconsultant.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlaneRepository extends JpaRepository<Plane, Long> {
}
