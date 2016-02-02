package com.shpp.skobzin.lesson3_androidappcomponents;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillTextView();
    }

    private void fillTextView() {
        InputStream inputStream = null;
        byte[] buffer = null;
        try {
            inputStream = getAssets().open("poem_body.txt");
            int fileSize = inputStream.available();
            buffer = new byte[fileSize];
            inputStream.read(buffer);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String poemHeader = getString(R.string.poem_header);

        Spannable poem = new SpannableString(poemHeader + "\n\n" + new String(buffer));
        poem.setSpan(new StyleSpan(Typeface.BOLD), 0, poemHeader.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = (TextView) findViewById(R.id.tvPoem);
        textView.setText(poem);
    }
}
