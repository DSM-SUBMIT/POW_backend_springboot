package com.submitproject.POW_backend_springboot.entity.clubTag;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubTagRepository extends CrudRepository<ClubTag, Integer> {
}
