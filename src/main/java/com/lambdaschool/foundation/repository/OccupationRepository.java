package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.models.Occupation;
import org.springframework.data.repository.CrudRepository;

public interface OccupationRepository extends CrudRepository<Occupation, Long> {
}
