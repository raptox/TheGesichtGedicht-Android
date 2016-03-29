package at.alextornoreanu.thegesichtgedicht.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.inject.Inject;

import java.io.FileNotFoundException;

import at.alextornoreanu.thegesichtgedicht.R;
import at.alextornoreanu.thegesichtgedicht.application.TheGesichtGedicht;
import at.alextornoreanu.thegesichtgedicht.services.FaceAndPoemService;
import at.alextornoreanu.thegesichtgedicht.services.SavedDataService;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActionBarActivity {
    private static final String TAG = "MainActivity";
    private Tracker mTracker;
    @InjectView(R.id.yourGesicht) ImageView yourGesicht;
    @Inject SavedDataService savedDataService;
    @Inject FaceAndPoemService faceAndPoemService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        googleAnalyticsGetTracker();
        loadImageIntoYourGesicht();
    }

    @Override
    protected void onResume() {
        super.onResume();
        googleAnalyticsTrackActivity();
    }

    private void loadImageIntoYourGesicht() {
        if (savedDataService.isYourGedichtAvailable()) {
            setUserGesicht();
        } else {
            setPoetGesicht();
        }
    }

    private void setPoetGesicht() {
        Drawable poetDrawable = getResources().getDrawable(R.drawable.poet);
        yourGesicht.setImageDrawable(poetDrawable);
    }

    private void setUserGesicht() {
        try {
            Bitmap faceBitmap = faceAndPoemService.readFaceBitmapFromDisk();
            yourGesicht.setImageBitmap(faceBitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void findNewGedicht(View view) {
        Log.d(TAG, "findNewGedicht");
        Intent intent = new Intent(this, FaceCaptureActivity.class);
        startActivity(intent);
    }

    public void showLastGedicht(View view) {
        Log.d(TAG, "showLastGedicht");

        if (savedDataService.isYourGedichtAvailable()) {
            Intent intent = new Intent(this, ShowMyGesichtGedichtActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.msg_noGedichtAvailable, Toast.LENGTH_SHORT).show();
        }
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
