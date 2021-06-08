package de.joshizockt.telegram.api.event;

import de.joshizockt.telegram.api.java.TelegramAPI;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventManager {

    private final List<EventListener> listeners;

    public EventManager() {
        this.listeners = new ArrayList<>();
    }

    public void registerListener(EventListener listener) {
        listeners.add(listener);
    }

    public void registerListener(EventListener... listener) {
        listeners.addAll(Arrays.asList(listener));
    }

    public void fire(Event event) {
        for(EventListener listener : listeners) {
            Method[] methods = listener.getClass().getDeclaredMethods();
            for(Method m : methods) {
                if(m.isAnnotationPresent(EventHandler.class)) {
                    if(m.getParameters()[0].getType().getSuperclass() == Event.class || (m.getParameters()[0].getType().getSuperclass().getSuperclass() != null && m.getParameters()[0].getType().getSuperclass().getSuperclass() == Event.class)) {
                        if( m.getParameters()[0].getType() == event.getClass() ) {
                            try {
                                m.setAccessible(true);
                                m.invoke(listener, event);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

}
