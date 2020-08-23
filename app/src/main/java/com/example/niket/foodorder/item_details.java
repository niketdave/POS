package com.example.niket.foodorder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.Serializable;
public class item_details extends AppCompatActivity {

    ImageView imageView;
    TextView txname,txprice,hiddenid;
    EditText ed1;
    Button order;
    String name;

    public DatabaseHepler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        imageView=(ImageView)findViewById(R.id.imgdisplay);
        txname=(TextView)findViewById(R.id.itemname);
        txprice=(TextView)findViewById(R.id.itemprise);
        ed1=(EditText)findViewById(R.id.edittext);
        order=(Button)findViewById(R.id.addtoorder);
        hiddenid=(TextView)findViewById(R.id.hiddenid);
         db=new DatabaseHepler(this);
        Intent getImage = getIntent();
        String gettingImageUrl = getImage.getStringExtra("image_id");

        Glide.with(item_details.this).load(gettingImageUrl)
                .placeholder(R.drawable.album1)
                .error(R.drawable.album1)
                .into(imageView);
        txname.setText("Name : "+getIntent().getStringExtra("Name"));
        txprice.setText(""+getIntent().getIntExtra("price",0));
        hiddenid.setText(""+getIntent().getIntExtra("id",0));
      //  txprice.setText("price :"+getIntent().getIntExtra("price",0));


    }

    // final String price=txprice.getText().toString();

    // final

    @Override
    protected void onStart() {
        super.onStart();
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ed1.getText().toString().length() == 0)
                {
                    Toast.makeText(item_details.this, "Enter Quantity first", Toast.LENGTH_SHORT).show();
                }
                else {
                    name = txname.getText().toString();
                    int price = Integer.parseInt(txprice.getText().toString());
                    int id = Integer.parseInt(hiddenid.getText().toString());
                    int val = Integer.parseInt(ed1.getText().toString());
                    int total = val * price;
                    System.out.print(total);
                    String s_price = Integer.toString(price);
                    String s_val = Integer.toString(val);
                    String s_total = Integer.toString(total);
                    String id_hi = Integer.toString(id);
                    db.insertData(id_hi, name, s_price, s_val, s_total);
                    db.updateData(id_hi, name, s_price, s_val, s_total);
                    Intent intent = new Intent(item_details.this, Order_details.class);
                    startActivity(intent);
                }
            }
        });
    }
}
