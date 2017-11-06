package com.medicalsystem.model.value

import com.medicalsystem.model.Field
import com.medicalsystem.model.Patient
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class FieldValue<T> {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ID")
    var id: Long = 0

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PATIENT_ID")
    var patient: Patient? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FIELD_ID")
    var field: Field? = null

    abstract var value: T


    abstract fun setValueFromString(s: String)
}