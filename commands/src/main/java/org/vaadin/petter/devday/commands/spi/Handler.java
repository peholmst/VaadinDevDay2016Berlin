package org.vaadin.petter.devday.commands.spi;

public interface Handler<MSG extends Message<RESULT>, RESULT> {

    default boolean supports(Class<?> messageClass) {
        HandlerFor handlerFor = getClass().getAnnotation(HandlerFor.class);
        if (handlerFor != null) {
            return handlerFor.value().isAssignableFrom(messageClass);
        } else {
            throw new UnsupportedOperationException("Cannot determine message class, please override this method");
        }
    }
}
