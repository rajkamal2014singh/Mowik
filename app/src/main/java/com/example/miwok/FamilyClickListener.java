package com.example.miwok;

import android.view.View;
import android.widget.Toast;

public class FamilyClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view)
    {
        Toast.makeText(view.getContext(),"Opens the family activity",Toast.LENGTH_SHORT).show();
    }
}
