package de.joshizockt.telegram.api.java;

import com.pengrad.telegrambot.TelegramBot;
import de.joshizockt.telegram.api.commands.CommandManager;
import de.joshizockt.telegram.api.event.EventManager;
import de.joshizockt.telegram.api.util.Logger;

public class TelegramAPI {

    private final Logger logger;

    private final TelegramBot bot;
    private final EventManager eventManager;
    private final CommandManager commandManager;

    public TelegramAPI(String token) {
        this(token, false);
    }

    public TelegramAPI(String token, boolean debug) {

        long t = System.currentTimeMillis();

        this.logger = new Logger("TelegramBot", "HH:mm:ss", debug);
        if(debug) {
            logger.log(Logger.LogType.DEBUG, "Loading TelegramBot with Token '" + token + "'...");
        } else
            logger.log(Logger.LogType.INFO, "Loading TelegramBot...");

        bot = new TelegramBot(token);

        this.eventManager = new EventManager();
        this.commandManager = new CommandManager(this);

        bot.setUpdatesListener(new TelegramUpdateListener(this));

        logger.log(Logger.LogType.INFO, "TelegramBot is loaded and ready in " + (System.currentTimeMillis() - t) + "ms!");

    }

    public TelegramBot getBot() {
        return bot;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

}
