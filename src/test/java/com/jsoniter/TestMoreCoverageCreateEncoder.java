package com.jsoniter;

import junit.framework.TestCase;

import java.io.IOException;

import java.lang.reflect.Type;

import com.jsoniter.extra.GsonCompatibilityMode;

import com.jsoniter.spi.JsonException;

import com.jsoniter.output.JsonStream;

import java.io.OutputStream;

public class TestMoreCoverageCreateEncoder extends TestCase {

    /**
    Test a certain character as input for the encoder. The character was not tested in previous suites.
    The expected result is, including quotation marks: "\u2028"
    */
    public void test_special_character_1() throws IOException{
        Type t = String.class;
        JsonStream js = new JsonStream(null, 100);
        GsonCompatibilityMode g = new GsonCompatibilityMode.Builder().build();
        String testString = "" + '\u2028';
        g.createEncoder("hello",t).encode(testString, js);
        assertEquals(js.buffer().toString(), "\"\\u2028\"");
    }

    /**
    Test a certain character as input for the encoder. The character was not tested in previous suites.
    The expected result is, including quotation marks: "\u2029"
    */
    public void test_special_character_2() throws IOException{
        Type t = String.class;
        JsonStream js = new JsonStream(null, 100);
        GsonCompatibilityMode g = new GsonCompatibilityMode.Builder().build();
        String testString = "" + '\u2029';
        g.createEncoder("hello",t).encode(testString, js);
        assertEquals(js.buffer().toString(), "\"\\u2029\"");
    }

    /**
    Tests the statements that checks for illigal surrogates. If the character is greater
    than 0xDBFF then the staement trigger.
    */
    public void test_GT_SURR1_LAST() throws IOException{
        Type t = String.class;
        JsonStream js = new JsonStream(null, 100);
        GsonCompatibilityMode g = new GsonCompatibilityMode.Builder().build();
        String testString = "" + '\uDC00';
        try{
            g.createEncoder("hello",t).encode(testString, js);
            fail();
        }catch(Exception e){
            assertEquals(e.getMessage().contains("illegalSurrogate"), true);
        }
    }

    /**
    Tests the statement that checks for broken surrogate pairs. The input consist
    of two chars with abnormal codes. This fools the encoder to believe it is a 4-byte node.
    Via careful tinkering with the two characters you can trigger the last statement.
    Since this statments contains the logical or operator we perform test twice.
    This way we gain full coverage of the if statement.
    */
    public void test_LT_SURR2_FIRST_OR_GT_SURR2_LAST() throws IOException{
        Type t = String.class;
        JsonStream js = new JsonStream(null, 100);
        GsonCompatibilityMode g = new GsonCompatibilityMode.Builder().build();
        String testString = "" + '\uDBFF' + '\uDBFF'; //Less than 0xDC00
        try{
            g.createEncoder("hello",t).encode(testString, js);
            fail();
        }catch(Exception e){
            assertEquals(e.getMessage().contains("Broken surrogate pair"), true);
        }

        js = new JsonStream(null, 100);
        g = new GsonCompatibilityMode.Builder().build();
        testString = "" + '\uDBFF' + '\uE000'; //greater than 0xDFF
        try{
            System.out.println("Correct");
            g.createEncoder("hello",t).encode(testString, js);
            fail();
        }catch(Exception e){
            assertEquals(e.getMessage().contains("Broken surrogate pair"), true);
        }
    }

}
