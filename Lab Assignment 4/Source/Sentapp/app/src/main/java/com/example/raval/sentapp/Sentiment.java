package com.example.raval.sentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.view.inputmethod.InputMethodManager;
//import org.json.JSONArray;

public class Sentiment extends AppCompatActivity {
    String sourceText;
    TextView outputTextView;
    ImageView outputImage;
    //  Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentiment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        outputTextView = (TextView) findViewById(R.id.txt_Result);
        outputImage =(ImageView)findViewById(R.id.img_result);
    }

    public void logout(View v) {
        Intent redirect = new Intent(Sentiment.this, Login.class);
        startActivity(redirect);
    }
    public void translateText(View v) {
        TextView sourceTextView = (TextView) findViewById(R.id.txt_Email);

        sourceText = sourceTextView.getText().toString();
        String getURL = "https://api.sentigem.com/external/get-sentiment?" +
                "api-key=6f30c1c09fd2d3e64921214da51407b0LeufsTjYtrgAmMDao2y64NqUc3PWvz5I" +"&text="+
                sourceText+"";//The API service URL
        //    final String response1 = "";
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request.Builder()
                    .url(getURL)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e.getMessage());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final JSONObject jsonResult;
                    final String result = response.body().string();
                    try {
                        jsonResult = new JSONObject(result);
                        //JSON convertedTextArray = jsonResult.getJSONArray("polarity");
                        final String convertedText = jsonResult.getString("polarity");
                        Log.d("okHttp", jsonResult.toString());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                outputTextView.setText(convertedText);
                                //outputTextView.setText(resultext);
                                //outputImage.setImageDrawable(myDrawable);


                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception ex) {
            outputTextView.setText(ex.getMessage());

        }

    }
}


/**  @Override
public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
getMenuInflater().inflate(R.menu.menu_sentiment, menu);
return true;
}
 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
 // Handle action bar item clicks here. The action bar will
 // automatically handle clicks on the Home/Up button, so long
 // as you specify a parent activity in AndroidManifest.xml.
 int id = item.getItemId();
 //noinspection SimplifiableIfStatement
 if (id == R.id.action_settings) {
 return true;
 }
 return super.onOptionsItemSelected(item);
 }
 **/
