package com.xl.ratingbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.math.BigDecimal;

/**
 * Author: LI(jiaru.li@jescard.com)
 * Date: 2017-07-10
 * Time: 17:21
 *
 * rating star
 */

public class MyRatingBar extends LinearLayout {

    //light number
    private int starNum;
    //total
    private int starCount;
    private boolean halfstart;
    private boolean mClickable;
    private boolean mTouchable;
    private float starImageWidth;
    private float starImageHeight;
    private float starImagePadding;
    private Drawable starEmptyDrawable;
    private Drawable starFillDrawable;
    private Drawable starHalfDrawable;
    private boolean isEmpty=true;
    private MyRatingBar.OnRatingChangeListener onRatingChangeListener;

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public void setHalfstart(boolean halfstart) {
        this.halfstart = halfstart;
    }

    public void setmClickable(boolean mClickable) {
        this.mClickable = mClickable;
    }

    public void setmTouchable(boolean mTouchable) {
        this.mTouchable = mTouchable;
    }

    public void setStarImageWidth(float starImageWidth) {
        this.starImageWidth = starImageWidth;
    }

    public void setStarImageHeight(float starImageHeight) {
        this.starImageHeight = starImageHeight;
    }

    public void setStarImagePadding(float starImagePadding) {
        this.starImagePadding = starImagePadding;
    }

    public void setStarEmptyDrawable(Drawable starEmptyDrawable) {
        this.starEmptyDrawable = starEmptyDrawable;
    }

    public void setStarFillDrawable(Drawable starFillDrawable) {
        this.starFillDrawable = starFillDrawable;
    }

    public void setStarHalfDrawable(Drawable starHalfDrawable) {
        this.starHalfDrawable = starHalfDrawable;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public void setOnRatingChangeListener(OnRatingChangeListener onRatingChangeListener) {
        this.onRatingChangeListener = onRatingChangeListener;
    }


    public MyRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, com.xl.ratingbar.R.styleable.RatingBar);
        starHalfDrawable = mTypedArray.getDrawable(com.xl.ratingbar.R.styleable.RatingBar_starHalf);
        starEmptyDrawable = mTypedArray.getDrawable(com.xl.ratingbar.R.styleable.RatingBar_starEmpty);
        starFillDrawable = mTypedArray.getDrawable(com.xl.ratingbar.R.styleable.RatingBar_starFill);
        starImageWidth = mTypedArray.getDimension(com.xl.ratingbar.R.styleable.RatingBar_starImageWidth, 60);
        starImageHeight = mTypedArray.getDimension(com.xl.ratingbar.R.styleable.RatingBar_starImageHeight, 120);
        starImagePadding = mTypedArray.getDimension(com.xl.ratingbar.R.styleable.RatingBar_starImagePadding, 15);
        starCount = mTypedArray.getInteger(com.xl.ratingbar.R.styleable.RatingBar_starCount, 5);
        starNum = mTypedArray.getInteger(com.xl.ratingbar.R.styleable.RatingBar_starNum, 0);
        mClickable = mTypedArray.getBoolean(com.xl.ratingbar.R.styleable.RatingBar_clickable, true);
        mTouchable = mTypedArray.getBoolean(com.xl.ratingbar.R.styleable.RatingBar_touchable, false);
        halfstart = mTypedArray.getBoolean(com.xl.ratingbar.R.styleable.RatingBar_halfstart, false);

//        for (int i = 0; i < starNum; ++i) {
//            ImageView imageView = getStarImageView(context,false,i);
//            addView(imageView);
//        }

        for (int i = 0; i < starCount; ++i) {
            ImageView imageView = getStarImageView(context,isEmpty,i);
            addView(imageView);
        }

        if (starNum > 0) {
            setStar(starNum);
        }

        mTypedArray.recycle();
    }


    private ImageView getStarImageView(Context context,boolean isEmpty,int i) {
        ImageView imageView = new ImageView(context);

        float paraWidth = i ==  starCount - 1? starImageWidth : starImageWidth + starImagePadding;

        ViewGroup.LayoutParams para = new ViewGroup.LayoutParams(
                Math.round(paraWidth),
                Math.round(starImageHeight)
        );
        imageView.setLayoutParams(para);

        if (i != starCount-1) {
            imageView.setPadding(0, 0, Math.round(starImagePadding), 0);
        }

        if(isEmpty){
            imageView.setImageDrawable(starEmptyDrawable);
        }else{
            imageView.setImageDrawable(starFillDrawable);
        }
        return imageView;
    }

    public void setStar(float starCount) {

        int fint = (int) starCount;
        BigDecimal b1 = new BigDecimal(Float.toString(starCount));
        BigDecimal b2 = new BigDecimal(Integer.toString(fint));
        float fPoint = b1.subtract(b2).floatValue();


        starCount = fint > this.starCount ? this.starCount : fint;
        starCount = starCount < 0 ? 0 : starCount;

        //drawfullstar
        for (int i = 0; i < starCount; ++i) {
            ((ImageView) getChildAt(i)).setImageDrawable(starFillDrawable);
        }

        //drawhalfstar
        if (fPoint > 0) {
            ((ImageView) getChildAt(fint)).setImageDrawable(starHalfDrawable);

            //drawemptystar
            for (int i = this.starCount - 1; i >= starCount + 1; --i) {
                ((ImageView) getChildAt(i)).setImageDrawable(starEmptyDrawable);
            }

        } else {
            //drawemptystar
            for (int i = this.starCount - 1; i >= starCount; --i) {
                ((ImageView) getChildAt(i)).setImageDrawable(starEmptyDrawable);
            }

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //click (part of touch)
        if (!mTouchable && mClickable) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    return onStarTouch(event);
            }
        }

        //touch
        return mTouchable && onStarTouch(event);
    }

    private boolean onStarTouch(MotionEvent event) {

        float pointX = event.getRawX();
        float pointY = event.getRawY();

        Rect rect = new Rect();
        getGlobalVisibleRect(rect);

        //Within limits
        if (pointX > rect.left && pointX < rect.right && pointY > rect.top && pointY < rect.bottom){

            float x = event.getX();
            float width = getWidth();

            //range
            if (x < 0) {
                x = 0;
            }else if (x > width) {
                x = width;
            }

            //simple calculating method
            float originalCount = x * starCount / width;

            int actualCount = (int)originalCount;

            if (originalCount > actualCount) {
                if (halfstart){
                    if (originalCount - actualCount <0.5 ){
                        setStar(actualCount + 0.5f );
                        if (onRatingChangeListener!=null) {
                            onRatingChangeListener.onRatingChange(this,actualCount + 0.5f);
                        }
                    }else {
                        setStar(actualCount + 1f );
                        if (onRatingChangeListener!=null) {
                            onRatingChangeListener.onRatingChange(this,actualCount + 1);
                        }
                    }
                }else {
                    setStar(actualCount + 1);
                    if (onRatingChangeListener!=null) {
                        onRatingChangeListener.onRatingChange(this,actualCount + 1);
                    }
                }
            }else {
                setStar(actualCount);
                if (onRatingChangeListener!=null) {
                    onRatingChangeListener.onRatingChange(this,actualCount);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * change start listener
     */
    public interface OnRatingChangeListener {

        void onRatingChange(MyRatingBar bar, float RatingCount);
    }

}
