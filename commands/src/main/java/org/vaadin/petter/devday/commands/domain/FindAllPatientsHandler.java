package org.vaadin.petter.devday.commands.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.vaadin.petter.devday.commands.spi.HandlerFor;
import org.vaadin.petter.devday.commands.spi.QueryHandler;

@HandlerFor(FindAllPatients.class)
public class FindAllPatientsHandler implements QueryHandler<FindAllPatients, List<Patient>> {

    @Override
    public List<Patient> handleQuery(FindAllPatients query) {
        List<Patient> allPatients = PatientRepository.findAll();
        if (query.getBornAfter().isPresent()) {
            return allPatients.stream().filter(p -> p.getBirthDate().isAfter(query.getBornAfter().get()))
                .collect(Collectors.toList());
        } else {
            return allPatients;
        }
    }
}
