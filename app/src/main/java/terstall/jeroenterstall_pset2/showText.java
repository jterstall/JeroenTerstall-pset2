package terstall.jeroenterstall_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class showText extends AppCompatActivity
{
    static final String OBJECT_NAME = "STORY_OBJECT";
    static final String STORY_NUMBER = "STORY_NUMBER";
    static final int MAX_INDEX = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);
        Intent intent = getIntent();
        Story story = (Story) intent.getSerializableExtra(OBJECT_NAME);
        TextView result_text = (TextView) findViewById(R.id.resultText);
        result_text.setText(Html.fromHtml(story.toString()));
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
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
            return true;
        }
        return false;
    }

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
    }
}
