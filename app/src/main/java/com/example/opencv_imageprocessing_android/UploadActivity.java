package com.example.opencv_imageprocessing_android;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
//****************************************************************************************************
public class UploadActivity extends AppCompatActivity    {

    private static final String TAG = "UploadActivity";
    private static final int PICK_IMAGE_REQUEST = 1;

    private Button ButtonChoose;
    private Button ButtonUpload;
    private TextView ShowUploads;
    private EditText Dosya_adi;
    private ImageView ImageView;
    private ProgressBar mProgressBar;

    private Uri ImageUri;

    private StorageReference StorageRef;
    private DatabaseReference DatabaseRef;
    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        ButtonChoose = findViewById(R.id.buton_choose);
        ButtonUpload = findViewById(R.id.buton_upload);
        ShowUploads = findViewById(R.id.text_view_show_uploads);
        Dosya_adi = findViewById(R.id.dosya_adi);
        ImageView = findViewById(R.id.image_view);
        mProgressBar = findViewById(R.id.progress_bar);

        StorageRef = FirebaseStorage.getInstance().getReference("uploads/");
        DatabaseRef = FirebaseDatabase.getInstance().getReference("uploads/");

        ButtonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        ButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(UploadActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        });

        ShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            HomePageacar();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            ImageUri = data.getData();
            ImageView.setImageURI(ImageUri);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile() {
        if (ImageUri != null) {
            final StorageReference fileReference = StorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(ImageUri));

            mUploadTask = fileReference.putFile(ImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    Log.d(TAG,"onSuccess: uri= "+ uri.toString());

                                    Upload upload = new Upload(Dosya_adi.getText().toString().trim(),
                                            uri.toString());

                                    String uploadId = DatabaseRef.push().getKey();
                                    DatabaseRef.child(uploadId).setValue(upload);
                                }
                            });
                            Toast.makeText(UploadActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UploadActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void HomePageacar(){
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }
}
