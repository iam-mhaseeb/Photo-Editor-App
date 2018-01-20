package com.ideotechs.photoeditorapp;

import ly.img.android.ImgLySdk;

/**
 * Created by Haseeb on 20/01/2018.
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ImgLySdk.init(this);
    }


}
