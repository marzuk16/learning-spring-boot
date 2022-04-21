package com.learning.Learningspringboot.utils;

import javassist.bytecode.stackmap.TypeData;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static Boolean checkColumnName(Field[] fields, String sortBy){
        Boolean flag = false;
        if(!sortBy.equals("")){
            String[] sort = sortBy.split(",");
            List<Field> fieldList = Arrays.asList(fields).stream().filter(i -> i.getName().equals(sort[0])).collect(
                    Collectors.toList());
            flag = fieldList.size() > 0 ? true : false;
        }
        return flag;
    }
}
