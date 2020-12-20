package fr.assemblee.calendrier;

import fr.assemblee.calendrier.dao.CalendarDao;
import fr.assemblee.calendrier.dao.CalendarFromHttpApiDao;

public abstract class Program {
    private static CalendarDao calendarDao;

    public static CalendarDao getCalendarDao(){
        if(calendarDao != null) calendarDao = new CalendarFromHttpApiDao("http://127.0.0.1:8080");
        return calendarDao;
    }
}
