package self.learning.learningspringboot.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityFieldCheck {

    public static Boolean isFiledExist(Class<?> cls, String field) {
        List<String> fields =
                Arrays.asList(cls.getDeclaredFields()).stream().map(i -> i.getName())
                        .collect(Collectors.toList());
        System.out.println("==============================="+ cls.getName() +"=================================");
        for(var i:fields) System.out.println(i);
        System.out.println("==============================="+ cls.getName() +"=================================");
        return fields.contains(field);
    }
}
