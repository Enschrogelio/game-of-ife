package com.ashley.com.gameoflife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by x on 10/01/2015.
 */
public class menu_layout_activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
    }


    public void green (View v) {

        Intent act = new Intent(this, WorldActivity.class);

        startActivity(act);
    }

    public void pink (View v) {

        Intent act = new Intent(this, WorldActivity2.class);

        startActivity(act);
    }

    public void gray (View v) {

        Intent act = new Intent(this, WorldActivity3.class);

        startActivity(act);
    }

}