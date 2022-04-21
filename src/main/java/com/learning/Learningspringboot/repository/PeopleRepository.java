package com.learning.Learningspringboot.repository;

import com.learning.Learningspringboot.entity.People;
import com.learning.Learningspringboot.repository.custom.PeopleCustomRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long>, PeopleCustomRepository {
//    List<People> findAllWithSort(Sort sort);
}
