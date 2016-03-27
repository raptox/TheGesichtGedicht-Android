package at.alextornoreanu.thegesichtgedicht.services;

import android.content.Context;

/**
 * Created by alex on 28.03.16.
 */
public class DependencyContainer {
    private static DependencyContainer instance = null;

    public SavedDataService savedDataService;
    public PoemsDbService poemsDbService;

    public DependencyContainer(Context context) {
        savedDataService = new SavedDataService(context);
        poemsDbService = new PoemsDbService(context);
    }

    public static void createInstance(Context context) {
        instance = new DependencyContainer(context);
    }

    public static DependencyContainer getInstance() {
        return instance;
    }
}
