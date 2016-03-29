package at.alextornoreanu.thegesichtgedicht.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.inject.Inject;

import at.alextornoreanu.thegesichtgedicht.R;
import at.alextornoreanu.thegesichtgedicht.application.TheGesichtGedicht;
import at.alextornoreanu.thegesichtgedicht.services.FaceAndPoemService;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by alex on 28.03.16.
 */
@ContentView(R.layout.activity_find_poem)
public class FindPoemForFaceActivity extends RoboActionBarActivity {
    private Tracker mTracker;
    @InjectView(R.id.previewFace) ImageView imageView;
    @Inject FaceAndPoemService faceAndPoemService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        googleAnalyticsGetTracker();
        imageView.setImageBitmap(faceAndPoemService.getFacePicture());
    }

    @Override
    protected void onResume() {
        super.onResume();
        googleAnalyticsTrackActivity();
    }

    public void findGedichtForGesicht(View view) {
        faceAndPoemService.findGesichtGedicht();
        Intent intent = new Intent(this, ShowLastPoemActivity.class);
        startActivity(intent);
    }

    private void googleAnalyticsGetTracker() {
        TheGesichtGedicht application = (TheGesichtGedicht) getApplication();
        mTracker = application.getDefaultTracker();
    }

    private void googleAnalyticsTrackActivity() {
        mTracker.setScreenName("MainActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
