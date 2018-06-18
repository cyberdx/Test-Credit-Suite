package com.constants;


import com.fasterxml.jackson.annotation.JsonValue;
public enum DataState {
    STARTED("STARTED"),
    FINISHED("FINISHED");

    private String value;

    DataState(String value){
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.value;
    }
}
