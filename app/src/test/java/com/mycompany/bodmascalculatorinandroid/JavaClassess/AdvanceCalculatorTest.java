package com.mycompany.bodmascalculatorinandroid.JavaClassess;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by amandeepsingh on 25/06/15.
 */
public class AdvanceCalculatorTest {

    AdvanceCalculator advanceCalculator;

    @Before
    public void instantiateMembers() {
        advanceCalculator = new AdvanceCalculator();
    }

    @Test
    public void shouldInvalidateStringInput() {

        assertThat(advanceCalculator.findPostfix("89898vwjgdjyqgyuegyjq7687556"), is("Invalid expression"));
        assertThat(advanceCalculator.findPostfix("vwjgdjyqgyuegyjq"), is("Invalid expression"));
        assertThat(advanceCalculator.findPostfix(""), is(""));
        assertThat(advanceCalculator.findPostfix("9++9"), is("Invalid expression"));
        assertThat(advanceCalculator.findPostfix("9+9*(8*7//9)"), is("Invalid expression"));
        assertThat(advanceCalculator.findPostfix("()()()()"), is("Invalid expression"));
        assertThat(advanceCalculator.findPostfix("()(())"), is("Invalid expression"));

    }

    @Test
    public void shouldInvalidateUnequalBraces() {

        assertThat(advanceCalculator.findPostfix("()()()()()())"), is("Invalid expression"));
        assertThat(advanceCalculator.findPostfix("()()()()()()()(((("), is("Invalid expression"));
        assertThat(advanceCalculator.findPostfix(")()())()()(()()(()(()()()()()"), is("Invalid expression"));

    }

    @Test
    public void shouldInvalidateIllegalOperatorBracketMatchingExpression() {

        assertThat(advanceCalculator.findPostfix("9*(6+2)8"), is("Invalid expression"));
        assertThat(advanceCalculator.findPostfix("7*(7*0)*9(8+3)"), is("Invalid expression"));
        assertThat(advanceCalculator.findPostfix("7*(6+6/)-8"), is("Invalid expression"));
        assertThat(advanceCalculator.findPostfix("7*(*8)"), is("Invalid expression"));

    }

    @Test
    public void shouldCoverAllTheHappyPaths() {

        String input;
        input = advanceCalculator.findPostfix("(9)");

        assertThat(advanceCalculator.evaluatePostfix(input), is(9.00f));

        input = advanceCalculator.findPostfix("2+(9*(8+(5/5)))");
        assertThat(advanceCalculator.evaluatePostfix(input), is(83.00f));

        input = advanceCalculator.findPostfix("9*((8))");
        assertThat(advanceCalculator.evaluatePostfix(input), is(72.00f));

        input = advanceCalculator.findPostfix("9*(8+7)");
        assertThat(advanceCalculator.evaluatePostfix(input), is(135.00f));
    }

}