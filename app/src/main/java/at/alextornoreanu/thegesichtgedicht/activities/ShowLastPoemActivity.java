package at.alextornoreanu.thegesichtgedicht.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import at.alextornoreanu.thegesichtgedicht.R;
import at.alextornoreanu.thegesichtgedicht.model.Poem;
import at.alextornoreanu.thegesichtgedicht.services.DependencyContainer;
import at.alextornoreanu.thegesichtgedicht.services.SavedDataService;

/**
 * Created by alex on 28.03.16.
 */
public class ShowLastPoemActivity extends AppCompatActivity {
    private static final String TAG = "ShowLastPoemActivity";

    private TextView mTitle;
    private TextView mText;
    private TextView mAuthor;

    private SavedDataService savedDataService = DependencyContainer.getInstance().savedDataService;

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
        Poem poem = savedDataService.getSavedLastPoem();
        Log.d(TAG, poem.toString());
        mTitle.setText(poem.getTitle());
        mText.setText(poem.getText());
        mAuthor.setText(poem.getAuthor());
    }
}
