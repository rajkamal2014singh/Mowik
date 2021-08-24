package com.example.miwok;

import android.view.View;
import android.widget.Toast;

public class PhrasesClickListener implements View.OnClickListener{
    @Override
    public void onClick(View view)
    {
        Toast.makeText(view.getContext(),"Opens the phrases activity",Toast.LENGTH_SHORT).show();
    }
}
