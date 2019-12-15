package com.pradeep.restapi.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Employee {
    Columns columns;
    //Map<String, String> columns = new HashMap<>();
    Ref ref;
}
