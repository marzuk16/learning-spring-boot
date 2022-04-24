package com.learning.Learningspringboot.repository.custom;

import com.learning.Learningspringboot.entity.People;
import com.learning.Learningspringboot.enums.RecordStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PeopleCustomRepository {
    List<People> getList(Integer page, Integer size, String sortBy, RecordStatus status);
    Page<People> getListPage(Integer page, Integer size, String sortBy, RecordStatus status, Pageable pageable);

    List<People> getAll(String sortBy);
}
