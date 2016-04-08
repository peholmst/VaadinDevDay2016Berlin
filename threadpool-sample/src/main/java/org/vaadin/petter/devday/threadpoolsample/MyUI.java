package org.vaadin.petter.devday.threadpoolsample;

import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
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
    private ScheduledFuture<?> job;

    @Override
    protected void init(VaadinRequest request) {
        layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
        job = ThreadPoolInit.executorService.scheduleAtFixedRate(this::pollBackend, 0, 1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void detach() {
        Logger.getLogger(MyUI.class.getName()).info("Cancelling job in UI " + this);
        job.cancel(true);
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

    @WebListener
    public static class ThreadPoolInit implements ServletContextListener {

        static ScheduledExecutorService executorService;

        @Override
        public void contextInitialized(ServletContextEvent sce) {
            executorService = Executors.newScheduledThreadPool(10);
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {
        }
    }
}
