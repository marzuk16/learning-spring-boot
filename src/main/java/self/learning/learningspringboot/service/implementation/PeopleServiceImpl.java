package self.learning.learningspringboot.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import self.learning.learningspringboot.dto.PeopleDto;
import self.learning.learningspringboot.entity.People;
import self.learning.learningspringboot.exception.ResourceNotFoundException;
import self.learning.learningspringboot.helper.PeopleHelper;
import self.learning.learningspringboot.listParameter.PeopleListParameter;
import self.learning.learningspringboot.repository.PeopleRepository;
import self.learning.learningspringboot.service.PeopleService;

import java.util.Arrays;
import java.util.List;

import static self.learning.learningspringboot.enums.RecordStatus.DRAFT;

@Service
@RequiredArgsConstructor
public class PeopleServiceImpl implements PeopleService {

    private final PeopleHelper helper;
    private final PeopleRepository repository;
    @Override
    public People save(PeopleDto dto) {
        People people = dto.to();
        helper.setBaseData(people);
        people = repository.save(people);
        return people;
    }

    @Override
    public People update(PeopleDto dto) {
        People people = this.find(dto.getId());
        dto.update(people);
        helper.updateBaseData(people, DRAFT);
        people = repository.save(people);
        return people;
    }

    @Override
    public People find(Long id) {
        People people = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("People not found by: " + id));
        return people;
    }

    @Override
    public List<People> getList(PeopleListParameter peopleListParameter) {
        return null;
    }

    @Override
    public List<People> getAll(String direction, String column) {
        direction = "Sort.Direction." + direction.toUpperCase();
        List<String> columns = Arrays.asList(column.split(","));
        //        Sort dynamicSort = new Sort(direction, columns);
        //        List<People> peopleList = repository.findAllWithSort(dynamicSort);
        List<People> peopleList = repository.findAll();
        return peopleList;
    }
}
