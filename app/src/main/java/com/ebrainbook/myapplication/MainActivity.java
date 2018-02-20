package com.ebrainbook.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mImageView = (ImageView) findViewById(R.id.image);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

//                new Thread(new Runnable() {
//                    public void run() {
////                        Bitmap b = ((BitmapDrawable)loadImageFromNetwork("https://developer.android.com/static/images/android_logo.png")).getBitmap();
//                        Bitmap b = loadImageFromNetwork("https://developer.android.com/static/images/android_logo.png");
//                        mImageView.setImageBitmap(b);
//                    }
//                }).start();

                new Thread(new Runnable(){
                    Drawable drawable = loadImageFromNetwork("https://developer.android.com/static/images/android_logo.png");
                    @Override
                    public void run() {

                        // post() 特别关键，就是到UI主线程去更新图片
                        mImageView.post(new Runnable(){
                            @Override
                            public void run() {
                                
                                // TODO Auto-generated method stub
                                mImageView.setImageDrawable(drawable) ;
                            }}) ;
                    }

                }).start()  ;
            }
        });


    }

    private Drawable loadImageFromNetwork(String imageUrl) {
        Drawable drawable = null;
        try { // 可以在这里通过文件名来判断，是否本地有此图片
             drawable = Drawable.createFromStream( new URL(imageUrl).openStream(), "image.jpg");
        } catch (IOException e) {
            Log.d("test", e.getMessage());
        }
        if (drawable == null) {
            Log.d("test", "null drawable");
        } else {
            Log.d("test", "not null drawable");
        }
        return drawable ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
