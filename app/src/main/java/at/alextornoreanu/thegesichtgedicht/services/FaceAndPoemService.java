package at.alextornoreanu.thegesichtgedicht.services;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.google.android.gms.vision.face.Face;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import at.alextornoreanu.thegesichtgedicht.model.Poem;

/**
 * Created by alex on 28.03.16.
 */
@Singleton
public class FaceAndPoemService {
    private Face face;
    private Bitmap facePicture;
    @Inject SavedDataService savedDataService;
    @Inject PoemsDbService poemsDbService;

    public FaceAndPoemService() {

    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public Bitmap getFacePicture() {
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        matrix.postRotate(-90);
        return Bitmap.createBitmap(facePicture, 0, 0, facePicture.getWidth(), facePicture.getHeight(), matrix, true);
    }

    public void setFacePicture(Bitmap facePicture) {
        this.facePicture = facePicture;
    }

    public void findGesichtGedicht() {
        Cursor cursor = poemsDbService.getNrandomPoems();
        Poem poem = new Poem(cursor.getString(1), cursor.getString(2), cursor.getString(0));
        savedDataService.saveToLastPoem(poem);
    }
}
