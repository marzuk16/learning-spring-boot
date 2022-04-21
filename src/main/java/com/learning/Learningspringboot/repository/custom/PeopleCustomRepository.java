package com.learning.Learningspringboot.repository.custom;

import com.learning.Learningspringboot.entity.People;
import com.learning.Learningspringboot.enums.RecordStatus;

import java.util.List;

public interface PeopleCustomRepository {
    List<People> getList(Integer page, Integer size, String sortBy, RecordStatus status);

    List<People> getAll(String sortBy);
}
