package com.ideotechs.photoeditorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import ly.img.android.ui.activities.CameraPreviewActivity;
import ly.img.android.ui.activities.CameraPreviewIntent;
import ly.img.android.ui.activities.PhotoEditorIntent;

public class MainActivity extends AppCompatActivity {

    public static int CAMERA_PREVIEW_RESULT = 1;
    private Button cameraBtn,liveCameraBtn,editorBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        cameraBtn = (Button)findViewById(R.id.camera);
        liveCameraBtn = (Button)findViewById(R.id.livecamera);
        editorBtn = (Button)findViewById(R.id.editor);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CameraPreviewIntent(MainActivity.this)
                        .setExportDir(CameraPreviewIntent.Directory.DCIM, "PhotoEditorApp")
                        .setExportPrefix("PhotoEditorApp_")
                        .setEditorIntent(
                                new PhotoEditorIntent(MainActivity.this)
                                        .setExportDir(PhotoEditorIntent.Directory.DCIM, "PhotoEditorApp")
                                        .setExportPrefix("PhotoEditorApp_result_")
                                        .destroySourceAfterSave(true)
                        )
                        .startActivityForResult(CAMERA_PREVIEW_RESULT);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CAMERA_PREVIEW_RESULT) {
            String path = data.getStringExtra(CameraPreviewActivity.RESULT_IMAGE_PATH);

            Toast.makeText(this, "Image Save on: " + path, Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
