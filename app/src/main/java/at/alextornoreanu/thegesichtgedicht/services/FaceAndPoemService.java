package at.alextornoreanu.thegesichtgedicht.services;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.google.android.gms.vision.face.Face;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import at.alextornoreanu.thegesichtgedicht.model.Poem;

/**
 * Created by alex on 28.03.16.
 */
@Singleton
public class FaceAndPoemService {
    public static final String FACE_FILENAME = "gesicht.png";
    private Face face;
    private Bitmap facePicture;
    private Context context;
    @Inject SavedDataService savedDataService;
    @Inject PoemsDbService poemsDbService;

    @Inject
    public FaceAndPoemService(Context context) {
        this.context = context;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public Bitmap getFacePicture() {
        return rotateAndMirrorBitmap(facePicture);
    }

    public void setFacePicture(Bitmap facePicture) {
        this.facePicture = facePicture;
    }

    public void findGesichtGedicht() {
        Cursor cursor = poemsDbService.getNrandomPoems();
        Poem poem = new Poem(cursor.getString(1), cursor.getString(2), cursor.getString(0));
        savedDataService.saveToLastPoem(poem);
        saveFaceBitmapToDisk();
    }

    private void saveFaceBitmapToDisk() {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(FACE_FILENAME, Context.MODE_PRIVATE);
            facePicture.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Bitmap readFaceBitmapFromDisk() throws FileNotFoundException{
        FileInputStream fileInputStream = context.openFileInput(FaceAndPoemService.FACE_FILENAME);
        Bitmap faceBitmap = BitmapFactory.decodeStream(fileInputStream);
        return rotateAndMirrorBitmap(faceBitmap);
    }

    private Bitmap rotateAndMirrorBitmap(Bitmap input) {
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        matrix.postRotate(-90);
        return Bitmap.createBitmap(input, 0, 0, input.getWidth(), input.getHeight(), matrix, true);
    }
}
