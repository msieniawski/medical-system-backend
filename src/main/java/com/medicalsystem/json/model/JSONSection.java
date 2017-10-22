package com.medicalsystem.json.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class JSONSection {

    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private List<JSONField> fields;

}
