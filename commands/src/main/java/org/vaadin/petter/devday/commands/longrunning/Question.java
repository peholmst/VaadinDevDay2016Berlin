package org.vaadin.petter.devday.commands.longrunning;

import java.util.Optional;

public class Question extends Statement {

    public Question(String text) {
        super(text);
    }

    @Override
    public boolean isActionRequired() {
        return true;
    }

    @Override
    public Optional<Statement> getNextStatement() {
        throw new UnsupportedOperationException("Use getNextStatement(boolean)");
    }

    public Statement getNextStatement(boolean answer) {
        QuestionLink link = (QuestionLink) ProblemSolvingFlowsheet.getInstance().getLink(this);
        return answer ? link.getYes() : link.getNo();
    }
}
