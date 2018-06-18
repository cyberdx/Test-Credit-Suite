package com.constants;


import com.fasterxml.jackson.annotation.JsonValue;

public enum DataType {
    APPLICATION_LOG("APPLICATION_LOG");

    private String value;

    DataType(String value){
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.value;
    }
}
