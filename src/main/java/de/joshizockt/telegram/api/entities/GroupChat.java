package de.joshizockt.telegram.api.entities;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import de.joshizockt.telegram.api.entities.sender.MessageSender;
import de.joshizockt.telegram.api.java.TelegramAPI;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;

public class GroupChat extends Chat implements MessageSender {

    private final TelegramAPI api;
    private final long id;

    private String title;
    private String invite;
    private List<User> users;

    public GroupChat(TelegramAPI api, long id) {
        this.api = api;
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getTitle() {
        return title;
    }

    public String getInvite() {
        return invite;
    }

    @Nullable
    public List<User> getUsers() {
        return users;
    }

    @Override
    public Message sendMessage(String message) {
        SendMessage request = new SendMessage(id, message);
        return api.getBot().execute(request).message();
    }

    @Override
    public Message sendFile(File file) {
        SendPhoto request = new SendPhoto(id, file);
        return api.getBot().execute(request).message();
    }

    @Override
    public void sendTyping() {

    }
}
