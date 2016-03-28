package at.alextornoreanu.thegesichtgedicht.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import at.alextornoreanu.thegesichtgedicht.R;
import at.alextornoreanu.thegesichtgedicht.services.PoemsDbService;
import at.alextornoreanu.thegesichtgedicht.model.Poem;
import at.alextornoreanu.thegesichtgedicht.services.DependencyContainer;
import at.alextornoreanu.thegesichtgedicht.services.SavedDataService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SavedDataService savedDataService;
    private PoemsDbService poemsDbService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpDependencies();
        testDb();
    }

    private void setUpDependencies() {
        DependencyContainer.createInstance(this);
        savedDataService = DependencyContainer.getInstance().savedDataService;
        poemsDbService = DependencyContainer.getInstance().poemsDbService;
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
