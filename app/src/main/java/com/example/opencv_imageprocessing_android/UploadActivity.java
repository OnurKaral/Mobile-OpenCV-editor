package com.example.opencv_imageprocessing_android;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
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

        StorageRef = FirebaseStorage.getInstance().getReference("uploads");
        DatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

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
        if (ImageUri != null)
        {
            StorageRef.putFile(ImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
            {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();

                    }
                    return StorageRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>()
        {
            @Override
            public void onComplete(@NonNull Task<Uri> task)
            {

                if (task.isSuccessful())
                {
                    Uri downloadUri = task.getResult();
                    Log.e(TAG, "then: " + downloadUri.toString());

                    Upload upload = new Upload(Dosya_adi.getText().toString().trim(),
                            downloadUri.toString());

                    DatabaseRef.push().setValue(upload);
                    Toast.makeText(UploadActivity.this, "Upload Successful" , Toast.LENGTH_SHORT).show();

                } else
                {
                    Toast.makeText(UploadActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        }
    }

    private void HomePageacar(){
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }
}
