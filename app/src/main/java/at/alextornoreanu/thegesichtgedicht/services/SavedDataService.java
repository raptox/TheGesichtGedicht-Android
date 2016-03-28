package at.alextornoreanu.thegesichtgedicht.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import at.alextornoreanu.thegesichtgedicht.model.Poem;

/**
 * Created by alex on 28.03.16.
 */
public class SavedDataService {
    private Context mContext;
    private static final String POEM_TITLE = "POEM_TITLE";
    private static final String POEM_AUTHOR = "POEM_AUTHOR";
    private static final String POEM_TEXT = "POEM_TEXT";

    public SavedDataService(Context context) {
        mContext = context;
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
        poem.setAuthor(sharedPref.getString(POEM_AUTHOR, "No Autor"));
        poem.setText(sharedPref.getString(POEM_TEXT, "No Text"));
        poem.setTitle(sharedPref.getString(POEM_TITLE, "No Title"));
        return poem;
    }
}
