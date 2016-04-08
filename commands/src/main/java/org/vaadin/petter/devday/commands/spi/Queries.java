package org.vaadin.petter.devday.commands.spi;

public class Queries extends Broker<QueryHandler> {

    private static final Queries INSTANCE = new Queries();

    Queries() {
        super(QueryHandler.class);
    }

    public static Queries getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public <QUERY extends Query<RESULT>, RESULT> RESULT ask(QUERY query) {
        return (RESULT) findHandler(query.getClass()).map(handler -> handler.handleQuery(query))
            .orElseThrow(() -> new IllegalArgumentException("No handler found"));
    }
}
