package com.learning.Learningspringboot.helper;

import com.learning.Learningspringboot.entity.People;
import com.learning.Learningspringboot.enums.RecordStatus;
import org.springframework.stereotype.Component;

import static com.learning.Learningspringboot.enums.RecordStatus.DRAFT;

@Component public class PeopleHelper {
    public void setBaseData(People people) {
        //        people.setCreatedBy();
        people.setRecordStatus(DRAFT);
    }

    public void updateBaseData(People people, RecordStatus status) {
        //        people.setUpdatedBy();
        people.setRecordStatus(status);
    }
}
