package com.learning.Learningspringboot.service;

import com.learning.Learningspringboot.dto.PeopleDto;
import com.learning.Learningspringboot.entity.People;
import com.learning.Learningspringboot.enums.RecordStatus;

import java.util.List;
import java.util.Map;

public interface PeopleService {
    People save(PeopleDto dto);

    People update(PeopleDto dto);

    People changeRecordStatus(Long id, RecordStatus status);

    People find(Long id);

    Map<String, Object> getList(String[] sortable, String[] searchable, String sortBy, String search, Integer page, Integer size);

    List<People> getAll(String sortBy);
}
