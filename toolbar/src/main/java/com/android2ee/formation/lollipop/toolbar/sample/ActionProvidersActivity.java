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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.ActionProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.android2ee.formation.lollipop.toolbar.R;

public class ActionProvidersActivity extends AppCompatActivity {
    /**
     * The action Bar
     */
    private ActionBar actionBar;
    /**
     * The ToolBar
     */
    private Toolbar toolbar;
    /***
     * Boolean to know which version is running
     */
    private boolean postICS,postLollipop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_providers);
        //find the toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        postICS =getResources().getBoolean(R.bool.postICS);
        postLollipop =getResources().getBoolean(R.bool.postLollipop);
        if(postLollipop){
            toolbar.setElevation(15);
        }
        //define the toolbar as the ActionBar
        setSupportActionBar(toolbar);
        actionBar=getSupportActionBar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_providers, menu);
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


    /******************************************************************************************/
    /** ActionProvider Code **************************************************************************/
    /******************************************************************************************/
    public static class AirPlaneActionProvider extends ActionProvider {

        /** An intent for launching the system settings. */
        private static final Intent sAirPlaneIntent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
        /** An intent for launching the system settings. */
        private static final Intent sSettingIntent = new Intent(Settings.ACTION_SETTINGS);
        /** An intent for launching the system settings. */
        private static final Intent sDictionnaryIntent = new Intent(Settings.ACTION_USER_DICTIONARY_SETTINGS);

        /** Context for accessing resources. */
        private final Context mContext;
        /**
         * The layout that carries the others buttons
         */
        LinearLayout lilOthersButtons;
        /**
         *
         */
        ImageButton buttonExtend;
        /**
         *
         */
        boolean isExtended=false;
        /**
         * Creates a new instance.
         *
         * @param context
         *            Context for accessing resources.
         */
        public AirPlaneActionProvider(Context context) {
            super(context);
            mContext = context;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public View onCreateActionView() {
            // Inflate the action view to be shown on the action bar.
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            View view = layoutInflater.inflate(R.layout.action_provider_view, null);
            buttonExtend = (ImageButton) view.findViewById(R.id.button_action_provider_extend);
            ImageButton buttonAction1 = (ImageButton) view.findViewById(R.id.button_action_provider1);
            ImageButton buttonAction2 = (ImageButton) view.findViewById(R.id.button_action_provider2);
            ImageButton buttonAction3 = (ImageButton) view.findViewById(R.id.button_action_provider3);
            lilOthersButtons=(LinearLayout)view.findViewById(R.id.lil_other_action);
            // Attach a click listener for launching the system settings.
            buttonExtend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setVisible();
                }
            });
            buttonAction1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    action1();
                }
            });
            buttonAction2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    action2();
                }
            });
            buttonAction3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    action3();
                }
            });
            return view;
        }

        /**
         * The action 1
         */
        private void action1() {
            mContext.startActivity(sAirPlaneIntent);
        }
        /**
         * The action 2
         */
        private void action2() {
            mContext.startActivity(sDictionnaryIntent);
        }
        /**
         * The action 3
         */
        private void action3() {
            mContext.startActivity(sSettingIntent);
        }

        /**
         * SetTheVisibility of the rest of the linearLayout
         */
        private void setVisible() {
            if(isExtended) {
                lilOthersButtons.setVisibility(View.GONE);
                buttonExtend.setBackgroundResource(R.drawable.ic_action_provider_extends);
            }else {
                lilOthersButtons.setVisibility(View.VISIBLE);
                buttonExtend.setBackgroundResource(R.drawable.ic_action_provider_collapse);
            }
            isExtended=!isExtended;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean onPerformDefaultAction() {
            // This is called if the host menu item placed in the overflow menu of the
            // action bar is clicked and the host activity did not handle the click.
            mContext.startActivity(sAirPlaneIntent);
            return true;
        }
    }
}
