package org.vaadin.petter.devday.commands.domain;

import org.vaadin.petter.devday.commands.spi.CommandHandler;
import org.vaadin.petter.devday.commands.spi.HandlerFor;

@HandlerFor(CreatePatient.class)
public class CreatePatientHandler implements CommandHandler<CreatePatient, Patient> {

    @Override
    public Patient handleCommand(CreatePatient command) {
        Patient patient = new Patient();
        patient.setBirthDate(command.birthDate);
        patient.setFirstName(command.firstName);
        patient.setLastName(command.lastName);

        PatientRepository.add(patient);

        return patient;
    }
}
