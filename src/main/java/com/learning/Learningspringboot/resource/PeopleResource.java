package com.learning.Learningspringboot.resource;

import com.learning.Learningspringboot.entity.People;
import com.learning.Learningspringboot.repository.PeopleRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.learning.Learningspringboot.utils.response.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/people")
@Api(tags = "people's data")
public class PeopleResource {

    private final PeopleRepository repository;

    @PostMapping("/save")
    @ApiOperation(value = "save people", response = People.class)
    public ResponseEntity<JSONObject> save( @RequestBody People people) {

        repository.save(people);
        return ok(success(people).getJson());
    }
}
