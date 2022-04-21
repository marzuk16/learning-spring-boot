package com.learning.Learningspringboot.resource;

import com.learning.Learningspringboot.dto.PeopleDto;
import com.learning.Learningspringboot.entity.People;
import com.learning.Learningspringboot.enums.RecordStatus;
import com.learning.Learningspringboot.listParameter.PeopleListParameter;
import com.learning.Learningspringboot.response.PeopleResponse;
import com.learning.Learningspringboot.service.PeopleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.learning.Learningspringboot.utils.response.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/people")
@Api(tags = "people's data")
public class PeopleResource {

    private final PeopleService service;

    @PostMapping("/save") @ApiOperation(value = "save people", response = PeopleResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody PeopleDto dto) {

        People people = service.save(dto);

        return ok(success(PeopleResponse.from(people), "People save success!").getJson());
    }

    @PutMapping("/update") @ApiOperation(value = "update people", response = PeopleResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody PeopleDto dto) {

        People people = service.update(dto);

        return ok(success(PeopleResponse.from(people), "People update success!").getJson());
    }

    @PutMapping("/change-record-status/{id}/{status}")
    @ApiOperation(value = "update people record status", response = PeopleResponse.class)
    public ResponseEntity<JSONObject> changeRecordStatus(@PathVariable Long id,
            @PathVariable RecordStatus status) {

        People people = service.changeRecordStatus(id, status);

        return ok(success(PeopleResponse.from(people), "Status change success!").getJson());
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "find people by id", response = PeopleResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        People people = service.find(id);

        return ok(success(PeopleResponse.from(people), "get success!").getJson());
    }

    @GetMapping("/getList")
    @ApiOperation(value = "get people lists", response = PeopleResponse.class)
    public ResponseEntity<JSONObject> getList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy,
            @RequestParam(value = "recordStatus", defaultValue = "") RecordStatus status
    ) {

        List<People> peoples = service.getList(page, size, sortBy, status);
        List<PeopleResponse> responses =
                peoples.stream().map(PeopleResponse::from).collect(Collectors.toList());

        return ok(success(responses, "get list success!").getJson());
    }

    @GetMapping("/getAll") @ApiOperation(value = "get all people", response = PeopleResponse.class)
    public ResponseEntity<JSONObject> getAll(@RequestParam(value = "sortBy", defaultValue = "") String sortBy) {

        List<People> peoples = service.getAll(sortBy);
        List<PeopleResponse> responses =
                peoples.stream().map(PeopleResponse::from).collect(Collectors.toList());

        return ok(success(responses, "all people get success").getJson());
    }
}
