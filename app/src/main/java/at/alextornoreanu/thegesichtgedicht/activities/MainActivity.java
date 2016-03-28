package at.alextornoreanu.thegesichtgedicht.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import at.alextornoreanu.thegesichtgedicht.R;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActionBarActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void findNewGedicht(View view) {
        Log.d(TAG, "findNewGedicht");
        Intent intent = new Intent(this, FaceTrackerActivity.class);
        startActivity(intent);
    }

    public void showLastGedicht(View view) {
        Log.d(TAG, "showLastGedicht");
        Intent intent = new Intent(this, ShowLastPoemActivity.class);
        startActivity(intent);
    }
}
