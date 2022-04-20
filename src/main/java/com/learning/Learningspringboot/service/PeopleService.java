package com.learning.Learningspringboot.service;

import com.learning.Learningspringboot.dto.PeopleDto;
import com.learning.Learningspringboot.entity.People;
import com.learning.Learningspringboot.enums.RecordStatus;
import com.learning.Learningspringboot.listParameter.PeopleListParameter;

import java.util.List;

public interface PeopleService {
    People save(PeopleDto dto);

    People update(PeopleDto dto);

    People changeRecordStatus(Long id, RecordStatus status);

    People find(Long id);

    List<People> getList(PeopleListParameter peopleListParameter);

    List<People> getAll(String direction, String column);
}
