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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.android2ee.formation.lollipop.toolbar.tabsnav.ActivityTabsNavViewPager;
import com.android2ee.formation.lollipop.toolbar.tabsnav.viewpagers.fragments.MyFragment1;
import com.android2ee.formation.lollipop.toolbar.tabsnav.viewpagers.fragments.MyFragment2;
import com.android2ee.formation.lollipop.toolbar.tabsnav.viewpagers.fragments.MyFragment3;
import com.android2ee.formation.lollipop.toolbar.tabsnav.viewpagers.fragments.MyFragment4;
import com.android2ee.formation.lollipop.toolbar.tabsnav.viewpagers.fragments.MyFragment5;

import java.util.ArrayList;

/**
 * Created by Mathias Seguy - Android2EE on 19/05/2015.
 * This class is an Adapter, it's goal is to give at the right time the right view
 * So nothing complex here
 * This class only deal with Fragments !!!
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    /**
     * The list of ordered fragments
     */
    private final ArrayList<Fragment> fragments;

    //On fournit à l'adapter la liste des fragments à afficher

    /***
     * The constructor
     * @param ctx The Context
     */
    public MyPagerAdapter(ActivityTabsNavViewPager ctx) {
        super(ctx.getSupportFragmentManager());
        fragments = new ArrayList<Fragment>();
        //A stuff I never did before, instanciate my fragment
        Fragment frag =
                new MyFragment1();
        fragments.add(frag);
        frag = new MyFragment2();
        fragments.add(frag);
        frag = new MyFragment3();
        fragments.add(frag);
        frag = new MyFragment4();
        fragments.add(frag);
        frag = new MyFragment5();
        fragments.add(frag);
    }

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return "This is the Page "+position;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return 5;
    }

}

