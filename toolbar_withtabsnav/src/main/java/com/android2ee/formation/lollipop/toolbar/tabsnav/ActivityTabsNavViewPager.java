/**
 * <ul>
 * <li>======================================================</li>
 * <p/>
 * <li>Projet : Android2EE Project</li>
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
package com.android2ee.formation.lollipop.toolbar.tabsnav;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android2ee.formation.lollipop.toolbar.tabsnav.viewpagers.MyPagerAdapter;
import com.android2ee.formation.lollipop.toolbar.tabsnav.viewpagers.MyPageTransformer;


public class ActivityTabsNavViewPager extends AppCompatActivity {
    /**
     * The TabLayout itself :)
     */
    TabLayout tabLayout;
    /**
     * The page Adapter : Manage the list of views (in fact here, it's fragments)
     * And send them to the ViewPager
     */
    private MyPagerAdapter pagerAdapter;
    /**
     * The ViewPager is a ViewGroup that manage the swipe from left to right to left
     * Like a listView with a gesture listener...
     */
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_nav_view_pager);
        //find the Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //use it as your action bar
        setSupportActionBar(toolbar);
        //Add subtitle
        getSupportActionBar().setSubtitle("Using ToolBar");
        //Find the Tab Layout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        //Define its gravity and its mode
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //instanciate the PageAdapter
        pagerAdapter=new MyPagerAdapter(this);
        //Define the color to use (depending on the state a different color should be disaplyed)
        //Works only if done before adding tabs
        tabLayout.setTabTextColors(getResources().getColorStateList(R.color.tab_selector_color));
        //Find the viewPager
        viewPager = (ViewPager) super.findViewById(R.id.viewpager);
        // Affectation de l'adapter au ViewPager
        viewPager.setAdapter(pagerAdapter);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(12);
        //Add animation when the page are swiped
        //this instanciation only works with honeyComb and more
        //if you want it all version use AnimatorProxy of the nineoldAndroid lib
        //@see:http://stackoverflow.com/questions/15767729/backwards-compatible-pagetransformer
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            viewPager.setPageTransformer(true, new MyPageTransformer(this));
        }
        //AND CLUE TABLAYOUT AND VIEWPAGER
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_with_tabs_nav, menu);
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

        if(id==R.id.action_switch){
            //launch the new activity
            Intent otherActivity=new Intent(this, ActivityWithTabsNav.class);
            startActivity(otherActivity);
            //kill this one
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
