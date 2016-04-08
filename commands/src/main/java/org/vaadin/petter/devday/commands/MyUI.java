package org.vaadin.petter.devday.commands;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.vaadin.petter.devday.commands.domain.CreatePatient;
import org.vaadin.petter.devday.commands.domain.FindAllPatients;
import org.vaadin.petter.devday.commands.domain.Patient;
import org.vaadin.petter.devday.commands.spi.Commands;
import org.vaadin.petter.devday.commands.spi.Queries;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MyUI extends UI {

    private BeanItemContainer<Patient> patientContainer;
    private DateField bornAfter;
    private TextField firstName;
    private TextField lastName;
    private DateField birthDate;

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        HorizontalLayout filterBar = new HorizontalLayout();
        filterBar.setSpacing(true);
        layout.addComponent(filterBar);

        bornAfter = new DateField("Born after");
        bornAfter.setResolution(Resolution.DAY);
        filterBar.addComponent(bornAfter);

        Button filter = new Button("Find Patients", this::find);
        filterBar.addComponent(filter);
        filterBar.setComponentAlignment(filter, Alignment.BOTTOM_LEFT);

        patientContainer = new BeanItemContainer<>(Patient.class);
        Grid patientGrid = new Grid(patientContainer);
        patientGrid.setSizeFull();
        layout.addComponent(patientGrid);
        layout.setExpandRatio(patientGrid, 1.0f);

        Label title = new Label("Add new patient");
        title.addStyleName(ValoTheme.LABEL_H2);
        layout.addComponent(title);

        HorizontalLayout form = new HorizontalLayout();
        form.setSpacing(true);
        layout.addComponent(form);

        firstName = new TextField("First name");
        form.addComponent(firstName);

        lastName = new TextField("Last name");
        form.addComponent(lastName);

        birthDate = new DateField("Birth date");
        birthDate.setResolution(Resolution.DAY);
        form.addComponent(birthDate);

        Button add = new Button("Add patient", this::add);
        layout.addComponent(add);
    }

    private void find(Button.ClickEvent event) {
        List<Patient> result = Queries.getInstance().ask(new FindAllPatients(dateToLocalDate(bornAfter.getValue())));
        patientContainer.removeAllItems();
        patientContainer.addAll(result);
    }

    private void add(Button.ClickEvent event) {
        Patient createdPatient = Commands.getInstance()
            .tell(new CreatePatient(firstName.getValue(), lastName.getValue(), dateToLocalDate(birthDate.getValue())));
        Notification.show(createdPatient + " was successfully created");
    }

    private static LocalDate dateToLocalDate(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
    }

    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false, heartbeatInterval = 5)
    public static class Servlet extends VaadinServlet {
    }
}
