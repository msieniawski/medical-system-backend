package com.medicalsystem.service;

import com.medicalsystem.json.model.JSONForm;
import com.medicalsystem.model.Form;
import com.medicalsystem.model.FormType;

public interface FormService extends CRUDService<Form, Integer> {

    Form findByType(FormType formType);

    JSONForm getForm(int patientId);

    JSONForm updateForm(int patientId, JSONForm jsonForm);

}
