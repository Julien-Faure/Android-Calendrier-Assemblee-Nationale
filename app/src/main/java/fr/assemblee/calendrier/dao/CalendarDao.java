package fr.assemblee.calendrier.dao;

import java.io.IOException;

import fr.assemblee.calendrier.entities.Calendar;

public interface CalendarDao {
    Calendar getCalendar() throws IOException;
}
