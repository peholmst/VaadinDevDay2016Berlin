package org.vaadin.petter.devday.commands.longrunning;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Conversation {

    private List<Statement> allStatements = new ArrayList<>();
    private List<Statement> unseenStatements = new ArrayList<>();

    public void addStatement(Statement statement) {
        allStatements.add(statement);
        unseenStatements.add(statement);
    }

    public List<Statement> retrieveUnseenStatements() {
        List<Statement> result = new ArrayList<>(unseenStatements);
        unseenStatements.clear();
        return result;
    }

    public Optional<Question> getNextQuestion() {
        if (allStatements.size() > 0) {
            Statement lastStatement = allStatements.get(allStatements.size() - 1);
            if (lastStatement instanceof Question) {
                return Optional.of((Question) lastStatement);
            }
        }
        return Optional.empty();
    }
}
