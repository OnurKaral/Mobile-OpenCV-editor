package com.example.opencv_imageprocessing_android;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

//****************************************************************************************************
public class FirstImageProcess extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{
    static final String TAG = FirstImageProcess.class.getSimpleName();
    private JavaCameraView cameraView;
    private Mat source;
    private Button screenshoot;
    private Bitmap bitmap;
    private JavaCameraView parentView;

    private BaseLoaderCallback openCVLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {

            switch (status) {

                case LoaderCallbackInterface.SUCCESS:
                    Log.i(TAG, "OpenCV loaded successfully");
                    cameraView.enableView();
                    cameraView.setVisibility(View.VISIBLE);
                    break;

                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };
    //****************************************************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_image_process);

        cameraView = findViewById(R.id.camera);

        cameraView.setVisibility(View.VISIBLE);
        cameraView.setCvCameraViewListener(this);

        screenshoot = findViewById(R.id.screenshoot);
        parentView = findViewById(R.id.camera);


        screenshoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bitmap = ScreenshotUtil.getInstance().takeScreenshotForScreen(FirstImageProcess.this); // Take ScreenshotUtil for activity
                requestPermissionAndSave();

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (cameraView != null) {
            cameraView.disableView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!OpenCVLoader.initDebug()) {

            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_3_0, this, openCVLoaderCallback);

        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            openCVLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    protected void onDestroy() {

        if (cameraView != null) {
            cameraView.disableView();
        }
        super.onDestroy();
    }

    //****************************************************************************************************
    @Override
    public void onCameraViewStarted(int width, int height) {

        source = new Mat(height, width, CvType.CV_8UC4);
    }

    @Override
    public void onCameraViewStopped() {
        source.release();

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {

        source = inputFrame.rgba();

        Imgproc.cvtColor(source, source, Imgproc.COLOR_BGR2GRAY);
        Imgproc.blur(source, source, new Size(5., 5.));
        Imgproc.Canny(source, source, 100, 20);

        return source;
    }


    private void requestPermissionAndSave() {

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                        if (bitmap != null) {
                            String path = Environment.getExternalStorageDirectory().toString() + "/test.png";
                            FileUtil.getInstance().storeBitmap(bitmap, path);
                            Toast.makeText(FirstImageProcess.this, getString(R.string.toast_message_screenshot_success) + " " + path, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(FirstImageProcess.this, getString(R.string.toast_message_screenshot), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            Toast.makeText(FirstImageProcess.this, getString(R.string.settings_message), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
}
