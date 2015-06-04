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

package com.android2ee.formation.lollipop.toolbar.sample;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android2ee.formation.lollipop.toolbar.R;

/**
 *http://stackoverflow.com/questions/29790070/upgraded-to-appcompat-v22-1-0-and-now-getting-illegalargumentexception-appcompa
 * http://stackoverflow.com/questions/26491689/how-do-i-declare-an-extended-height-toolbar-action-bar-on-android-lollipop
 */
public class ActivityWithItems extends AppCompatActivity {
    /**
     * The action Bar
     */
    private ActionBar actionBar;
    private Toolbar toolbar;
    private boolean postICS,postLollipop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);
        //then define the ToolBar as the ActionBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        postICS =getResources().getBoolean(R.bool.postICS);
        postLollipop =getResources().getBoolean(R.bool.postLollipop);
        if(postLollipop){
            toolbar.setElevation(15);
        }
        setSupportActionBar(toolbar);
        actionBar=getSupportActionBar();

    }
    /******************************************************************************************/
    /**Playing with animations **************************************************************************/
    /******************************************************************************************/
    Runnable toolBarAnimator=new Runnable() {
        @Override
        public void run() {
            if(postICS){
//                toolbar.setTranslationX(iteration);
//                toolbar.setElevation(iteration);
                toolbar.setMinimumHeight(iteration * 5);
                toolbar.setOverScrollMode(Toolbar.OVER_SCROLL_ALWAYS);
                toolbar.getLayoutParams().height++;
            }
            uiThreadHanlder.postDelayed(this,16);
            iteration++;
            if(iteration>150)iteration=0;
        }
    };
    Handler uiThreadHanlder=new Handler();
    int iteration=0;

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
        //launch the animation
        uiThreadHanlder.postDelayed(toolBarAnimator, 1000);
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        //stop the animation
        uiThreadHanlder.removeCallbacks(toolBarAnimator);
    }
/******************************************************************************************/
    /** ActionBar Management **************************************************************************/
    /******************************************************************************************/
    /**
     * Changing the title
     */
    boolean toogleChangeTitle = true;
    String initialTitle = null;

    public void changeTitle(View view) {
        if (null == initialTitle) {
            initialTitle = (String) actionBar.getTitle();
        }
        // ensure changing the title only if it's displayed
        // either you'll get a bug
        if (!textVisible) {
            actionBar.setDisplayShowTitleEnabled(true);
        }
        if (toogleChangeTitle) {
            actionBar.setTitle("a new Title");
            actionBar.setSubtitle("A subtitle");
        } else {
            actionBar.setTitle(initialTitle);
            actionBar.setSubtitle(null);
        }
        toogleChangeTitle = !toogleChangeTitle;
        if (!textVisible) {
            actionBar.setDisplayShowTitleEnabled(false);

        }
    }

    /**
     * Hiding the title
     */
    boolean textVisible = true;

    public void hideText(View view) {
        if (textVisible) {
            actionBar.setDisplayShowTitleEnabled(false);
        } else {

            actionBar.setDisplayShowTitleEnabled(true);
        }
        textVisible = !textVisible;
    }

    /**
     * The ClickListener of the button changeTitle
     *
     * @param view
     */
    boolean toogleIcon = false;

    @SuppressLint("NewApi")
    public void changeIcon(View view) {
        // Only available for IceScreamSandwich
        if (getResources().getBoolean(R.bool.postICS)) {
            if (toogleIcon) {
                Drawable drawable = getResources().getDrawable(R.mipmap.ic_toolbar_background);
                actionBar.setDisplayUseLogoEnabled(true);
                actionBar.setLogo(drawable);
            } else {
                Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setIcon(drawable);
            }
        }
        toogleIcon = !toogleIcon;
    }

    /**
     * The ClickListener of the button changeTitle
     *
     * @param view
     */
    boolean iconVisible = true;

    public void hideIcon(View view) {
        if (iconVisible) {
            actionBar.setDisplayShowHomeEnabled(false);
        } else {
            actionBar.setDisplayShowHomeEnabled(true);
        }
        iconVisible = !iconVisible;
    }

    /**
     * Changing the background
     */
    int colorRed = 0xFFFF0000;
    int colorBlue = 0xFF00FF00;
    int colorGreen = 0xFF0000FF;
    int backgroundIndex = 0;

    public void changeBackground(View view) {
        ColorDrawable colorDrawable = new ColorDrawable(0xFF00FF00);
        if(getResources().getBoolean(R.bool.postICS)) {
            Drawable drawable = null;
            switch (backgroundIndex % 3) {
                case 0:
                    colorDrawable.setColor(colorRed);
                    break;
                case 1:
                    colorDrawable.setColor(colorGreen);
                    break;
                case 2:
                    colorDrawable.setColor(colorBlue);
                    break;
            }
        }
        actionBar.setBackgroundDrawable(colorDrawable);
        backgroundIndex++;
        // workaround to take into account the background change
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        // ok, any way there is a bug when displaying a real picture
        // sometimes it works other times it doesn't (case 4)
        // to display a real picture you should not update dynamically, you should define it using
        // the style inheritance
    }

    /******************************************************************************************/
    /** SetHomeButtonEnable **************************************************************************/
    /******************************************************************************************/
    boolean homeEnable = false;

    @SuppressLint("NewApi")
    public void setHomeEnable(View view) {
        // Only available for IceScreamSandwich
        if (getResources().getBoolean(R.bool.postICS)) {
            if (homeEnable) {
                actionBar.setHomeButtonEnabled(false);
            } else {
                actionBar.setHomeButtonEnabled(true);
            }
            homeEnable = !homeEnable;
        }
    }

    boolean upEnable = false;

    @SuppressLint("NewApi")
    public void setUpEnable(View view) {
        // Only available for IceScreamSandwich
        if (getResources().getBoolean(R.bool.postICS)) {
            if (!upEnable) {
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setDisplayHomeAsUpEnabled(true);
            } else {
                actionBar.setDisplayUseLogoEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(false);
            }
            upEnable = !upEnable;
        }
    }

    /******************************************************************************************/
    /** Change Activity to see if the actionBar's state is depending on the activity
     * or on the application And to see if the change persist
     * And the Conclusion is they don't persist.
     * So if you want to change ActionBar at runTime and to persist that state you need to
     * Save those preferences into a SharedPreference
     * Have a motherActivity for all your activities that updates the actionBar in the OnCreate
     *  method using the values stored in the SharedPreference
     /******************************************************************************************/


    /**
     * Change activity to see what happens to ActionBar
     *
     * @param view
     */
    public void changeActivty(View view) {
        finish();
    }

    /******************************************************************************************/
    /** TOOLBAR new Method **************************************************************************/
    /******************************************************************************************/
    public void hideOverFlowMenu(View view){
        Log.e("ActivityWithItems", "HideOverFlowMenu");
        //the toolbar is updated, the actionbar also
        //but not all the methods works
        toolbar.setSubtitle("Tool bar changed => actionbarchanged");
    }


    /******************************************************************************************/
    /** Menu **************************************************************************/
    /******************************************************************************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_with_items, menu);
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                Toast.makeText(this, "The home button has been clicked", Toast.LENGTH_SHORT).show();
                // should look like something like that:
//			Intent intent = new Intent(this, ActionBarActivity.class);
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			startActivity(intent);
//			return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
