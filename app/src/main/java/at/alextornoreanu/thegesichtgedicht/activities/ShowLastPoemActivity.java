package at.alextornoreanu.thegesichtgedicht.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.google.inject.Inject;

import at.alextornoreanu.thegesichtgedicht.R;
import at.alextornoreanu.thegesichtgedicht.model.Poem;
import at.alextornoreanu.thegesichtgedicht.services.SavedDataService;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by alex on 28.03.16.
 */
@ContentView(R.layout.activity_showlast)
public class ShowLastPoemActivity extends RoboActionBarActivity {
    private static final String TAG = "ShowLastPoemActivity";

    @InjectView(R.id.poemTitle) TextView titleView;
    @InjectView(R.id.poemText) TextView textView;
    @InjectView(R.id.poemAuthor) TextView authorView;

    @Inject SavedDataService savedDataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTextViews();
    }

    private void setTextViews() {
        Poem poem = savedDataService.getSavedLastPoem();
        titleView.setText(poem.getTitle());
        textView.setText(poem.getText());
        authorView.setText(poem.getAuthor());
    }
}
