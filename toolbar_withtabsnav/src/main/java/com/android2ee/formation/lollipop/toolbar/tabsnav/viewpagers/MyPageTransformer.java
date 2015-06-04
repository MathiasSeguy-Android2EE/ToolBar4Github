/**
 * <ul>
 * <li>ForecastRestYahooSax</li>
 * <li>com.android2ee.formation.restservice.sax.forecastyahoo</li>
 * <li>28 mai 2014</li>
 * <p/>
 * <li>======================================================</li>
 * <p/>
 * <li>Projet : Mathias Seguy Project</li>
 * <li>Produit par MSE.</li>
 * <p/>
 * /**
 * <ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br>
 * Produced by <strong>Dr. Mathias SEGUY</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 * Belongs to <strong>Mathias Seguy</strong></br>
 * ***************************************************************************************************************</br>
 * This code is free for any usage but can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * <p/>
 * *****************************************************************************************************************</br>
 * Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 * Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br>
 * Sa propriété intellectuelle appartient à <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * *****************************************************************************************************************</br>
 */

package com.android2ee.formation.lollipop.toolbar.tabsnav.viewpagers;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android2ee.formation.lollipop.toolbar.tabsnav.R;

/**
 * Created by Mathias Seguy - Android2EE on 19/05/2015.
 * This class aims to make nice animation when swipping from one view to another
 * Only works with post-honeycomb elements
 * Some resources to do thath:!
 * http://developer.android.com/training/animation/screen-slide.html
 * http://stackoverflow.com/questions/18710561/can-i-use-view-pager-with-views-not-with-fragments
 * https://github.com/codepath/android_guides/wiki/ViewPager-with-FragmentPagerAdapter
 * http://www.tutos-android.com/fragment-slider-page-lautre
 *
 * And for compatibility see:http://stackoverflow.com/questions/15767729/backwards-compatible-pagetransformer
 * To explains how to change properties on a view under gingerbread
 *
 */
public class MyPageTransformer implements ViewPager.PageTransformer {
    Button btnButton;
    ImageView imvPicture;
    TextView txvTitle,txvLeft,txvRight;
    int pageWidth;
    /****************************************************************************************
     *                                ViewPager.PageTransformer
     *****************************************************************************************/
    public MyPageTransformer(Context ctx){
        this.ctx=ctx;
    }
    Context ctx;
    float positionPaddingLeft=-102;
    public void transformPage(View view, float position) {

        //Only the main layout is passed here
         pageWidth = view.getWidth();
        btnButton = (Button) view.findViewById(R.id.btnButton);
        txvTitle = (TextView) view.findViewById(R.id.txvTitle);
        txvLeft = (TextView) view.findViewById(R.id.txvLeft);
        txvRight = (TextView) view.findViewById(R.id.txvRight);
        imvPicture= (ImageView) view.findViewById(R.id.imvPicture);
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 1) { // [-1,1]
            if(view.getAlpha()!=1){
                view.setAlpha(1);
            }
            if(position ==0){

            }else if (position < 0) {
                //right
//                view.setTranslationX(position * (pageWidth * 2)); //Half the normal speed
                left(view, position);
            } else {
                //left
               // view.setTranslationX(-position * pageWidth); //Half the normal speed
                right(view, position);
            }

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }

    /***
     * The position is between -1 and 0
     * @param view
     * @param position
     */
    private void left(View view, float position) {
        common(view, position);
        //I left
        //I want to make the ImageView getting big:
        //so -1 is size 0 and 0 is normal size:

        float ratio=1+position;
        imvPicture.setScaleY((ratio));
        txvTitle.setRotation(360 * position);
        btnButton.setTranslationY(position * 1000);
        btnButton.setText("left");
    }

    /***
     * The position is between 0 and 1
     * @param view
     * @param position
     */
    private void right(View view, float position) {
        common(view, position);
        float ratio=1-position;
        imvPicture.setScaleY(ratio);
        btnButton.setTranslationY(position * 1000);
        btnButton.setX(pageWidth*(1-position)/2);
        btnButton.setText("right");
//        btnButton.setTranslationX((pageWidth-btnButton.getWidth())*ratio);

    }
    private void common(View view, float position) {
        txvRight.setText("pos="+position);
        txvLeft.setText("pos="+position);
        float rotationAngle=360*position+360;
        txvTitle.setRotationX(rotationAngle);
    }


}
