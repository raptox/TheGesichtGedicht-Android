package at.alextornoreanu.thegesichtgedicht.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import at.alextornoreanu.thegesichtgedicht.R;
import at.alextornoreanu.thegesichtgedicht.database.PoemsDbHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testDb();
    }

    private void testDb() {
        PoemsDbHelper poemsDbHelper = new PoemsDbHelper(this);
        Cursor cursor = poemsDbHelper.getRandomPoems();
        while (!cursor.isAfterLast()) {
            System.out.println(cursor.getString(2));
            cursor.moveToNext();
        }
    }

    public void findNewGedicht(View view) {
        Log.d(TAG, "findNewGedicht");
        Intent i = new Intent(this, FaceTrackerActivity.class);
        startActivity(i);
    }

    public void showLastGedicht(View view) {
        Log.d(TAG, "showLastGedicht");
    }
}
