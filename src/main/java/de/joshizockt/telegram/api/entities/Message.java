package de.joshizockt.telegram.api.entities;

public interface Message {

    String getContent();
    User getSender();
    com.pengrad.telegrambot.model.Message getReal();

}
