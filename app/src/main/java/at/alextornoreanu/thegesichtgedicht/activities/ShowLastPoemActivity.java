package at.alextornoreanu.thegesichtgedicht.activities;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import at.alextornoreanu.thegesichtgedicht.R;
import at.alextornoreanu.thegesichtgedicht.services.PoemsDbService;
import at.alextornoreanu.thegesichtgedicht.model.Poem;
import at.alextornoreanu.thegesichtgedicht.services.DependencyContainer;
import at.alextornoreanu.thegesichtgedicht.services.SavedDataService;

/**
 * Created by alex on 28.03.16.
 */
public class ShowLastPoemActivity extends Activity {
    private static final String TAG = "ShowLastPoemActivity";

    private TextView mTitle;
    private TextView mText;
    private TextView mAuthor;

    private SavedDataService savedDataService = DependencyContainer.getInstance().savedDataService;
    private PoemsDbService poemsDbService = DependencyContainer.getInstance().poemsDbService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showlast);
        initUi();
        setTextViews();
    }

    private void initUi() {
        mTitle = (TextView) findViewById(R.id.poemTitle);
        mText = (TextView) findViewById(R.id.poemText);
        mAuthor = (TextView) findViewById(R.id.poemAuthor);
    }

    private void setTextViews() {
        Cursor cursor = poemsDbService.getRandomPoems();
        Poem poem = new Poem(cursor.getString(1), cursor.getString(2), cursor.getString(0));
//        Poem poem = savedDataService.getSavedLastPoem();
        Log.d(TAG, poem.toString());
        mTitle.setText(poem.getTitle());
        mText.setText(poem.getText());
        mAuthor.setText(poem.getAuthor());
    }
}
