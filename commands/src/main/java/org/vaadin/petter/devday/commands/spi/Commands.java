package org.vaadin.petter.devday.commands.spi;

public class Commands extends Broker<CommandHandler> {

    private static Commands INSTANCE = new Commands();

    Commands() {
        super(CommandHandler.class);
    }

    public static Commands getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public <COMMAND extends Command<RESULT>, RESULT> RESULT tell(COMMAND command) {
        return (RESULT) findHandler(command.getClass()).map(handler -> handler.handleCommand(command))
            .orElseThrow(() -> new IllegalArgumentException("No handler found"));
    }
}
