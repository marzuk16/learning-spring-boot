package self.learning.learningspringboot.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import self.learning.learningspringboot.Routes;
import self.learning.learningspringboot.dto.PeopleDto;
import self.learning.learningspringboot.entity.People;
import self.learning.learningspringboot.listParameter.PeopleListParameter;
import self.learning.learningspringboot.response.PeopleResponse;
import self.learning.learningspringboot.service.PeopleService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;
import static self.learning.learningspringboot.utils.response.ResponseBuilder.success;

@RestController
@RequiredArgsConstructor
@RequestMapping(Routes.API_VERSION)
@Api(tags = "people's data")
public class PeopleResource {
    private final PeopleService service;

    @GetMapping(Routes.PEOPLE_BASE_ROUTE)
    @ApiOperation(value = "get all people", response = PeopleResponse.class)
    public ResponseEntity<JSONObject> getAll(@RequestParam(value = "direction") String direction,
            @RequestParam(value = "sortBy") String column) {

        List<People> peoples = service.getAll(direction, column);
        List<PeopleResponse> responses = peoples.stream().map(PeopleResponse::from).collect(Collectors.toList());

        return ok(success(responses, "all people get success").getJson());
    }
    @PostMapping(Routes.PEOPLE_BASE_ROUTE) @ApiOperation(value = "save people", response = PeopleResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody PeopleDto dto) {

        People people = service.save(dto);

        return ok(success(PeopleResponse.from(people), "People save success!").getJson());
    }

    @PutMapping(Routes.PEOPLE_BASE_ROUTE) @ApiOperation(value = "update people", response = PeopleResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody PeopleDto dto) {

        People people = service.update(dto);

        return ok(success(PeopleResponse.from(people), "People update success!").getJson());
    }

    @GetMapping(Routes.FIND_PEOPLE)
    @ApiOperation(value = "find people by id", response = PeopleResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable(value = "id") Long id) {

        People people = service.find(id);

        return ok(success(PeopleResponse.from(people), "get success!").getJson());
    }

    @GetMapping(Routes.PEOPLE_LIST)
    @ApiOperation(value = "get people lists", response = PeopleResponse.class)
    public ResponseEntity<JSONObject> getList(@RequestParam(value = "parameter", required = false) PeopleListParameter parameter) {

        List<People> peoples = service.getList(parameter);
        peoples.stream().map(PeopleResponse::from).collect(Collectors.toList());

        return ok(success(peoples, "get list success!").getJson());
    }
}
