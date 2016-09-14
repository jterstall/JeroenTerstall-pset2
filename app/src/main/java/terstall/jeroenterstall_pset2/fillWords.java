package terstall.jeroenterstall_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class fillWords extends AppCompatActivity
{
    static final String OBJECT_NAME = "STORY_OBJECT";
    static final String STORY_NUMBER = "STORY_NUMBER";
    static final String words_remaining_right = " word(s) left";
    static final String word_description_left = "Please type a/an ";
    String words_remaining;
    String word_description;
    String nextPlaceholder;
    int placeholderCount;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_words);

        Intent intent = getIntent();
        Story story = (Story) intent.getSerializableExtra(OBJECT_NAME);
        int storyNumber = intent.getIntExtra(STORY_NUMBER, 0);
        if(story.isFilledIn() != true)
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
        else
        {
            Intent nextIntent = new Intent(this, showText.class);
            nextIntent.putExtra(OBJECT_NAME, story);
            nextIntent.putExtra(STORY_NUMBER,storyNumber);
            startActivity(nextIntent);
        }
    }

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
            return true;
        }
        return false;
    }

    public void nextPlaceholder(View view)
    {
        Intent intent = getIntent();
        Story story = (Story) intent.getSerializableExtra(OBJECT_NAME);
        int storyNumber = intent.getIntExtra(STORY_NUMBER, 0);
        EditText editwordtype = (EditText) findViewById(R.id.edit_word_type);
        String input = editwordtype.getText().toString();
        if(input.trim().length() != 0)
        {
            story.fillInPlaceholder("<b>" + input + "</b>");
            Intent newIntent = new Intent(this, fillWords.class);
            newIntent.putExtra(OBJECT_NAME, story);
            newIntent.putExtra(STORY_NUMBER, storyNumber);
            startActivity(newIntent);
        }
        else
        {
            System.out.println("Use valid input");
        }

    }
}
