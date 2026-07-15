package com.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import java.lang.reflect.Type;

public class Configurer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object transformer(Object fromValue, Type toValueType) {
        // Explica ao Cucumber como converter as tabelas e parâmetros usando o Jackson
        return objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType));
    }
}
