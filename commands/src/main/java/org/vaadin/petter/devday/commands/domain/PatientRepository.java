package org.vaadin.petter.devday.commands.domain;

import java.util.ArrayList;
import java.util.List;

public class PatientRepository {

    private static List<Patient> patientList = new ArrayList<>();

    public static void add(Patient patient) {
        patientList.add(patient);
    }

    public static List<Patient> findAll() {
        return new ArrayList<>(patientList);
    }
}
