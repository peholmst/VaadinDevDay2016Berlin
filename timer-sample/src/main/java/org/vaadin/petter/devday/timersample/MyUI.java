package org.vaadin.petter.devday.timersample;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
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
    private Timer timer;

    @Override
    protected void init(VaadinRequest request) {
        layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
        timer = new Timer("PollingTimer@" + toString());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                pollBackend();
            }
        }, 0, 1000);
    }

    @Override
    public void detach() {
        Logger.getLogger(MyUI.class.getName()).info("Stopping timer in UI " + this);
        timer.cancel();
        super.detach();
    }

    private void pollBackend() {
        Logger.getLogger(MyUI.class.getName()).info("Polling backend from UI " + this);
        Optional<String> latestMessage = MyBackend.getLatestMessage();
        if (latestMessage.isPresent()) {
            access(() -> layout.addComponent(new Label(latestMessage.get())));
        }
    }

    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false, heartbeatInterval = 5)
    public static class Servlet extends VaadinServlet {
    }
}
