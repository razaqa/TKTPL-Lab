package id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for all functions in MainActivity class.
 */
public class MainActivityUnitTest {

    @Test
    public void checkPalindrome_GivenTrueLowercasePalindromeInputText_ShouldReturnConfirmed() {
        String input = "abbcbaabcbba";
        String output = MainActivity.checkPalindrome(input);

        assertEquals(MainActivity.IS_PALINDROME, output);
    }

    @Test
    public void checkPalindrome_GivenTrueLettercasePalindromeInputText_ShouldReturnConfirmed() {
        String input = "AbbcBaAbCBbA";
        String output = MainActivity.checkPalindrome(input);

        assertEquals(MainActivity.IS_PALINDROME, output);
    }

    @Test
    public void checkPalindrome_GivenTruePalindromeInputTextAndSymbol_ShouldReturnConfirmed() {
        String input = "abbcba01a@@a10abcbba";
        String output = MainActivity.checkPalindrome(input);

        assertEquals(MainActivity.IS_PALINDROME, output);
    }

    @Test
    public void checkPalindrome_GivenEmptyInput_ShouldReturnConfirmed() {
        String input = "";
        String output = MainActivity.checkPalindrome(input);

        assertEquals(MainActivity.IS_PALINDROME, output);
    }

    @Test
    public void checkPalindrome_GivenSingleInputText_ShouldReturnConfirmed() {
        String input = "A";
        String output = MainActivity.checkPalindrome(input);

        assertEquals(MainActivity.IS_PALINDROME, output);
    }

    @Test
    public void checkPalindrome_GivenFalsePalindromeInput_ShouldReturnNotPalindrome() {
        String input = "absSJjj9@sbaaAaSdasaRcbBa";
        String output = MainActivity.checkPalindrome(input);

        assertEquals(MainActivity.NOT_PALINDROME, output);
    }
}