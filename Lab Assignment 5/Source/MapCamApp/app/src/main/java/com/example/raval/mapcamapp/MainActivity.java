package com.example.raval.mapcamapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    int TAKE_PHOTO_CODE = 0;
    int SELECT_FILE = 1;
    ImageView userImage;
    static Bitmap photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Button Gallary = (Button) findViewById(R.id.button2);

        Button Signup = (Button) findViewById(R.id.button);

        userImage = (ImageView) findViewById(R.id.imageView);
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent i = new Intent(MainActivity.this, MapsActivity.class);
                                          startActivity(i);
                                      }

                                  }
        );


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            userImage.setImageBitmap(photo);
            Log.d("CameraDemo", "Pic saved");
        }
        if (requestCode == SELECT_FILE && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            Bitmap bm = null;
            try {
                bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (Exception E) {
            }
            ;
            Bitmap bms = Bitmap.createScaledBitmap(bm, 250, 250, false);
            photo = bms;
            userImage.setImageBitmap(photo);

            Log.d("CameraDemo", "Gallery pic saved");
        }
    }
    public void address(View v){
        TextView txt = (TextView) findViewById(R.id.editText5);
        txt.setText("  Kansas City, Missouri");
    }
    public void upload(View v) {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);

    }


}