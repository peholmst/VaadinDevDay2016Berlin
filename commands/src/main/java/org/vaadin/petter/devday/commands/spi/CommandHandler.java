package org.vaadin.petter.devday.commands.spi;

public interface CommandHandler<COMMAND extends Command<RESULT>, RESULT> extends Handler<COMMAND, RESULT> {

    RESULT handleCommand(COMMAND command);
}
