package org.vaadin.petter.devday.commands.longrunning;

import org.vaadin.petter.devday.commands.spi.Command;

public class AnswerQuestion implements Command<Conversation> {

    private final Conversation conversation;
    private final boolean answer;

    public AnswerQuestion(Conversation conversation, boolean answer) {
        this.conversation = conversation;
        this.answer = answer;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public boolean isAnswer() {
        return answer;
    }
}
