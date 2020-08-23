package com.example.niket.foodorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedBack extends AppCompatActivity {

    EditText editTextName, editTextphoneno, editTextdiscription;

    String Name, phoneno, discription;
    Button buttonSubmit ;

    String DataParseUrl = "http://34.213.254.224/insert_data.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        editTextName = (EditText)findViewById(R.id.name);
        editTextphoneno = (EditText)findViewById(R.id.pnumber);
        editTextdiscription = (EditText)findViewById(R.id.discription);

        buttonSubmit = (Button)findViewById(R.id.go);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(editTextName.getText().toString().length()==0)
                {
                    Toast.makeText(FeedBack.this, "Should not be empty", Toast.LENGTH_SHORT).show();
                }
                else if(editTextphoneno.getText().toString().length() < 10)
                {
                    Toast.makeText(FeedBack.this, "Not valid number", Toast.LENGTH_SHORT).show();
                }
                else if(editTextdiscription.getText().toString().length() == 0)
                {
                    Toast.makeText(FeedBack.this, "Should not be empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    GetDataFromEditText();
                    SendDataToServer(Name, phoneno, discription);
                    editTextName.setText("");
                    editTextphoneno.setText("");
                    editTextdiscription.setText("");
                }


            }
        });

    }
    public void GetDataFromEditText(){

        Name = editTextName.getText().toString();
        phoneno = editTextphoneno.getText().toString();
        discription = editTextdiscription.getText().toString();

    }

    public void SendDataToServer(final String name, final String phoneno, final String website){
        class SendPostReqAsyncTask extends AsyncTask<String, String, String> {
            @Override
            protected String doInBackground(String... params) {

                String QuickName = name ;
                String Quickphoneno = phoneno ;
                String Quickdiscription = website;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("Name", QuickName));
                nameValuePairs.add(new BasicNameValuePair("Phone_No", Quickphoneno));
                nameValuePairs.add(new BasicNameValuePair("FeedBack", Quickdiscription));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(DataParseUrl);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Submit Successfully";
            }
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(FeedBack.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name, phoneno, website);
    }

}
