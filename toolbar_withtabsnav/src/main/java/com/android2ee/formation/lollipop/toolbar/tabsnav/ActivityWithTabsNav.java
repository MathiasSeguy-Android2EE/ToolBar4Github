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
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * 04/06/2015
 * This class aims to shows the usage of the TabLayout
 */
public class ActivityWithTabsNav extends AppCompatActivity {
    /**
     * ********************************************************
     * Attributes
     * ********************************************************
     */
    /**
     * The button to change the gravity of the TabLayout
     * CENTER or FILL
     */
    Button btnChangeGravity;
    /**
     * if the current gravity of the TabLayout is set to FILL
     */
    boolean gravitySetToFill = true;
    /**
     * The button to change the mode of the TabLayout
     */
    Button btnChangeMode;
    /**
     * if the current mode is set to Fixed or to Scroll
     */
    boolean modeSetToFixed = true;
    /**
     * The button to add a Tab
     */
    Button btnAddTab;
    /**
     * The TabLayout itself :)
     */
    TabLayout tabLayout;
    /**
     * The the TextView that disaplys ToolBar state
     * And the String to displays them
     */
    TextView txvState;
    private StringBuilder strBuildTemp=new StringBuilder();
    private String strGravityFill,strGravityCenter,strModeFixed,strModeScrollable;
    /**
     * The tab number only used to add a title to new tab
     */
    int tabNumber = 3;
    /**
     * ********************************************************
     * Managing Life Cycle
     * ********************************************************
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set your view
        setContentView(R.layout.activity_activity_with_tabs_nav);
        //find the Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //use it as your action bar
        setSupportActionBar(toolbar);
        //Add subtitle
        getSupportActionBar().setSubtitle("Using ToolBar");
        //Find the Tab Layout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        //Define its gravity and its mode
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //Define the color to use (depending on the state a different color should be disaplyed)
        //Works only if done before adding tabs
        tabLayout.setTabTextColors(getResources().getColorStateList(R.color.tab_selector_color));
        //populate your TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1").setIcon(R.drawable.ic_tab));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        //add a listener
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {/*do your navigation*/}

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {/*do nothing*/}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {/*do nothing*/}
        });
        //find the txvState
        txvState= (TextView) findViewById(R.id.txvState);
        //then find your button
        btnAddTab = (Button) findViewById(R.id.btnAddTab);
        btnChangeGravity = (Button) findViewById(R.id.btnChangeGravity);
        btnChangeMode = (Button) findViewById(R.id.btnChangeMode);
        //define their listener
        btnChangeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMode();
            }
        });
        btnChangeGravity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeGravity();
            }
        });
        btnAddTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTab();
            }
        });
        //initiliaze string
        Resources res=getResources();
        strGravityCenter=res.getString(R.string.txvStateGravityCenter);
        strGravityFill=res.getString(R.string.txvStateGravityFill);
        strModeFixed=res.getString(R.string.txvStateModeFixed);
        strModeScrollable=res.getString(R.string.txvStateModeScrollable);
        updateState();
    }

    /***********************************************************
     *  Managing TabLayout methods
     **********************************************************/
    /**
     * Add a Tab
     */
    private void addTab() {
        tabNumber++;
        tabLayout.addTab(tabLayout.newTab().setText("Tab " + tabNumber));
    }

    /**
     * Change the tabLayout mode
     */
    private void changeMode() {
        modeSetToFixed = !modeSetToFixed;
        if (modeSetToFixed) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        updateState();
    }

    /**
     * Change the TabLayout Gravity
     */
    private void changeGravity() {
        gravitySetToFill = !gravitySetToFill;
        if (gravitySetToFill) {
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        } else {
            tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        }
        updateState();
    }


    /**
     * update the text view state to dispaly the state of the tabLayout
     */
    private void updateState(){
        strBuildTemp.delete(0,strBuildTemp.length());
        if (gravitySetToFill) {
            strBuildTemp.append(strGravityFill);
        } else {
            strBuildTemp.append(strGravityCenter);
        }
        if (modeSetToFixed) {
            strBuildTemp.append(strModeFixed);
        } else {
            strBuildTemp.append(strModeScrollable);
        }
        txvState.setText(strBuildTemp.toString());
    }

    /**
     * ********************************************************
     * Manage your menu
     * ********************************************************
     */
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
            Intent otherActivity=new Intent(this, ActivityTabsNavViewPager.class);
            startActivity(otherActivity);
            //kill this one
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
