package com.mycompany.bodmascalculatorinandroid;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.test.ActivityTestCase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by amandeepsingh on 25/06/15.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> main = new ActivityTestRule<MainActivity>(MainActivity.class);

    EditText editText;
    TextView errorText;
    Button equalButton;

    @Before
    public void getUiElements() {

        editText = (EditText) main.getActivity().findViewById(R.id.edit_text);
        errorText = (TextView) main.getActivity().findViewById(R.id.error_message);
        equalButton = (Button) main.getActivity().findViewById(R.id.equal_button);
    }


    @Test
    public void testShouldLaunchMainActivityAndCheckItsContent() {

        assertThat(View.VISIBLE, is(editText.getVisibility()));
        assertThat(editText.getText().toString(), is(""));

        assertThat(View.VISIBLE, is(equalButton.getVisibility()));
        assertThat(equalButton.getText().toString(), is("="));

        assertThat(View.GONE, is(errorText.getVisibility()));
    }

    @Test
    public void itShouldCalculateResultAndShowThem() {

        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                editText.setText("5+6*(7-3)/2");
                equalButton.performClick();
                assertThat(editText.getText().toString(), is("17.0"));
            }
        });

        assertThat(View.GONE, is(errorText.getVisibility()));

    }

    @Test
    public void erroeMessageShouldBeVisibleAfterAddingInvalidInput() {

        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                editText.setText("5+6*(7-3)2");
                equalButton.performClick();
            }
        });

        assertThat(View.VISIBLE, is(errorText.getVisibility()));
        assertThat(editText.getText().toString(), is(""));

    }


}