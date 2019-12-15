package com.pradeep.restapi.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Ref {
    Dep_id dep_id;
    Add_id add_id;

    //Map<String, Map<String, String>> ref = new HashMap<>();

}
