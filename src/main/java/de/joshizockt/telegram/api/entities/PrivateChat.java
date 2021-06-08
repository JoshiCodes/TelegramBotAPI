package de.joshizockt.telegram.api.entities;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import de.joshizockt.telegram.api.entities.sender.MessageSender;
import de.joshizockt.telegram.api.java.TelegramAPI;

import java.io.File;

public class PrivateChat extends Chat implements MessageSender {

    private final TelegramAPI api;

    private String firstName;
    private String lastName;
    private final long id;

    public PrivateChat(TelegramAPI api, long id) {
        super();
        this.api = api;
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public Message sendMessage(String message) {
        SendMessage request = new SendMessage(id, message);
        return api.getBot().execute(request).message();
    }

    @Override
    public Message sendFile(File file) {
        return null;
    }

    @Override
    public void sendTyping() {
    }

}
