package com.mycompany.bodmascalculatorinandroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mycompany.bodmascalculatorinandroid.JavaClassess.AdvanceCalculator;


public class MainActivity extends ActionBarActivity {

    AdvanceCalculator advanceCalculator;
    EditText textArea;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textArea = (EditText) findViewById(R.id.edit_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getTextFromButton(View view) {

        String buttonText = (String) ((Button)view).getText();


        text = textArea.getText().toString();
        text = text+buttonText;
        textArea.setText(text);


    }

    public void evaluateExpression(View view) {
        advanceCalculator = new AdvanceCalculator();
        text = textArea.getText().toString();

        String postfixExpression = advanceCalculator.findPostfix(text);
        float result = advanceCalculator.evaluatePostfix(postfixExpression);
//        TODO You can make it like 2+4=6.0*2=12.0..so that further you can take actions..however i don't want it that messy;)
        textArea.setText(""+result);

    }

    public void clearTextField(View view) {
        textArea.setText("");
    }

    

}
