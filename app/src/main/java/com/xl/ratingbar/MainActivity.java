package com.xl.ratingbar;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MyRatingBar.OnRatingChangeListener {

    private TextView text1;
    private TextView text2;
    private TextView text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);

        MyRatingBar bar1 = (MyRatingBar)findViewById(R.id.bar1);
        MyRatingBar bar2 = (MyRatingBar)findViewById(R.id.bar2);
        MyRatingBar bar3 = (MyRatingBar)findViewById(R.id.bar3);

        bar1.setOnRatingChangeListener(this);
        bar2.setOnRatingChangeListener(this);
        bar3.setOnRatingChangeListener(this);
    }

    @Override
    public void onRatingChange(MyRatingBar bar, float RatingCount) {
        switch (bar.getId()) {
            case R.id.bar1:
                text1.setText(getResources().getString(R.string.score).concat(String.valueOf(RatingCount)));
                break;
            case R.id.bar2:
                text2.setText(getResources().getString(R.string.score).concat(String.valueOf(RatingCount)));
                break;
            case R.id.bar3:
                changeByStar(bar,RatingCount,text3);
                break;
        }
    }

    private void changeByStar(MyRatingBar bar,float star,TextView textView){
        Drawable amimia = ContextCompat.getDrawable(this, R.mipmap.amimia);
        Drawable crying = ContextCompat.getDrawable(this, R.mipmap.crying);
        Drawable smiling = ContextCompat.getDrawable(this, R.mipmap.smiling);

        if (star == 1.0f) {
            chage(bar, textView, amimia, crying, getResources().getString(R.string.Worst));
        }else if (star == 2.0f) {
            chage(bar, textView, amimia, crying, getResources().getString(R.string.Bad));
        }else if (star == 3.0f) {
            chage(bar, textView, amimia, smiling, getResources().getString(R.string.Good));
        }else if (star == 4.0f) {
            chage(bar, textView, amimia, smiling, getResources().getString(R.string.Better));
        }else if (star == 5.0f) {
            chage(bar, textView, amimia, smiling, getResources().getString(R.string.Best));
        }
    }

    private void chage(MyRatingBar bar, TextView textView, Drawable empty, Drawable full, String string) {
        textView.setText(string);
        bar.setStarEmptyDrawable(empty);
        bar.setStarFillDrawable(full);
    }
}
