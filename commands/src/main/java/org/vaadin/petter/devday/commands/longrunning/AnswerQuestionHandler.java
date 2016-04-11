package org.vaadin.petter.devday.commands.longrunning;

import org.vaadin.petter.devday.commands.spi.CommandHandler;
import org.vaadin.petter.devday.commands.spi.HandlerFor;

@HandlerFor(AnswerQuestion.class)
public class AnswerQuestionHandler implements CommandHandler<AnswerQuestion, Conversation> {

    @Override
    public Conversation handleCommand(AnswerQuestion command) {
        Question question = command.getConversation().getNextQuestion()
            .orElseThrow(() -> new IllegalStateException("No question to answer"));
        Conversation conversation = command.getConversation();
        boolean answer = command.isAnswer();
        Statement nextStatement = question.getNextStatement(answer);
        conversation.addStatement(nextStatement);
        while (!nextStatement.isActionRequired() && !nextStatement.isFinalStatement()) {
            nextStatement = nextStatement.getNextStatement().get();
            conversation.addStatement(nextStatement);
        }
        return conversation;
    }
}
