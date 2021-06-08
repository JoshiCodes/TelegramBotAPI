package de.joshizockt.telegram.api.event.events;

import de.joshizockt.telegram.api.entities.Chat;
import de.joshizockt.telegram.api.entities.Message;
import de.joshizockt.telegram.api.entities.User;
import de.joshizockt.telegram.api.java.TelegramAPI;

import java.io.File;

public class PrivateChatEvent extends ChatEvent {

    PrivateChatEvent(TelegramAPI api, Chat chat, Message message) {
        super(api, chat, message);
    }

    @Override
    public com.pengrad.telegrambot.model.Message sendMessage(String message) {
        return getUser().sendMessage(message);
    }

    @Override
    public com.pengrad.telegrambot.model.Message sendFile(File file) {
        return getUser().sendFile(file);
    }

}
