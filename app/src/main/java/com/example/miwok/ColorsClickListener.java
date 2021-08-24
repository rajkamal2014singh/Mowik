package com.example.miwok;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class ColorsClickListener implements View.OnClickListener {

    @Override
    public void onClick(View view)
    {
        Toast.makeText(view.getContext(),"Opens the color activity",Toast.LENGTH_SHORT).show();
    }
}

//These extra classes are of no use as we can use the concept of inline and use these methods in the mainActivity