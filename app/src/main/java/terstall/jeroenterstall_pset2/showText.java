// Jeroen Terstall
// 10766030
// This activity displays the final text and lets the user play the game again

package terstall.jeroenterstall_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class showText extends AppCompatActivity
{
    // Name to store story object in intent
    static final String OBJECT_NAME = "STORY_OBJECT";
    // Name to store story number in intent
    static final String STORY_NUMBER = "STORY_NUMBER";
    // Max file index for the list of files for stories
    static final int MAX_INDEX = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);
        // Last screen sets the text to the story created by the user and displays it
        Intent intent = getIntent();
        Story story = (Story) intent.getSerializableExtra(OBJECT_NAME);
        TextView result_text = (TextView) findViewById(R.id.resultText);
        // fromHtml is needed to bold the words
        result_text.setText(Html.fromHtml(story.toString()));
    }

    // If make another story button is clicked, go to first screen and go to the next story
    // by increasing the story number and passing that to first screen
    public void newStory(View view)
    {
        Intent intent = getIntent();
        int storyNumber = intent.getIntExtra(STORY_NUMBER, 0);
        if(storyNumber == MAX_INDEX)
        {
            storyNumber = 0;
        }
        else
        {
            storyNumber++;
        }
        Intent newIntent = new Intent(this, FirstScreen.class);
        newIntent.putExtra(STORY_NUMBER, storyNumber);
        startActivity(newIntent);
        finish();
    }

    // If the back button was pressed I wanted it to go back to the first screen with the same
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
