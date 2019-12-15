package com.pradeep.restapi.dto;

import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

public class Validator {

    private static final javax.validation.Validator validator;

    static {
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        validator = factory.getValidator();
        factory.close();
    }
}
