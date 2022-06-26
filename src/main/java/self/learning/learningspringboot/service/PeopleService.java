package self.learning.learningspringboot.service;

import self.learning.learningspringboot.dto.PeopleDto;
import self.learning.learningspringboot.entity.People;
import self.learning.learningspringboot.enums.RecordStatus;
import self.learning.learningspringboot.listParameter.PeopleListParameter;

import java.util.List;

public interface PeopleService {
    People save(PeopleDto dto);

    People update(PeopleDto dto);

    People find(Long id);

    List<People> getList(PeopleListParameter peopleListParameter);

    List<People> getAll(String direction, String column);
}
