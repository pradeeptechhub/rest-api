package com.pradeep.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pradeep.restapi.dto.RequestPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class HomeController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Validator validator;

    public HomeController() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @PostMapping(path = "/hello")
    public String request(@Valid @RequestBody RequestPojo abc) throws JsonProcessingException, IllegalAccessException {

        //System.out.println("One:\n"+abc);
        ObjectMapper objectMapper = new ObjectMapper();
        // Java objects to JSON string - compact-print
        String jsonString = objectMapper.writeValueAsString(abc);
        //System.out.println("NEW:\n"+jsonString);
        // pretty print
        String prettyStaff1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(abc);
        System.out.println(prettyStaff1);

        RequestPojo emp = objectMapper.readValue(jsonString, RequestPojo.class);
        //System.out.println("EMP->"+emp);

        //System.out.println(emp.getData().getTable().getEmployee().getColumns().getDepartment());
        //validator.validate(emp).stream().forEach(HomeController::printError);
        Map<?,?> empMap = objectMapper.readValue(jsonString, Map.class);
        //System.out.println("Final->"+empMap);
        //System.out.println();

        System.out.println("***************************************");
        for (Map.Entry<?, ?> entry : empMap.entrySet()) {
            iterableMethod(entry);
        }

        Set<ConstraintViolation<RequestPojo>> violations = validator.validate(emp);

        for (ConstraintViolation<RequestPojo> violation : violations)
        {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            // Add JSR-303 errors to BindingResult
            // This allows Spring to display them in view via a FieldError
            System.out.println("ERROR");
            System.out.println(propertyPath + "||||||"+ message);
        }

        return abc.toString();
    }

    private void iterableMethod(Map.Entry<?, ?> entry) {
        if(entry.getValue() instanceof List){
            System.out.println("LIST->"+entry.getValue());
            // using iterators
            for (Object entry1 : (List) entry.getValue()) {
                if (entry1 instanceof Map) {
                    for (Map.Entry<?, ?> entry2 : ((Map<?, ?>) entry1).entrySet()) {
                        iterableMethod(entry2);
                    }
                }
            }
        }
        String employee = null;
        if(entry.getValue() instanceof Map) {
            if(((Map) entry.getValue()).get("columns") != null) {
                System.out.println("|Columns1->" + Arrays.toString(((Map) ((Map) entry.getValue()).get("columns")).keySet().stream().toArray()));
            }
            if(((Map) entry.getValue()).get("ref") != null) {
                System.out.println("|Ref1->" + Arrays.toString(((Map)((Map) entry.getValue()).get("ref")).keySet().stream().toArray()));
            }
            if(((Map) entry.getValue()).get("table") != null) {
                System.out.println("|Table1->" + ((Map)((Map) entry.getValue()).get("table")).keySet().stream().findFirst().get());
            }
            // using iterators
            for (Object o : ((Map) entry.getValue()).entrySet()) {
                Map.Entry<?, ?> entry1 = (Map.Entry<?, ?>) o;
                iterableMethod(entry1);
            }
         }else{
            System.out.println("Key = "+entry.getKey()+", Value = "+entry.getValue());
        }
    }



    private static void printError (
            ConstraintViolation<RequestPojo> violation) {
        System.out.println("HELLO");
        System.out.println(violation.getMessage());
    }
    @GetMapping
    public String test(){
        System.out.println("Test Request");
        return "Test Successful";
    }


}
