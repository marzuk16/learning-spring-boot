package com.learning.Learningspringboot.dto;

import com.learning.Learningspringboot.entity.People;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor public class PeopleDto {

    private Long id;
    @NotNull private String name;
    @NotNull private String email;
    @NotNull private String phone;
    @NotNull private String password;

    public People to() {
        People people = new People();
        people.setName(name);
        people.setEmail(email);
        people.setPhone(phone);
        people.setPassword(password);
        return people;
    }

    public void update(People people) {
        people.setId(id);
        people.setName(name);
        people.setEmail(email);
        people.setPhone(phone);
        people.setPassword(password);
    }
}
