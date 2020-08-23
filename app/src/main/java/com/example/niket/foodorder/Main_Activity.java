package com.example.niket.foodorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.Toast;

public class Main_Activity extends AppCompatActivity {
Button menu,order,feedback,suggestion,aboutus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);
menu=(Button)findViewById(R.id.menu);
        order=(Button)findViewById(R.id.Order);
        feedback=(Button)findViewById(R.id.FeedBack);
        suggestion=(Button)findViewById(R.id.Suggestion);
        aboutus=(Button)findViewById(R.id.About);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main_Activity.this, Menu_final.class);
                startActivity(intent);
                Toast T=Toast.makeText(getApplicationContext(),"Menu",Toast.LENGTH_SHORT);
                T.show();
            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main_Activity.this,About_us.class);
                startActivity(intent);
                Toast T=Toast.makeText(getApplicationContext(),"About us",Toast.LENGTH_SHORT);
                T.show();
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main_Activity.this,FeedBack.class);
                startActivity(intent);
                Toast T=Toast.makeText(getApplicationContext(),"FeedBack",Toast.LENGTH_SHORT);
                T.show();
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main_Activity.this,Order_details.class);
                startActivity(intent);
                Toast T=Toast.makeText(getApplicationContext(),"Order Details",Toast.LENGTH_SHORT);
                T.show();
            }
        });
        suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main_Activity.this,Rate_us.class);
                startActivity(intent);
                Toast T=Toast.makeText(getApplicationContext(),"Order Details",Toast.LENGTH_SHORT);
                T.show();
            }
        });

    }
}
