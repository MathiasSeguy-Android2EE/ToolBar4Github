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

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android2ee.formation.lollipop.toolbar.R;

public class ActionViewActivity extends AppCompatActivity {
    /**
     * The action Bar
     */
    private ActionBar actionBar;
    /**
     * The ToolBar
     */
    private Toolbar toolbar;
    private Animation tweenAnimation;
    private ObjectAnimator objectAnimator;
    /***
     * Boolean to know which version is running
     */
    private boolean postICS,postLollipop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_view);
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

    MenuItem menuItemActionView;
    LinearLayout lilActionView;
    EditText edtActionView;
    ImageButton btnActionView;
    int edtActionViewHeight=0, edtActionViewWidth=0;
    boolean collapsing;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_action_view, menu);
        menuItemActionView = menu.findItem(R.id.menu_item_actionview);
        lilActionView = (LinearLayout) MenuItemCompat.getActionView(menuItemActionView);
        edtActionView = (EditText) lilActionView.findViewById(R.id.edtActionView);
        btnActionView = (ImageButton) lilActionView.findViewById(R.id.btnActionView);
        btnActionView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actionOfTheActionView();
            }
        });
        // When using the support library, the setOnActionExpandListener() method is
        // static and accepts the MenuItem object as an argument
        MenuItemCompat.setOnActionExpandListener(menuItemActionView, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed, it's too late for animation
                Log.e("ActionViewActivity", "menu_item_actionview collapsing");
                return true; // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                //the first time, elements are not expanded, It's too soon to have their size
                   if(getResources().getBoolean(R.bool.postICS)) {
                       btnActionView.animate().rotationBy(360f).alpha(1f).setDuration(300);
                       edtActionView.animate().rotationBy(360f).alpha(1f).y(0).setDuration(300);
                   }
                // Do something when expanded
                Log.e("ActionViewActivity", "menu_item_actionview extending");
                return true; // Return true to expand action view
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    /**
     * Handling Action from the btnActionView contained by the ActionView
     */
    private void actionOfTheActionView() {
        Log.e("ActionViewActivity", "ActionView edt " + edtActionView.getText().toString());
        Toast.makeText(this, "ActionView edt = " + edtActionView.getText().toString(), Toast.LENGTH_SHORT).show();
        collapsing=true;
        //this is called when the user press the search icon, so the
        if(getResources().getBoolean(R.bool.postICS)) {
            edtActionView.animate().alpha(0).rotationBy(-360f).setDuration(300);
            btnActionView.animate().alpha(0).rotationBy(-360f).setDuration(300).setListener(new Animator.AnimatorListener() {
                //the listener is set for all the animations (further animations)
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (collapsing) {
                        MenuItemCompat.collapseActionView(menuItemActionView);
                        collapsing = false;
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    if (collapsing) {
                        MenuItemCompat.collapseActionView(menuItemActionView);
                        collapsing = false;
                    }
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
        }
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
            case R.id.menu_item_actionview:
                // Because the system expands the action view when the user selects the action, you do
                // not need to respond to the item in the onOptionsItemSelected() callback. The system
                // still calls onOptionsItemSelected(), but if you return true (indicating you've
                // handled the event instead), then the action view will not expand.
                Log.e("ActionViewActivity", "menu_item_actionview get");
                return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
