package org.vaadin.petter.devday.commands.longrunning;

import java.util.Optional;

public class Statement {

    private final String text;

    public Statement(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean isActionRequired() {
        return false;
    }

    public boolean isFinalStatement() {
        return ProblemSolvingFlowsheet.getInstance().getLink(this).isFinalStatement();
    }

    public Optional<Statement> getNextStatement() {
        return ((StatementLink) ProblemSolvingFlowsheet.getInstance().getLink(this)).getNextStatement();
    }
}
