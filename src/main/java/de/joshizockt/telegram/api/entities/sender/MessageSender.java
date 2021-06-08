package de.joshizockt.telegram.api.entities.sender;

import com.pengrad.telegrambot.model.Message;

import java.io.File;

public interface MessageSender {

    Message sendMessage(String message);
    Message sendFile(File file);
    void sendTyping();
    // sendInline
    // sendButtons

}
