package de.joshizockt.telegram.api.event;

import de.joshizockt.telegram.api.java.TelegramAPI;

public class Event {

    private final TelegramAPI api;

    private final String name;
    private final boolean cancellable;

    public Event(TelegramAPI api, String name, boolean cancellable) {
        this.api = api;
        this.name = name;
        this.cancellable = cancellable;
    }

    public Event(TelegramAPI api, String name) {
        this(api, name, false);
    }

    public Event(TelegramAPI api) {
        this(api, null);
    }

}
