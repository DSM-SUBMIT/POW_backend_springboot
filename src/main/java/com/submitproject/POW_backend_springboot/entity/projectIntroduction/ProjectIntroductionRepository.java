package com.submitproject.POW_backend_springboot.entity.projectIntroduction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectIntroductionRepository extends CrudRepository<ProjectIntroduction, Integer> {
    List<ProjectIntroduction> findAllByClubId(Integer id);
}
