package de.joshizockt.telegram.api.event.events;

import de.joshizockt.telegram.api.entities.Chat;
import de.joshizockt.telegram.api.entities.GroupChat;
import de.joshizockt.telegram.api.entities.Message;
import de.joshizockt.telegram.api.java.TelegramAPI;

import java.io.File;

public class GroupChatEvent extends ChatEvent {

    GroupChatEvent(TelegramAPI api, Chat chat, Message message) {
        super(api, chat, message);
    }

    @Override
    public com.pengrad.telegrambot.model.Message sendMessage(String message) {
        return getGroupChat().sendMessage(message);
    }

    @Override
    public com.pengrad.telegrambot.model.Message sendFile(File file) {
        return null;
    }

    public GroupChat getGroupChat() {
        return (GroupChat) getChat();
    }

}
