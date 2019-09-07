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

import java.util.ArrayList;
import java.util.Calendar;

import pl.factorymethod.bua.R;
import pl.factorymethod.bua.decorators.CustomDecoration;
import pl.factorymethod.bua.model.Event;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        CalendarDayView calendar = root.findViewById(R.id.dayView1);
        calendar.setDecorator(new CustomDecoration(getContext()));
        calendar.setLimitTime(10,19);
        ArrayList events = new ArrayList<IEvent>();
        TabLayout tablayout = root.findViewById(R.id.tabLayout);
        tablayout.addTab(tablayout.newTab().setText("TEEST"));

        int eventColor = ContextCompat.getColor(getContext(), R.color.eventColor);
        Calendar timeStart = Calendar.getInstance();
        timeStart.set(Calendar.HOUR_OF_DAY, 11);
        timeStart.set(Calendar.MINUTE, 0);
        timeStart.set(Calendar.DAY_OF_MONTH, 5);
        Calendar timeEnd = (Calendar) timeStart.clone();
        timeEnd.set(Calendar.HOUR_OF_DAY, 12);
        timeEnd.set(Calendar.MINUTE, 0);
        timeStart.set(Calendar.DAY_OF_MONTH, 5);
        Event event = new Event(1, timeStart, timeEnd, "Event", "Nazwisko 1", eventColor);
        event.setTitle("event 2 this is test");
        event.setDescription("Yuong alsdf");
        event.setQuote("Google map");
        events.add(event);

        calendar.setEvents(events);
        return root;
    }
}