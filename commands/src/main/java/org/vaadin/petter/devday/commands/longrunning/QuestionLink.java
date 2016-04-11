package org.vaadin.petter.devday.commands.longrunning;

public class QuestionLink extends AbstractStatementLink {

    private final Statement yes;
    private final Statement no;

    public QuestionLink(Statement statement, Statement yes, Statement no) {
        super(statement);
        this.yes = yes;
        this.no = no;
    }

    public Statement getYes() {
        return yes;
    }

    public Statement getNo() {
        return no;
    }

    @Override
    public boolean isFinalStatement() {
        return false;
    }
}
