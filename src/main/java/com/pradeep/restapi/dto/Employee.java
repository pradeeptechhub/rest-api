package com.pradeep.restapi.dto;

import lombok.Data;

@Data
public class Employee {
    Columns columns;
    //Map<String, String> columns = new HashMap<>();
    Ref ref;
}
