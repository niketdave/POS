package com.example.niket.foodorder;

import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Rate_us extends AppCompatActivity {
RatingBar ratingBar;
    private HelperforRating db;
    Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);
        ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        bt1=(Button)findViewById(R.id.submitrating);
        TextView tx=(TextView)findViewById(R.id.txt1);
        db=new HelperforRating(this);
        tx.setText(db.getAllData());
        bt1.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
                if (ratingBar.getRating() == 0 )
                    Toast.makeText(Rate_us.this, "Please enter rating to be added!", Toast.LENGTH_SHORT).show();
                else {
                    float rating=ratingBar.getRating();
                    db.addResult(rating);
                        Toast.makeText(Rate_us.this, "Results Added!", Toast.LENGTH_SHORT).show();
                    recreate();

                    new Handler().postDelayed(new Runnable() {
                    @Override
                        public void run() {

                            Intent i=new Intent(Rate_us.this,Main_Activity.class);
                            startActivity(i);
                    }
                }, 5000);
        }
    }
});


    }
}
