package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        NumberClickListener numberClick = new NumberClickListener();
//        //Finding view that shows numberList
//        TextView numberList = (TextView)findViewById(R.id.numbers);
//        //Set the numberClick on that View
//        numberList.setOnClickListener(numberClick);
//
//        ColorsClickListener colorClick = new ColorsClickListener();
//        TextView colorList = (TextView)findViewById(R.id.colors);
//        colorList.setOnClickListener(colorClick);
//
//        FamilyClickListener familyClick = new FamilyClickListener();
//        TextView familyList = (TextView)findViewById(R.id.family);
//        familyList.setOnClickListener(familyClick);
//
//        PhrasesClickListener phrasesClick = new PhrasesClickListener();
//        TextView phrasesList =(TextView)findViewById(R.id.phrases);
//        phrasesList.setOnClickListener(phrasesClick);

        //Earlier we have done these task by crating a Listener class and then creating a object and calling the required method
        TextView colors = (TextView)findViewById(R.id.colors);
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Opens Colors Activity",Toast.LENGTH_SHORT).show();
                Intent colorIntent = new Intent(MainActivity.this,ColorsActivity.class);
                startActivity(colorIntent);
            }
        });

        TextView numbers = (TextView)findViewById(R.id.numbers);
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Opens Numbers Activity",Toast.LENGTH_SHORT).show();
                Intent numbersIntent = new Intent(MainActivity.this,NumberActivity.class);
                startActivity(numbersIntent);
            }
        });

        TextView family = (TextView)findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Opens the Family Activity",Toast.LENGTH_SHORT).show();
                Intent familyIntent = new Intent(MainActivity.this,FamilyMembersActivity.class);
                startActivity(familyIntent);
            }
        });

        TextView phrases = (TextView)findViewById(R.id.phrases);
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Opens the Phrases Activity",Toast.LENGTH_SHORT).show();
                Intent phrasesIntent = new Intent(MainActivity.this,PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });
    }

//    /*
//        Method is used to intent the app to NumberList
//     */
//    public void openNumberList(View View)
//    {
//        Intent numbers = new Intent(this,NumberActivity.class);
//        numbers.putExtra("layout",R.layout.activity_number);
//        startActivity(numbers);
//    }
//
//    /*
//       Method is used to intent the app to ColorsList
//    */
//    public void openColorsList(View View)
//    {
//        Intent colors = new Intent(this,ColorsActivity.class);
//        colors.putExtra("layout",R.layout.activity_colors);
//        startActivity(colors);
//    }
//
//    /*
//       Method is used to intent the app to FamilyMembersList
//    */
//    public void openFamilyMembers(View View)
//    {
//        Intent family = new Intent(this, FamilyMembersActivity.class);
//        family.putExtra("layout",R.layout.activity_family_members);
//        startActivity(family);
//    }
//
//    /*
//       Method is used to intent the app to NumberList
//    */
//    public void openPhrasesList(View View)
//    {
//        Intent phrases = new Intent(this,PhrasesActivity.class);
//        phrases.putExtra("layout",R.layout.activity_phrases);
//        startActivity(phrases);
//
//    }
}