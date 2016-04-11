package org.vaadin.petter.devday.commands.longrunning;

public abstract class AbstractStatementLink {

    private final Statement statement;

    public AbstractStatementLink(Statement statement) {
        this.statement = statement;
    }

    public Statement getStatement() {
        return statement;
    }

    public abstract boolean isFinalStatement();
}
