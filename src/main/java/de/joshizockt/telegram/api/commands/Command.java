package de.joshizockt.telegram.api.commands;

import de.joshizockt.telegram.api.entities.Message;
import de.joshizockt.telegram.api.entities.User;
import de.joshizockt.telegram.api.event.events.ChatEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Command {

    private final String name;
    private final String description;
    private final String[] aliases;

    public Command(@NotNull String name, @Nullable String description, @Nullable String... aliases) {
        this.name = name;
        this.description = description;
        this.aliases = aliases;
    }

    abstract void execute(User user, Message message, String[] args, ChatEvent event);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAliases() {
        return aliases;
    }

}
