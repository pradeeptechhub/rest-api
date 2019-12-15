package com.pradeep.restapi.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Size;

@Data
public class Dep_id {

    //@NonNull
    @Size(min = 1, max = 4, message = "ISSUE WITH SIZE")
    private String dep_code;
}
