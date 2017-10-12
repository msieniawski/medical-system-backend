package com.medicalsystem.model.field;

import com.medicalsystem.model.IdComparableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * A superclass for every type of field.
 * Subclasses must override "options" getter providing JPA annotations for the join table.
 * @param <T> - the type of the values stored in the field
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public abstract class Field<T> extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    /**
     * An index of the column in the excel file corresponding to the field.
     */
    @Getter @Setter
    private int excelColumn;

    /**
     * A map describing values that the field can acquire.
     * Key   - values of the cell in the excel file (e.g. "0", "1", "2", "x")
     * Value - a 'literal' values for the corresponding key (e.g. "smoker", "non-smoker", "n/a")
     *
     * The map remains empty if the field has no restrictions on the possible values.
     *
     * This field has the @Transient annotation, since its JPA mapping is handled by annotating getter in subclasses.
     */
    @Transient
    @Getter @Setter
    private Map<T, String> options = new HashMap<>();


    /**
     * Constructor
     *
     * @param name        name of the field
     * @param excelColumn index of the corresponding excel column
     * @param options     a map of possible options (null if none)
     */
    public Field(String name, int excelColumn, Map<T, String> options) {
        this.name = name;
        this.excelColumn = excelColumn;
        this.options = (options == null) ? new HashMap<>() : options;
    }

    public void addOption(T key, String value) {
        this.options.put(key, value);
    }

    /**
     * Abstract method for adding option with a String representation of the key.
     * The key should be converted to a proper type in subclasses.
     *
     * @param key   a String representation of the key
     * @param value values
     */
    public abstract void addOption(String key, String value);

}
