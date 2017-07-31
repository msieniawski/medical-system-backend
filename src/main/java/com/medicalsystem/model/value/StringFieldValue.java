package com.medicalsystem.model.value;

import javax.persistence.*;

@Entity
@Table(name = "STRING_FIELDS_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class StringFieldValue extends FieldValue<String> {

    @Access(AccessType.PROPERTY)
    @Override
    public String getValue() {
        return super.getValue();
    }
}
