package com.example.niket.foodorder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.attr.order;

public class Order_details extends AppCompatActivity {
    String DataParseUrl = "http://34.213.254.224/insert_data_order.php";
    public DatabaseHepler myDb;
    StringBuffer buffer = new StringBuffer();
    private List<StringBuffer> datalist = new ArrayList<>();
   ListView l1;
    EditText ed1;
    String data_order;
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
         myDb = new DatabaseHepler(this);
        l1=(ListView)findViewById(R.id.listview);
        ed1=(EditText)findViewById(R.id.ed_remove);
        b1=(Button)findViewById(R.id.deletebutton);
        b2=(Button)findViewById(R.id.placeorder);
        b3=(Button)findViewById(R.id.backtomenu);
        prepareData();
b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        myDb.deleteData(ed1.getText().toString());
        ed1.setText("");
        recreate();

    }
});
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetDataFromEditText();
                SendDataToServer(data_order);
                myDb.deleteDataall();
                Intent intent=new Intent(Order_details.this,Menu_final.class);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Order_details.this,Menu_final.class);
                startActivity(intent);

            }
        });

    }

    private void GetDataFromEditText() {
         data_order= buffer.toString();
        Toast.makeText(Order_details.this,data_order, Toast.LENGTH_LONG).show();
    }

    public void SendDataToServer(final String data_order) {
        class SendPostReqAsyncTask extends AsyncTask<String, String, String> {

            @Override
            protected String doInBackground(String... strings) {

                String qdata_order = data_order;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
              //  Toast.makeText(Order_details.this,qdata_order, Toast.LENGTH_LONG).show();
                nameValuePairs.add(new BasicNameValuePair("order_details", qdata_order));

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

                Toast.makeText(Order_details.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(data_order);
    }

    private void prepareData() {

        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            // show message
            showMessage("Error", "Nothing found");
            return;
        }

//        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append(res.getString(1) + "\n");
            buffer.append("Price :" + res.getString(2) + "\n");
            buffer.append("Quantity :" + res.getString(3) + "\n");
            buffer.append("ToTal :" + res.getString(4) + "\n\n");
        }
        datalist.add(buffer);
        final List<StringBuffer> order_list = new ArrayList<StringBuffer>(Arrays.<StringBuffer>asList(buffer));
        final ArrayAdapter<StringBuffer> arrayAdapter = new ArrayAdapter<StringBuffer>
                (this, android.R.layout.simple_list_item_1, order_list);

        // DataBind ListView with items from ArrayAdapter
        l1.setAdapter(arrayAdapter);
        // Show all data
        //showMessage("Data", buffer.toString());
    }


        public void showMessage(String title,String Message){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(Message);
            builder.show();
    }
}
