package com.example.miwok;

import android.view.View;
import android.widget.Toast;

public class NumberClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view)
    {
        Toast.makeText(view.getContext(),"Open the List of Numbers",Toast.LENGTH_SHORT).show();
    }
}
