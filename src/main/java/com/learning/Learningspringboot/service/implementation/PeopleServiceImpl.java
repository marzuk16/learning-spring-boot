package com.learning.Learningspringboot.service.implementation;

import com.learning.Learningspringboot.dto.PeopleDto;
import com.learning.Learningspringboot.entity.People;
import com.learning.Learningspringboot.enums.RecordStatus;
import com.learning.Learningspringboot.exception.ResourceNotFoundException;
import com.learning.Learningspringboot.helper.GetListHelper;
import com.learning.Learningspringboot.helper.PeopleHelper;
import com.learning.Learningspringboot.repository.PeopleRepository;
import com.learning.Learningspringboot.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

import static com.learning.Learningspringboot.enums.RecordStatus.DRAFT;

@Service @RequiredArgsConstructor public class PeopleServiceImpl implements PeopleService {

    private final EntityManager em;
    private final PeopleHelper helper;

    private final PeopleRepository repository;

    @Override public People save(PeopleDto dto) {
        People people = dto.to();
        helper.setBaseData(people);
        people = repository.save(people);
        return people;
    }

    @Override public People update(PeopleDto dto) {
        People people = this.find(dto.getId());
        dto.update(people);
        helper.updateBaseData(people, DRAFT);
        people = repository.save(people);
        return people;
    }

    @Override public People changeRecordStatus(Long id, RecordStatus status) {
        People people = this.find(id);
        helper.updateBaseData(people, status);
        people = repository.save(people);
        return people;
    }

    @Override public People find(Long id) {
        People people = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " not found!"));
        return people;
    }

    @Override public Map<String, Object> getList(String[] sortable, String[] searchable, String sortBy, String search, Integer page, Integer size) {
        GetListHelper<People> listHelper = new GetListHelper<>(em, People.class);
        Map<String, Object> peoples = listHelper.getList(sortable, searchable, sortBy, search, page, size);
        return peoples;
    }

    @Override public List<People> getAll(String sortBy) {
        List<People> peopleList = repository.getAll(sortBy);
        return peopleList;
    }
}
