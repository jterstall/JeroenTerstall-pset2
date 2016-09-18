// Jeroen Terstall
// 10766030
// This file handles the filling in of words of the mad lib game

package terstall.jeroenterstall_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class fillWords extends AppCompatActivity
{
    // Name to store story object in intent
    static final String OBJECT_NAME = "STORY_OBJECT";
    // Name to store story number in intent
    static final String STORY_NUMBER = "STORY_NUMBER";
    // Part of the description for words remaining
    static final String words_remaining_right = " word(s) left";
    // Part of the word description
    static final String word_description_left = "Please type a/an ";
    // Init needed variables
    String words_remaining;
    String word_description;
    String nextPlaceholder;
    int placeholderCount;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_words);
        // Get passed intent and retrieve the story and story number and go to the main activity
        Intent intent = getIntent();
        Story story = (Story) intent.getSerializableExtra(OBJECT_NAME);
        int storyNumber = intent.getIntExtra(STORY_NUMBER, 0);
        mainActivity(story, storyNumber);
    }

    // Main activity
    // Checks if the story is already filled in, if not handles the changing of the desscriptions for the next screen
    private void mainActivity(Story story, int storyNumber)
    {
        if (story.isFilledIn() != true)
        {
            placeholderCount = story.getPlaceholderRemainingCount();
            nextPlaceholder = story.getNextPlaceholder();
            words_remaining = Integer.toString(placeholderCount) + words_remaining_right;
            word_description = word_description_left + nextPlaceholder;
            TextView wordtype = (TextView) findViewById(R.id.word_type);
            wordtype.setText(word_description);
            TextView wordsleft = (TextView) findViewById(R.id.words_left);
            wordsleft.setText(words_remaining);
            EditText editwordtype = (EditText) findViewById(R.id.edit_word_type);
            editwordtype.setHint(nextPlaceholder);
        }
        // If already filled in go to final screen while passing story and story number
        else
        {
            Intent nextIntent = new Intent(this, showText.class);
            nextIntent.putExtra(OBJECT_NAME, story);
            nextIntent.putExtra(STORY_NUMBER, storyNumber);
            startActivity(nextIntent);
            finish();
        }
    }

    // Function gets called if a word is filled in and button is pressed
    public void nextPlaceholder(View view)
    {
        // Retrieve story variables
        Intent intent = getIntent();
        Story story = (Story) intent.getSerializableExtra(OBJECT_NAME);
        int storyNumber = intent.getIntExtra(STORY_NUMBER, 0);
        // Get filled in word
        EditText editwordtype = (EditText) findViewById(R.id.edit_word_type);
        String input = editwordtype.getText().toString();
        // Reset edit text
        editwordtype.setText("");
        // Check if a word was actually filled in
        // If it was, fill in placeholder and start main activity again
        if(input.trim().length() != 0)
        {
            story.fillInPlaceholder("<b>" + input + "</b>");
            mainActivity(story, storyNumber);
        }
        else
        {
            System.out.println("Use valid input");
        }

    }

    // When the back button was pressed, I wanted it to go back to the first screen with the same
    // story so you can start over. This handles that
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = getIntent();
            int storyNumber = intent.getIntExtra(STORY_NUMBER, 0);
            Intent newIntent = new Intent(this, FirstScreen.class);
            newIntent.putExtra(STORY_NUMBER, storyNumber);
            startActivity(newIntent);
            finish();
            return true;
        }
        return false;
    }
}
