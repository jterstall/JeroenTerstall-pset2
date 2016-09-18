// Jeroen Terstall
// 10766030
// This activity displays the first screen of the mad libs game

package terstall.jeroenterstall_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

public class FirstScreen extends AppCompatActivity
{
    // Max index in filenames list
    static final int MAX_INDEX = 4;
    // All filenames
    static final String[] FILENAMES = {"madlib0_simple.txt", "madlib1_tarzan.txt", "madlib2_university.txt", "madlib3_clothes.txt", "madlib4_dance.txt"};
    // Name to store the story in the intent
    static final String OBJECT_NAME = "STORY_OBJECT";
    // Name to store the story number in the intent
    static final String STORY_NUMBER = "STORY_NUMBER";
    // Randomizer object
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
    }

    // This function is called when the start button is clicked
    // It creates a story and its story number and then starts the next activity
    public void fillWords(View view)
    {
        try
        {
            Intent thisIntent = getIntent();
            // If there was no previous activity which lead to this activity, pick a random story
            //  and go to fill words screen
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
            // If there was a previous activity that lead to this screen, use the story number
            // stored in that activity to create a story which is passed to the next screen
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
