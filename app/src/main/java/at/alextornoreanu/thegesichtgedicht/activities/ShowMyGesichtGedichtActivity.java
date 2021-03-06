package at.alextornoreanu.thegesichtgedicht.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.inject.Inject;

import at.alextornoreanu.thegesichtgedicht.R;
import at.alextornoreanu.thegesichtgedicht.application.TheGesichtGedicht;
import at.alextornoreanu.thegesichtgedicht.model.Poem;
import at.alextornoreanu.thegesichtgedicht.services.SavedDataService;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by alex on 28.03.16.
 */
@ContentView(R.layout.activity_showlast)
public class ShowMyGesichtGedichtActivity extends RoboActionBarActivity {
    private static final String TAG = "ShowLastPoemActivity";
    private Tracker mTracker;

    @InjectView(R.id.poemTitle) TextView titleView;
    @InjectView(R.id.poemText) TextView textView;
    @InjectView(R.id.poemAuthor) TextView authorView;

    @Inject SavedDataService savedDataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        googleAnalyticsGetTracker();
        setTextViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        googleAnalyticsTrackActivity();
    }

    private void setTextViews() {
        Poem poem = savedDataService.getYourGedicht();
        titleView.setText(poem.getTitle());
        textView.setText(poem.getText());
        authorView.setText(poem.getAuthor());
    }

    private void googleAnalyticsGetTracker() {
        TheGesichtGedicht application = (TheGesichtGedicht) getApplication();
        mTracker = application.getDefaultTracker();
    }

    private void googleAnalyticsTrackActivity() {
        mTracker.setScreenName("ShowMyGesichtGedichtActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
