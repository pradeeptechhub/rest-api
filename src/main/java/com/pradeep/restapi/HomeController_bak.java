package com.pradeep.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pradeep.restapi.dto.RequestPojo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@RestController
public class HomeController_bak {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Validator validator;

    public HomeController_bak() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @PostMapping(path = "/hello")
    public String request(@Valid @RequestBody RequestPojo abc) throws JsonProcessingException, IllegalAccessException {

        System.out.println("One:\n"+abc);
        ObjectMapper objectMapper = new ObjectMapper();
        // Java objects to JSON string - compact-print
        String jsonString = objectMapper.writeValueAsString(abc);
        System.out.println("NEW:\n"+jsonString);
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
            Iterator itr = ((List) entry.getValue()).iterator();
            while(itr.hasNext()) {
                Object entry1 = itr.next();
                if(entry1 instanceof Map) {
                   // .iterator()
                    for (Map.Entry<?, ?> entry2 : ((Map<?,?>) entry1).entrySet()) {
                        iterableMethod(entry2);
                    }

                }
                //iterableRedundantMethod(entry1);
            }
        }
        if(entry.getValue() instanceof Map) {
            if(((Map) entry.getValue()).get("columns") != null) {
                //System.out.println("|Columns->" + ((Map) entry.getValue()).get("columns"));
                System.out.println("|Columns1->" + Arrays.toString(((Map) ((Map) entry.getValue()).get("columns")).keySet().stream().toArray()));
            }
            if(((Map) entry.getValue()).get("ref") != null) {
                //System.out.println("|Ref->" + ((Map) entry.getValue()).get("ref"));
                System.out.println("|Ref1->" + Arrays.toString(((Map)((Map) entry.getValue()).get("ref")).keySet().stream().toArray()));
            }
            if(((Map) entry.getValue()).get("table") != null) {
                //System.out.println("|Table->" + ((Map) entry.getValue()).get("table"));
                System.out.println("|Table1->" + ((Map)((Map) entry.getValue()).get("table")).keySet().stream().findFirst().get());
            }
            // using iterators
            Iterator itr = ((Map) entry.getValue()).entrySet().iterator();
            while(itr.hasNext()) {
                Map.Entry<?, ?> entry1 = (Map.Entry<?, ?>) itr.next();
                iterableMethod(entry1);
                //iterableRedundantMethod(entry1);
            }
         }else{
            System.out.println("Key = "+entry.getKey()+", Value = "+entry.getValue());
        }
    }

    private void iterableRedundantMethod(Map.Entry<?, ?> entry1) {
        if(entry1.getValue() instanceof Map){
            Iterator<Map.Entry<?, ?>> itr1 = ((Map) entry1.getValue()).entrySet().iterator();
            while(itr1.hasNext()) {
                Map.Entry<?, ?> entry2 = itr1.next();
                if(entry2.getValue() instanceof Map){
                    Iterator<Map.Entry<?, ?>> itr2 = ((Map) entry2.getValue()).entrySet().iterator();
                    while(itr2.hasNext()) {
                        Map.Entry<?, ?> entry3 = itr2.next();
                        if(entry3.getValue() instanceof Map){
                            Iterator<Map.Entry<?, ?>> itr3 = ((Map) entry3.getValue()).entrySet().iterator();
                            while(itr3.hasNext()) {
                                Map.Entry<?, ?> entry4 = itr3.next();
                                if(entry4.getValue() instanceof Map){
                                    Iterator<Map.Entry<?, ?>> itr4 = ((Map) entry4.getValue()).entrySet().iterator();
                                    while(itr4.hasNext()) {
                                        Map.Entry<?, ?> entry5 = itr4.next();
                                        System.out.println("Key5 = "+entry5.getKey()+", Value5 = "+entry5.getValue());
                                    }
                                }else{
                                    System.out.println("Key4 = "+entry4.getKey()+", Value4 = "+entry4.getValue());
                                }
                            }
                        }else{
                            System.out.println("Key3 = "+entry3.getKey()+", Value3 = "+entry3.getValue());
                        }
                    }
                }else{
                    System.out.println("Key2 = "+entry2.getKey()+", Value2 = "+entry2.getValue());
                }
            }
        }else{
            System.out.println("Key1 = "+entry1.getKey()+", Value1 = "+entry1.getValue());
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


    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

}
