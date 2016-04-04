package org.vaadin.petter.devday.threadsample;

import java.util.Optional;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Push
public class MyUI extends UI {

    private VerticalLayout layout;

    @Override
    protected void init(VaadinRequest request) {
        layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
        new Thread(this::pollBackend, "PollingThread@" + toString()).start();
    }

    private void pollBackend() {
        while (true) {
            Logger.getLogger(MyUI.class.getName()).info("Polling backend from UI " + this);
            Optional<String> latestMessage = MyBackend.getLatestMessage();
            if (latestMessage.isPresent()) {
                access(() -> layout.addComponent(new Label(latestMessage.get())));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class Servlet extends VaadinServlet {
    }
}
