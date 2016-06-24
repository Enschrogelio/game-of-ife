package com.ashley.com.gameoflife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by x on 11/01/2015.
 */
public class OptionActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_layout);
    }
    public void Instructions (View v) {

        Intent act = new Intent(this, Instruction.class);

        startActivity(act);
    }

    public void Start (View v) {

        Intent act = new Intent(this, menu_layout_activity.class);

        startActivity(act);
    }
    public void About (View v) {

        Intent act = new Intent(this, About.class);

        startActivity(act);
    }
}
