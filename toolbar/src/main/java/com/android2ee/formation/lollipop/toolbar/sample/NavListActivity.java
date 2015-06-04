/**
 * <ul>
 * <li>======================================================</li>
 * <p/>
 * <li>Projet : Android2EE Project</li>
 * <li>Produit par MSE.</li>
 * </ul>
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
package com.android2ee.formation.lollipop.toolbar.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android2ee.formation.lollipop.toolbar.R;

import java.util.ArrayList;
import java.util.List;

public class NavListActivity extends AppCompatActivity {
    /**
     * The action Bar
     */
    private ActionBar actionBar;
    /**
     * The ToolBar
     */
    private Toolbar toolbar;
    private Spinner spinner;
    private List<String> mNavigationItems;
    private ArrayAdapter<CharSequence> mArrayAdapter;
    /***
     * Boolean to know which version is running
     */
    private boolean postICS,postLollipop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_list);//find the toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        postLollipop =getResources().getBoolean(R.bool.postLollipop);
        if(postLollipop){
            toolbar.setElevation(15);
        }
        //define the toolbar as the ActionBar
        setSupportActionBar(toolbar);
        actionBar=getSupportActionBar();
        //now manage the spinner3
        mNavigationItems=new ArrayList<String>();
        mNavigationItems.add("navigation 1");
        mNavigationItems.add("nav 2");
        mNavigationItems.add("navigation 3");
        spinner= (Spinner) findViewById(R.id.action_bar_spinner);
        mArrayAdapter = new ArrayAdapter(this, R.layout.actionbar_spinner_dropdown_item, mNavigationItems);
        mArrayAdapter.setDropDownViewResource(R.layout.actionbar_spinner_dropdown_item);
        spinner.setAdapter(mArrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_with_items, menu);
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
