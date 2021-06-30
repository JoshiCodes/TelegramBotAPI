package de.joshizockt.telegram.api.java;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import de.joshizockt.telegram.api.entities.*;
import de.joshizockt.telegram.api.event.events.ChatEvent;

import java.io.File;
import java.util.List;

public class TelegramUpdateListener implements UpdatesListener {

    private final TelegramAPI api;

    TelegramUpdateListener(TelegramAPI api) {
        this.api = api;
    }

    @Override
    public int process(List<Update> list) {

        for(Update u : list) {
            parse(u);
        }

        return CONFIRMED_UPDATES_ALL;
    }

    private void parse(Update u) {
        com.pengrad.telegrambot.model.Message teleMsg = u.message();
        com.pengrad.telegrambot.model.User teleUser = teleMsg.from();
        com.pengrad.telegrambot.model.Chat teleChat = teleMsg.chat();
        boolean privateChat = (teleMsg.chat().firstName() != null);
        User user = new User() {
                @Override
                public com.pengrad.telegrambot.model.Message sendMessage(String message) {
                    SendMessage request = new SendMessage(teleChat.id(), message);
                    return api.getBot().execute(request).message();
                }
                @Override
                public com.pengrad.telegrambot.model.Message sendFile(File file) {
                    SendPhoto request = new SendPhoto(teleChat.id(), file);
                    return api.getBot().execute(request).message();
                }

                @Override
                public void sendTyping() {

                }

            @Override
                public String getFirstName() {
                    return teleUser.firstName();
                }
                @Override
                public String getLastName() {
                    return teleUser.lastName();
                }
                @Override
                public boolean isBot() {
                    return teleUser.isBot();
                }
                @Override
                public String getUsername() {
                    return teleUser.username();
                }
                @Override
                public boolean haveUsername() {
                    return getUsername() != null;
                }
                @Override
                public boolean canJoinGroups() {
                    return teleUser.canJoinGroups();
                }
                @Override
                public boolean canReadGroupMessages() {
                    return teleUser.canReadAllGroupMessages();
                }
            };
        Chat chat;
        if(privateChat) {
            PrivateChat c = new PrivateChat(api, teleChat.id());
            c.setFirstName(teleChat.firstName());
            c.setLastName(teleChat.lastName());
            chat = c;
        } else {
            GroupChat c = new GroupChat(api, teleChat.id());
            c.setInvite(teleChat.inviteLink());
            c.setTitle(teleChat.title());
            chat = c;
        }
        Message msg = new Message() {
            @Override
            public String getContent() {
                return teleMsg.text();
            }
            @Override
            public User getSender() {
                return user;
            }
            @Override
            public com.pengrad.telegrambot.model.Message getReal() {
                return teleMsg;
            }
            @Override
            public Chat getChat() {
                return chat;
            }
        };
        ChatEvent event = ChatEvent.from(api, chat, msg);
        if(event == null) return;
        api.getEventManager().fire(event);
    }

}
