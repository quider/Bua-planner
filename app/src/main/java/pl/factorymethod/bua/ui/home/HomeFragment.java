package pl.factorymethod.bua.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.framgia.library.calendardayview.CalendarDayView;
import com.framgia.library.calendardayview.data.IEvent;
import com.google.android.material.tabs.TabLayout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import pl.factorymethod.bua.R;
import pl.factorymethod.bua.decorators.CustomDecoration;
import pl.factorymethod.bua.model.Event;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Map<LocalDate, List<Event>> eventMap = new HashMap<>();

    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final CalendarDayView calendar = root.findViewById(R.id.dayView1);
        calendar.setDecorator(new CustomDecoration(getContext()));
        calendar.setLimitTime(9, 19);
        final ArrayList events = new ArrayList<IEvent>();
        TabLayout tablayout = root.findViewById(R.id.tabLayout);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        LocalDate now = LocalDate.now();
        for (int i = 0; i < 10; i++) {
            LocalDate localDate = now.plusDays(i);
            TabLayout.Tab tab = tablayout.newTab();
            tab.setText(localDate.toString());
            tab.setTag(localDate);
            ArrayList<Event> eventList = new ArrayList<>();
            eventList.add(createEvent(10 + i, 0, 11 + i, 0));
            homeViewModel.putDate(localDate, eventList);

            tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    calendar.setEvents(homeViewModel.get(tab.getTag()));
                    calendar.refresh();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            tablayout.addTab(tab);

        }
        return root;
    }

    private Event createEvent(int hourStart, int minStart, int hourEnd, int minEnd) {
        int eventColor = ContextCompat.getColor(getContext(), R.color.eventColor);
        Calendar timeStart = Calendar.getInstance();
        timeStart.set(Calendar.HOUR_OF_DAY, hourStart);
        timeStart.set(Calendar.MINUTE, minStart);
        timeStart.set(Calendar.DAY_OF_MONTH, 5);
        Calendar timeEnd = (Calendar) timeStart.clone();
        timeEnd.set(Calendar.HOUR_OF_DAY, hourEnd);
        timeEnd.set(Calendar.MINUTE, minEnd);
        timeStart.set(Calendar.DAY_OF_MONTH, 5);
        Event event = new Event(UUID.randomUUID().toString(), timeStart, timeEnd, "Nazwisko 1 - rodzaj masażu", "Nazwisko 1", eventColor);
        event.setTitle("event 2 this is test");
        event.setDescription("Yuong alsdf");
        return event;
    }
}