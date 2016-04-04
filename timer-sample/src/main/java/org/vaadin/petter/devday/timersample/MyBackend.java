package org.vaadin.petter.devday.timersample;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class MyBackend {

    private static final AtomicReference<Instant> lastMessage = new AtomicReference<>(Instant.now());

    private static final Random rnd = new Random();

    public static Optional<String> getLatestMessage() {
        if (lastMessage.get().plusSeconds(2 + rnd.nextInt(5)).isBefore(Instant.now())) {
            lastMessage.set(Instant.now());
            return Optional.of("A new message received at " + Instant.now());
        } else {
            return Optional.empty();
        }
    }
}
