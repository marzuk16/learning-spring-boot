package com.learning.Learningspringboot.response;

import com.learning.Learningspringboot.entity.People;
import com.learning.Learningspringboot.enums.RecordStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PeopleResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private RecordStatus status;
    private Integer recordVersion;

    public static PeopleResponse from(People people) {
        PeopleResponse response = new PeopleResponse();
        response.setId(people.getId());
        response.setName(people.getName());
        response.setEmail(people.getEmail());
        response.setPhone(people.getPhone());
        response.setPassword(people.getPassword());
        response.setStatus(people.getRecordStatus());
        response.setRecordVersion(people.getRecordVersion());
        return response;
    }
}
