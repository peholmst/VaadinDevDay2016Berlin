package org.vaadin.petter.devday.commands.longrunning;

import java.util.Optional;

public class StatementLink extends AbstractStatementLink {

    private final Statement nextStatement;

    public StatementLink(Statement statement) {
        super(statement);
        this.nextStatement = null;
    }

    public StatementLink(Statement statement, Statement nextStatement) {
        super(statement);
        this.nextStatement = nextStatement;
    }

    @Override
    public boolean isFinalStatement() {
        return nextStatement == null;
    }

    public Optional<Statement> getNextStatement() {
        return Optional.ofNullable(nextStatement);
    }
}
