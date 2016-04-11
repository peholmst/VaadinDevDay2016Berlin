package org.vaadin.petter.devday.commands.longrunning;

import org.vaadin.petter.devday.commands.spi.CommandHandler;
import org.vaadin.petter.devday.commands.spi.HandlerFor;

@HandlerFor(StartConversation.class)
public class StartConversationHandler implements CommandHandler<StartConversation, Conversation> {

    @Override
    public Conversation handleCommand(StartConversation command) {
        Statement initialQuestion = ProblemSolvingFlowsheet.getInstance().getInitialQuestion();
        Conversation conversation = new Conversation();
        conversation.addStatement(initialQuestion);
        return conversation;
    }
}
