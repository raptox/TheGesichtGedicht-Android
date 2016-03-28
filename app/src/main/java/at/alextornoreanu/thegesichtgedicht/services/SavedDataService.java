package at.alextornoreanu.thegesichtgedicht.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import at.alextornoreanu.thegesichtgedicht.model.Poem;

/**
 * Created by alex on 28.03.16.
 */
@Singleton
public class SavedDataService {
    private Context mContext;
    private static final String POEM_TITLE = "POEM_TITLE";
    private static final String POEM_AUTHOR = "POEM_AUTHOR";
    private static final String POEM_TEXT = "POEM_TEXT";

    @Inject
    public SavedDataService(Context context) {
        mContext = context;
    }

    public boolean isPoemAvailable() {
        if (getSavedLastPoem().getAuthor().equals("N/A")) {
            return false;
        } else {
            return true;
        }
    }

    public void saveToLastPoem(Poem poem) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(POEM_TITLE, poem.getTitle());
        editor.putString(POEM_AUTHOR, poem.getAuthor());
        editor.putString(POEM_TEXT, poem.getText());
        editor.apply();
    }

    public Poem getSavedLastPoem() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
        Poem poem = new Poem();
        poem.setAuthor(sharedPref.getString(POEM_AUTHOR, "N/A"));
        poem.setText(sharedPref.getString(POEM_TEXT, "N/A"));
        poem.setTitle(sharedPref.getString(POEM_TITLE, "N/A"));
        return poem;
    }
}
