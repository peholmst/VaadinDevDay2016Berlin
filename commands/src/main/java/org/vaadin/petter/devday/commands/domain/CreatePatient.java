package org.vaadin.petter.devday.commands.domain;

import java.time.LocalDate;

import org.vaadin.petter.devday.commands.spi.Command;

public class CreatePatient implements Command<Patient> {

    final String firstName;
    final String lastName;
    final LocalDate birthDate;

    public CreatePatient(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }
}
