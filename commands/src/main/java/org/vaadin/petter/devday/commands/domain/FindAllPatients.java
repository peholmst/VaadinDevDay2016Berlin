package org.vaadin.petter.devday.commands.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.vaadin.petter.devday.commands.spi.Query;

public class FindAllPatients implements Query<List<Patient>> {

    private final LocalDate bornAfter;

    public FindAllPatients(LocalDate bornAfter) {
        this.bornAfter = bornAfter;
    }

    public FindAllPatients() {
        this.bornAfter = null;
    }

    public Optional<LocalDate> getBornAfter() {
        return Optional.ofNullable(bornAfter);
    }
}
