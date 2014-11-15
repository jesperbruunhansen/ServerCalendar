package Model.Calendar;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jesperbruun on 10/10/14.
 */
public class Events {
    ArrayList<Event> events = new ArrayList<Event>();

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> event) {
        this.events = event;
    }

    @Override
    public String toString() {
        return Arrays.toString(events.toArray());
    }
}