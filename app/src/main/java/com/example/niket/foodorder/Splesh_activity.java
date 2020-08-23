package com.example.niket.foodorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Splesh_activity extends AppCompatActivity {

    Constant time=new Constant();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splesh_activity);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
               /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splesh_activity.this,First_page.class);
                Splesh_activity.this.startActivity(mainIntent);
                Splesh_activity.this.finish();
            }
        },time.Time);
    }
}
