package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.icu.text.Transliterator;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity {

    //creating a MediaPlayer variable
    private MediaPlayer mMediaPlayer;

    //Handles audio focus when playing a sound file
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        //Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);


        //To add the numbers in the Array of Strings
//        String[] numbers = new String[10];
//        numbers[0] = "One";
//        numbers[1] = "Two";
//        numbers[2] = "Three";
//        numbers[3] = "Four";
//        numbers[4] = "Five";
//        numbers[5] = "Six";
//        numbers[6] = "Seven";
//        numbers[7] = "Eight";
//        numbers[8] = "Nine";
//        numbers[9] = "Ten";

        //Adding the elements in a ArrayList
        final ArrayList<Word> numbers = new ArrayList<Word>();

        Word w = new Word("one", "lutti", R.drawable.number_one, R.raw.number_one);
        numbers.add(w);
        //Or smaller method show below
        //Creating a object and directly adding it to list
        numbers.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        numbers.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        numbers.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        numbers.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        numbers.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        numbers.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numbers.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        numbers.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        numbers.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

/*
         //Using ArrayAdapter and ListView(It has limitation that only one TextView is only handled by it so we need to create a custom ArrayAdapter to get our work done
        ArrayAdapter<String> numberAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_view_1,numbers);

        //Creating List View and adding the array adapter to it
        ListView numberListView = (ListView)findViewById(R.id.list);
        numberListView.setAdapter(numberAdapter);
*/

        //Using custom created WordAdapter that can take two textView as input
        WordAdapter numberAdapter = new WordAdapter(this, numbers, R.color.category_numbers);
        ListView numberListView = (ListView) findViewById(R.id.list);
        numberListView.setAdapter(numberAdapter);


        //calling a setOnItemClickListener method on listView and passing a object of OnItemClickListener
        numberListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

//                Toast.makeText(view.getContext(),"Plays the audio file",Toast.LENGTH_SHORT).show();
                // Get the {@link Word} object at the given position the user clicked on
                Word word = numbers.get(position);

               /* //this is generally used for debugging purpose by printing the values present in Words
                Log.v("NumberActivity", "Current word : " + word.toString());*/

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(NumberActivity.this, word.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        //releasing the Media whenever activity is stooped either app is closed or activity
        //we don't want our audio file to play
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

        }
    }

     /* //Creating Grid View and adding the array adapter to it
        GridView numberListView = (GridView) findViewById(R.id.list);
        numberListView.setAdapter(numberAdapter);*/

//        //Verifying the list of elements using array
//     /*  int count = 0;
//     while(count<10){
//      Log.v("NumberActivity","Word at index" + count " is " + numbers.get(count));
//      count++;
//      }
//
//       */

    //Finding the layout by id so that we can set other view in it
    //LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);

//        /*
//        using while loop
//        int count = 0;
//        while(count<numbers.size())
//        {
//            TextView numberView = new TextView(this);
//            numberView.setText(numbers.get(count));
//            rootView.addView(numberView);
//            count++;
//         }
//         */

       /* for(int i=0 ; i<numbers.size() ; i++)
        {
            TextView numberView = new TextView(this);
            numberView.setText(numbers.get(i));
            //Adding the view to the layout
            rootView.addView(numberView);
        }*/
}