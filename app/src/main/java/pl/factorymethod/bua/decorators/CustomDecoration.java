package pl.factorymethod.bua.decorators;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.TextView;

import com.framgia.library.calendardayview.EventView;
import com.framgia.library.calendardayview.data.IEvent;
import com.framgia.library.calendardayview.decoration.CdvDecorationDefault;

public class CustomDecoration extends CdvDecorationDefault {
    public CustomDecoration(Context context) {
        super(context);
    }

    @Override
    public EventView getEventView(IEvent event, Rect eventBound, int hourHeight, int seperateHeight) {
        final EventView eventView = super.getEventView(event, eventBound, hourHeight, seperateHeight);

        // hide event header
        TextView textHeader1 = eventView.findViewById(com.framgia.library.calendardayview.R.id.item_event_header_text1);
        TextView textHeader2 = eventView.findViewById(com.framgia.library.calendardayview.R.id.item_event_header_text2);

        textHeader1.setVisibility(View.GONE);
        textHeader2.setVisibility(View.GONE);

        return eventView;

    }
}
