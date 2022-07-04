package app.events;

import entities.User;
import javafx.event.Event;
import javafx.event.EventType;

public class UserEvent extends Event {
    // Custom events are following
    public static final EventType<UserEvent> LOGIN = new EventType<>(Event.ANY, "ANY");
    public static final EventType<UserEvent> ANY = LOGIN;
    public static final EventType<UserEvent> LOGIN_SUCCESSFULL = new EventType<>(UserEvent.ANY, "LOGIN_SUCCESSFULL");
    public static final EventType<UserEvent> LOG_OUT = new EventType<>(UserEvent.ANY, "LOG_OUT");
    // Storing the user
    private static User loggedInUser = null;

    // Constructor
    public UserEvent(EventType<? extends Event> eventType, User user) {
        super(eventType);

        loggedInUser = user;
    }

    // getter for the user
    public static User getUser() {
        return loggedInUser;
    }
}
