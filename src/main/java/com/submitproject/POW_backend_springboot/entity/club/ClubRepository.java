package com.submitproject.POW_backend_springboot.entity.club;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // @Repository를 안 붙여도 레포지토리로서 사용할 수 있지만, 붙이는 것이 일반적이다.
public interface ClubRepository extends CrudRepository<Club, Integer> {
    Optional<Club> findByCode(String code);
    List<Club> findAllByOrderByName();
    List<Club> findAllByNameContainingOrderByName(String name);
}
