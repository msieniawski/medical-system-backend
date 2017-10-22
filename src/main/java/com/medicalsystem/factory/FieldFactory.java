package com.medicalsystem.factory;

import com.medicalsystem.model.field.Field;
import com.medicalsystem.properties.ConfigProperties;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log
public class FieldFactory {

    private static final String FIELD_PACKAGE = "com.medicalsystem.model.field";
    private static final String FIELD_SUFFIX = "Field";

    private static ConfigProperties props;

    @Autowired
    public FieldFactory(ConfigProperties props) {
        FieldFactory.props = props;
    }

    /**
     * Creates a Field object from the config file
     *
     * @param _field an object representing a field loaded from config
     * @return       created Field object
     */
    public static Field<?> fromConfig(ConfigProperties.Section.Field _field) {
        Field<?> field = getFieldObject(_field.getType());

        // Set name
        field.setName(_field.getName());

        // Set excel columns
        field.setOpenExcelColumn(_field.getOpenIndex());
        field.setEvarExcelColumn(_field.getEvarIndex());

        // Set if multiple
        field.setMultiple(props.getMultipleFields().contains(field.getName()));

        // Add options
        List<ConfigProperties.Section.Field.Option> options = _field.getOptions();
        if (options != null) {
            options.forEach(option -> field.addOption(option.getKey(), option.getVal()));
        }

        log.info(String.format("Field created: '%s'", field.getName()));

        return field;
    }

    /**
     * Creates a Field object upon the type of the class. Case sensitive.
     *
     * @param type type of the class, e.g. "Text" or "Date"
     * @return     a proper extension of the Field class, eg. "TextField" or "DateField"
     */
    private static Field<?> getFieldObject(String type) {
        Field<?> field = null;
        String className = FIELD_PACKAGE + "." + type + FIELD_SUFFIX;
        try {
            Class<?> c = Class.forName(className);
            field = (Field<?>) c.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            log.severe(e.getMessage());
        }
        return field;
    }

}
