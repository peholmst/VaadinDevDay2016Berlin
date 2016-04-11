package org.vaadin.petter.devday.commands;

import com.vaadin.ui.*;
import org.vaadin.petter.devday.commands.longrunning.*;

import org.vaadin.petter.devday.commands.spi.Commands;

public class MyLongRunningWindow extends Window {

    private VerticalLayout statementLayout;

    private Conversation conversation;

    private HorizontalLayout answerButtons;

    public MyLongRunningWindow() {
        setModal(true);
        setWidth("80%");
        setHeight("80%");
        center();

        VerticalLayout root = new VerticalLayout();
        root.setMargin(true);
        root.setSpacing(true);
        root.setSizeFull();
        setContent(root);

        root.addComponent(new Button("Start new", this::startNewConversation));

        statementLayout = new VerticalLayout();
        statementLayout.setMargin(true);
        statementLayout.setSpacing(true);

        Panel statementPanel = new Panel(statementLayout);
        statementPanel.setSizeFull();
        root.addComponent(statementPanel);
        root.setExpandRatio(statementPanel, 1.0f);

        answerButtons = new HorizontalLayout();
        answerButtons.setSpacing(true);

        Button yes = new Button("Yes", this::answerYes);
        answerButtons.addComponent(yes);

        Button no = new Button("No", this::answerNo);
        answerButtons.addComponent(no);
    }

    private void startNewConversation(Button.ClickEvent event) {
        statementLayout.removeAllComponents();
        conversation = Commands.getInstance().tell(new StartConversation());
        addUnseenStatements();
    }

    private void answerYes(Button.ClickEvent event) {
        answer(true);
    }

    private void answerNo(Button.ClickEvent event) {
        answer(false);
    }

    private void answer(boolean answer) {
        Commands.getInstance().tell(new AnswerQuestion(conversation, answer));
        statementLayout.removeComponent(answerButtons);
        addUnseenStatements();
    }

    private void addUnseenStatements() {
        conversation.retrieveUnseenStatements().forEach(this::addStatement);
    }

    private void addStatement(Statement statement) {
        statementLayout.addComponent(new Label(statement.getText()));
        if (statement instanceof Question) {
            statementLayout.addComponent(answerButtons);
        }
    }
}
