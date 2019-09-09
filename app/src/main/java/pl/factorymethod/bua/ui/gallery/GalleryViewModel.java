package pl.factorymethod.bua.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.framgia.library.calendardayview.data.IEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.factorymethod.bua.model.Event;

public class GalleryViewModel extends ViewModel {

    private Map<LocalDate, List<Event>> eventMap = new HashMap<>();
    private MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void putDate(LocalDate localDate, ArrayList<Event> eventList) {
        eventMap.put(localDate, eventList);
    }

    public List<? extends IEvent> get(Object tag) {
        return eventMap.get(tag);
    }
}