package com.pradeep.restapi.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@lombok.Data
public class RequestPojo {
    private String service_name;
    private String userid;
    //List<Data> data;
    Data data;
    //private Map<String, Data> data = new HashMap<String, Data>();
}

