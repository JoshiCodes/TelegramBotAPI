package de.joshizockt.telegram.api.commands;

import de.joshizockt.telegram.api.event.EventHandler;
import de.joshizockt.telegram.api.event.EventListener;
import de.joshizockt.telegram.api.event.events.PrivateChatEvent;
import de.joshizockt.telegram.api.java.TelegramAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandManager implements EventListener {

    private boolean useGlobal = true;
    private final TelegramAPI api;
    private final HashMap<Command, CommandType> commands;

    @EventHandler
    public void onPrivateChat(PrivateChatEvent e) {
        if(!e.getMessage().getContent().startsWith("/")) return;
        String[] args = e.getMessage().getContent().split(" ");
        Command command = null;
        for(Command cmd : getByType(CommandType.PRIVATE_ONLY)) {
            if(args[0].equalsIgnoreCase("/" + cmd.getName())) {
                command = cmd;
            }
            if(cmd.getAliases() != null) {
                for(String alias : cmd.getAliases()) {
                    if (args[0].equalsIgnoreCase("/" + alias)) {
                        command = cmd;
                        break;
                    }
                }
            }
        }
        if(command == null) return;
        command.execute(e.getUser(), e.getMessage(), args, e);
    }

    public CommandManager(TelegramAPI api) {
        this.api = api;
        this.commands = new HashMap<>();

        api.getEventManager().registerListener(this);

    }

    public void setUseGlobal(boolean useGlobal) {
        this.useGlobal = useGlobal;
    }

    public List<Command> getByType(CommandType type) {
        List<Command> list = new ArrayList<>();
        for(Command cmd : commands.keySet()) {
            if(commands.get(cmd) == type || (useGlobal && commands.get(cmd) == CommandType.GLOBAL)) {
                list.add(cmd);
            }
        }
        return list;
    }

    public HashMap<Command, CommandType> getCommands() {
        return commands;
    }

}
