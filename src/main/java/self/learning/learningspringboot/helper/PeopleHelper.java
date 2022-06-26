package self.learning.learningspringboot.helper;

import org.springframework.stereotype.Component;
import self.learning.learningspringboot.entity.People;
import self.learning.learningspringboot.enums.RecordStatus;

import static self.learning.learningspringboot.enums.RecordStatus.DRAFT;

@Component
public class PeopleHelper {
    public void setBaseData(People people) {
        //        people.setCreatedBy();
        people.setRecordStatus(DRAFT);
    }

    public void updateBaseData(People people, RecordStatus status) {
        //        people.setUpdatedBy();
        people.setRecordStatus(status);
    }
}
