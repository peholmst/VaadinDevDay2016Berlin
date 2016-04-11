package org.vaadin.petter.devday.commands.longrunning;

import java.util.HashMap;
import java.util.Map;

public class ProblemSolvingFlowsheet {

    private static final ProblemSolvingFlowsheet INSTANCE = new ProblemSolvingFlowsheet();

    private Map<Statement, AbstractStatementLink> links = new HashMap<>();
    private Question initial;

    public ProblemSolvingFlowsheet() {

        // Questions
        Question q0 = new Question("Does the damn thing work?");
        Question q1 = new Question("Did you f**k with it?");
        Question q2 = new Question("Does anybody know");
        Question q3 = new Question("Will you catch hell?");
        Question q4 = new Question("Can you blame someone else?");

        // Statements
        Statement st0 = new Statement("Don't f**k with it");
        Statement st1 = new Statement("You dumb s**t");
        Statement st2 = new Statement("Hide it");
        Statement st3 = new Statement("S**t-can it");
        Statement st4 = new Statement("You poor bastard");
        Statement st5 = new Statement("No problem");

        // Links
        links.put(q0, new QuestionLink(q0, st0, q1));
        links.put(q1, new QuestionLink(q1, st1, q3));
        links.put(q2, new QuestionLink(q2, st4, st2));
        links.put(q3, new QuestionLink(q3, st4, st3));
        links.put(q4, new QuestionLink(q4, st5, st4));

        links.put(st0, new StatementLink(st0, st5));
        links.put(st1, new StatementLink(st1, q2));
        links.put(st2, new StatementLink(st2, st5));
        links.put(st3, new StatementLink(st3, st5));
        links.put(st4, new StatementLink(st4, q4));
        links.put(st5, new StatementLink(st5));

        initial = q0;
    }

    public Question getInitialQuestion() {
        return initial;
    }

    public AbstractStatementLink getLink(Statement statement) {
        return links.get(statement);
    }

    public static ProblemSolvingFlowsheet getInstance() {
        return INSTANCE;
    }
}
