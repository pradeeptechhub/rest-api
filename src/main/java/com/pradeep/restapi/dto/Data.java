package com.pradeep.restapi.dto;

import java.util.Map;

@lombok.Data
public class Data {
    private String operation;
    Table table;
    //Map<String, Table> table;
}