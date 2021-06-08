package de.joshizockt.telegram.api.event.events;

import de.joshizockt.telegram.api.entities.*;
import de.joshizockt.telegram.api.event.Event;
import de.joshizockt.telegram.api.java.TelegramAPI;

import java.io.File;

public abstract class ChatEvent extends Event {

    private final Chat chat;
    private final Message message;

    public static ChatEvent from(TelegramAPI api, Chat chat, Message message) {

        ChatEvent e;

        if(chat instanceof GroupChat) {
            e = new GroupChatEvent(api, chat, message);
        } else if(chat instanceof PrivateChat) {
            e = new PrivateChatEvent(api, chat, message);
        } else return null;

        return e;

    }

    ChatEvent(TelegramAPI api, Chat chat, Message message) {
        super(api);
        this.chat = chat;
        this.message = message;
    }

    abstract public com.pengrad.telegrambot.model.Message sendMessage(String message);

    abstract public com.pengrad.telegrambot.model.Message sendFile(File file);

    public Chat getChat() {
        return chat;
    }

    public Message getMessage() {
        return message;
    }

    public User getUser() {
        return message.getSender();
    }

}

