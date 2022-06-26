package self.learning.learningspringboot.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import self.learning.learningspringboot.entity.People;
import self.learning.learningspringboot.enums.RecordStatus;

import static self.learning.learningspringboot.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class PeopleDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String password;

    @NotNull
    private RecordStatus status = DRAFT;

    public People to() {
        People people = new People();
        people.setName(name);
        people.setEmail(email);
        people.setPhone(phone);
        people.setPassword(password);
        people.setRecordStatus(status);
        return people;
    }

    public void update(People people) {
        people.setId(id);
        people.setName(name);
        people.setEmail(email);
        people.setPhone(phone);
        people.setPassword(password);
        people.setRecordStatus(status);
    }
}
