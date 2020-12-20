package fr.assemblee.calendrier.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import fr.assemblee.calendrier.Program;
import fr.assemblee.calendrier.entities.Calendar;
import fr.assemblee.calendrier.entities.Entry;

public class CalendarViewModel extends ViewModel {

    private MutableLiveData<Date> day;
    private Calendar calendar;
    private Events events;

    private MutableLiveData<List<Entry>> entries;

    public CalendarViewModel() {
        day = new MutableLiveData<>();
    }

    public LiveData<String> getDay() {
        return day;
    }

    public LiveData<List<Entry>> getEntries() {
        return entries;
    }

    public void refresh(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    calendar = Program.getCalendarDao().getCalendar();
                    parseCalendar();
                    events.refreshDone();
                } catch (IOException e) {
                    events.refreshError(e);
                }
            }
        });
    }

    private void parseCalendar() {

    }

    public static interface Events {
        void refreshDone();
        void refreshError(Exception e);
    }
}