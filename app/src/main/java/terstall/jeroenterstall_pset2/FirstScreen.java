package terstall.jeroenterstall_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class FirstScreen extends AppCompatActivity
{
    static final int MAX_INDEX = 4;
    static final String[] FILENAMES = {"madlib0_simple.txt", "madlib1_tarzan.txt", "madlib2_university.txt", "madlib3_clothes.txt", "madlib4_dance.txt"};
    static final String OBJECT_NAME = "STORY_OBJECT";
    static final String STORY_NUMBER = "STORY_NUMBER";
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
    }

    public void fillWords(View view)
    {
        try
        {
            Intent thisIntent = getIntent();
            if(thisIntent.getExtras() == null)
            {
                int randomNum = rand.nextInt(MAX_INDEX + 1);
                DataInputStream textFileStream = new DataInputStream(getAssets().open(FILENAMES[randomNum]));
                Story story = new Story(textFileStream);
                Intent intent = new Intent(this, fillWords.class);
                intent.putExtra(STORY_NUMBER, randomNum);
                intent.putExtra(OBJECT_NAME, story);
                startActivity(intent);
            }
            else
            {
                int storyNumber = thisIntent.getIntExtra(STORY_NUMBER, 0);
                DataInputStream textFileStream = new DataInputStream(getAssets().open(FILENAMES[storyNumber]));
                Story story = new Story(textFileStream);
                Intent intent = new Intent(this, fillWords.class);
                intent.putExtra(OBJECT_NAME, story);
                intent.putExtra(STORY_NUMBER, storyNumber);
                startActivity(intent);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
