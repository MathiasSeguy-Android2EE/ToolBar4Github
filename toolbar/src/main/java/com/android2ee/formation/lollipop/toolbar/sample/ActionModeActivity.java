
/**<ul>
 * <li>======================================================</li>
 *
 * <li>Projet : Android2EE Project</li>
 * <li>Produit par MSE.</li>
 * </ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br>
 * Produced by <strong>Dr. Mathias SEGUY</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 *  Belongs to <strong>Mathias Seguy</strong></br>
 ****************************************************************************************************************</br>
 * This code is free for any usage but can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 *
 * *****************************************************************************************************************</br>
 *  Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 *  Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br>
 *  Sa propriété intellectuelle appartient à <strong>Mathias Seguy</strong>.</br>
 *  <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * *****************************************************************************************************************</br>
 */
package com.android2ee.formation.lollipop.toolbar.sample;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.android2ee.formation.lollipop.toolbar.R;


/**
 * This class aims to show you how ActionMode can be set
 * http://android-developers.blogspot.fr/2014/10/appcompat-v21-material-design-for-pre.html
 * http://treyrobinson.net/blog/android-l-tutorials-part-2-material-theme-colors/
 * https://developer.android.com/training/material/theme.html
 * http://stackoverflow.com/questions/26443403/toolbar-and-contextual-actionbar-with-appcompat-v7
 * http://stackoverflow.com/users/474997/chris-banes
 */
public class ActionModeActivity extends AppCompatActivity {
    /**
     * The actionMode
     */
    ActionMode mMode;
	/**
	 * The action Bar
	 */
	private ActionBar actionBar;
    /**
     * The ToolBar
     */
    private Toolbar toolbar;
    /***
     * The ActionMode callBack
     */
    Callback actionModeCallBack;
    /***
     * Boolean to know which version is running
     */
    private boolean postICS,postLollipop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_mode);
        //find the toolbar
		toolbar = (Toolbar) findViewById(R.id.toolbar);
        //customize the toolbar
        toolbar.setNavigationIcon(R.drawable.ic_action_custom_up);
		postICS =getResources().getBoolean(R.bool.postICS);
		postLollipop =getResources().getBoolean(R.bool.postLollipop);
		if(postLollipop){
			toolbar.setElevation(15);
		}
        //define the toolbar as the ActionBar
		setSupportActionBar(toolbar);
		actionBar=getSupportActionBar();
        //You could also hide the action Bar
        //getSupportActionBar().hide();

        // Show the Up button in the action bar.
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setListeners();

        //initialize the actionMode callback
        initializeActionModeCallBack();
    }
    /**
     * Launch the Action Mode
     */
    private void enableActionMode() {
		mMode = startSupportActionMode(actionModeCallBack);
	}

    /**
     * Initialize the callback for the actionMode
     */
    private void initializeActionModeCallBack() {
        actionModeCallBack=new  Callback() {
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.action_mode, menu);
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                Toast.makeText(ActionModeActivity.this, "Got click: " + item, Toast.LENGTH_SHORT).show();
                mode.finish();
                return true;
            }
        };
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_activity_with_items, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

    /**
     * Define the listeners on the button Start/Stop ActionMode
     */
    private void setListeners() {
        findViewById(R.id.start_actionmode).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                enableActionMode();
            }
        });
        findViewById(R.id.stop_actionmode).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMode != null) {
                    //To quit the ActionMode
                    mMode.finish();
                }
            }
        });
    }
}
