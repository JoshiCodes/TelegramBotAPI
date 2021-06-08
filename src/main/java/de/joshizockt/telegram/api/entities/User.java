package de.joshizockt.telegram.api.entities;

import de.joshizockt.telegram.api.entities.sender.MessageSender;

public interface User extends MessageSender {

    String getFirstName();
    String getLastName();
    boolean isBot();
    String getUsername();
    boolean haveUsername();
    boolean canJoinGroups();
    boolean canReadGroupMessages();

}
