package org.vaadin.petter.devday.commands.spi;

public interface QueryHandler<QUERY extends Query<RESULT>, RESULT> extends Handler<QUERY, RESULT> {

    RESULT handleQuery(QUERY query);
}
