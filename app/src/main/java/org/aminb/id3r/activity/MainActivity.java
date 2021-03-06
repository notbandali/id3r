/*
 * MainActivity.java
 * Copyright (C) 2014 Amin Bandali <me@aminb.org>
 *
 * id3r is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * id3r is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.aminb.id3r.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.melnykov.fab.FloatingActionButton;

import org.aminb.id3r.R;
import org.aminb.id3r.fragment.AboutDialog;
import org.aminb.id3r.fragment.MainFragment;


public class MainActivity extends ActionBarActivity implements AboutDialog.DismissListener {

    private FloatingActionButton fab;
    Toolbar toolbar;
    private static boolean aboutDialogShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }

        fab = (FloatingActionButton) findViewById(R.id.btn_save);
        fab.hide(false);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            if (aboutDialogShown) return false;
            aboutDialogShown = true; // double clicking without this causes the dialog to be shown twice
            new AboutDialog().show(getSupportFragmentManager(), "ABOUT");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showFAB() {
        fab.show();
    }

    public void hideFAB() {
        fab.hide();
    }

    public void setToolbarProgress (boolean enabled) {
        if (enabled)
            toolbar.findViewById(R.id.progress).setVisibility(View.VISIBLE);
        else
            toolbar.findViewById(R.id.progress).setVisibility(View.GONE);
    }

    public void setFABListener (View.OnClickListener listener) {
        fab.setOnClickListener(listener);
    }

    @Override
    public void onDismiss() {
        aboutDialogShown = false;
    }
}
