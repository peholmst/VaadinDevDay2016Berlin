package org.vaadin.petter.devday.commands.spi;

import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

abstract class Broker<HANDLER extends Handler> {

    private final ServiceLoader<HANDLER> serviceLoader;
    private final Map<Class, HANDLER> handlerCache = new ConcurrentHashMap<>();

    Broker(Class<HANDLER> handlerClass) {
        serviceLoader = ServiceLoader.load(handlerClass);
    }

    @SuppressWarnings("unchecked")
    protected Optional<? extends HANDLER> findHandler(Class<?> messageClass) {
        HANDLER cachedHandler = handlerCache.get(messageClass);
        if (cachedHandler != null) {
            return Optional.of(cachedHandler);
        }
        for (HANDLER handler : serviceLoader) {
            if (handler.supports(messageClass)) {
                handlerCache.put(messageClass, handler);
                return Optional.of(handler);
            }
        }
        return Optional.empty();
    }
}
