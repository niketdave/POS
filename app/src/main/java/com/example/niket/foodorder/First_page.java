package com.example.niket.foodorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class First_page extends AppCompatActivity {
    Button go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go=(Button)findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(First_page.this,Main_Activity.class);
                startActivity(intent);
                Toast T=Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT);
                T.show();
            }
        });
    }
}
