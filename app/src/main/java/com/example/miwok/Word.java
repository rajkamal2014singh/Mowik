package com.example.miwok;

public class Word{
    //miwok Translation of the word
    private String mMiwokWord;
    //english word for the miwok word
    private String mEnglishWord;
    //creating a integer for the imageId
    private int imageResourceId = NO_IMAGE_PROVIDED;
    //creating a integer variable for audio file
    private int mAudioResourceId;

    //creating a constant value to check if image is provided or not
    private static final int NO_IMAGE_PROVIDED = -1;

    //creating a audio resourceId

    //constructor to initialize the strings and audio
    public Word(String englishWord, String  miwokWord, int AudioResourceId)
    {
        mMiwokWord = miwokWord;
        mEnglishWord = englishWord;
        mAudioResourceId = AudioResourceId;
    }

    //multiple constructor or constructor overriding to add image too to it
    public Word(String englishWord, String  miwokWord, int imageId, int AudioResourceId)
    {
        mMiwokWord = miwokWord;
        mEnglishWord = englishWord;
        imageResourceId = imageId;
        mAudioResourceId = AudioResourceId;
    }

    //getting the miwok translation using these method
    public String getMiwokWord()
    {
        return mMiwokWord;
    }

    //getting the english word using these method
    public String getEnglishWord()
    {
        return mEnglishWord;
    }

    //method to get the image
    public int getImageResourceId()
    {
        return imageResourceId;
    }

    //methods returns true if any image is provided else it returns flase
    public boolean hasImage()
    {
        return imageResourceId != NO_IMAGE_PROVIDED;
    }

    //method that returns the audio resouce id value
    public int getAudioResourceId()
    {
        return mAudioResourceId;
    }

    //Mainly used for debugging purposes to show on the log
    @Override
    public String toString() {
        return "Word{" +
                "mMiwokWord='" + mMiwokWord + '\'' +
                ", mEnglishWord='" + mEnglishWord + '\'' +
                ", imageResourceId=" + imageResourceId +
                ", mAudioResourceId=" + mAudioResourceId +
                '}';
    }
}
