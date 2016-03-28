package at.alextornoreanu.thegesichtgedicht.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.inject.Inject;

import at.alextornoreanu.thegesichtgedicht.R;
import at.alextornoreanu.thegesichtgedicht.model.Poem;
import at.alextornoreanu.thegesichtgedicht.services.PoemsDbService;
import at.alextornoreanu.thegesichtgedicht.services.SavedDataService;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActionBarActivity {
    private static final String TAG = "MainActivity";
    @Inject SavedDataService savedDataService;
    @Inject PoemsDbService poemsDbService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testDb();
    }

    private void testDb() {
        Cursor cursor = poemsDbService.getNrandomPoems();
//        while (!cursor.isAfterLast()) {
//            System.out.println(cursor.getString(2));
//            cursor.moveToNext();
//        }
        Poem poem = new Poem(cursor.getString(1), cursor.getString(2), cursor.getString(0));
        Log.d(TAG, poem.toString());
        savedDataService.saveToLastPoem(poem);
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
