package com.jsoniter.extra;

import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import junit.framework.TestCase;

import com.jsoniter.spi.JsonException;
import com.jsoniter.spi.Decoder;
import java.util.Date;
import java.io.IOException;

import java.lang.reflect.Type;
/**
    Ensures (almost) full test coverage of createDecoder().
*/
public class TestGsonCompatibilityMode extends TestCase {
    /**
        Testing Date type.
    */
    public void test_createDecoder_Date() throws IOException{
        GsonCompatibilityMode gson = new GsonCompatibilityMode.Builder().build();

        // TEST 1: Should throw ParseException, since it shouldn't be able to parse the JSON below to a Date object.
        JsonIterator it = JsonIterator.parse("52");
        Decoder dec = gson.createDecoder("", Date.class);
        try{
            dec.decode(it);
            fail();
        }catch(JsonException e){
            assertEquals(e.getMessage().contains("expect string or null"), true);
        }
    }

    /**
        Testing String type.
    */
    public void test_createDecoder_String() throws IOException{
        GsonCompatibilityMode gson = new GsonCompatibilityMode.Builder().build();

        // TEST 2: Should return a boolean string that says true.
        JsonIterator it = JsonIterator.parse("true");
        Decoder dec = gson.createDecoder("", String.class);
        assertEquals(dec.decode(it), "true");

        // TEST 3: Should return a boolean string that says false.
        it = JsonIterator.parse("false");
        assertEquals(dec.decode(it), "false");

        // TEST 4: Should return null, since the string contains a null object.
        it = JsonIterator.parse("null");
        assertEquals(dec.decode(it), null);

        // TEST 5: Should throw an error, since the array can't be converted to a string.
        it = JsonIterator.parse("{\"array\":[1,2,3]}");
        try{
            dec.decode(it);
            fail();
        }catch(JsonException e){
            assertEquals(e.getMessage().contains("expect string, but found OBJECT"), true);
        }
    }

    /**
        Testing boolean type.
    */
    public void test_createDecoder_boolean() throws IOException{
        GsonCompatibilityMode gson = new GsonCompatibilityMode.Builder().build();

        // TEST 6: Should return a boolean with value true.
        JsonIterator it = JsonIterator.parse("true");
        Decoder dec = gson.createDecoder("", boolean.class);
        assertEquals(dec.decode(it), true);

        // TEST 7: Should throw an exception, since the int can't be converted to a boolean.
        it = JsonIterator.parse("52");
        try{
            dec.decode(it);
            fail();
        }catch(JsonException e){
            assertEquals(e.getMessage().contains("expect boolean, but found NUMBER"), true);
        }
    }

    /**
        Testing long type.
    */
    public void test_createDecoder_long() throws IOException{
        GsonCompatibilityMode gson = new GsonCompatibilityMode.Builder().build();

        // TEST 8: Should return the largest long possible.
        long max = Long.MAX_VALUE;
        JsonIterator it = JsonIterator.parse(Long.toString(max));
        Decoder dec = gson.createDecoder("", long.class);
        assertEquals(dec.decode(it), max);

        // TEST 9: Should throw an exception, since the boolean can't be converted to a long.
        it = JsonIterator.parse("true");
        try{
            dec.decode(it);
            fail();
        }catch(JsonException e){
            assertEquals(e.getMessage().contains("expect long, but found BOOLEAN"), true);
        }
    }

    /**
        Testing int type.
    */
    public void test_createDecoder_int() throws IOException{
        GsonCompatibilityMode gson = new GsonCompatibilityMode.Builder().build();

        // TEST 10: Should return the largest int possible.
        int max = Integer.MAX_VALUE;
        JsonIterator it = JsonIterator.parse(Integer.toString(max));
        Decoder dec = gson.createDecoder("", int.class);
        assertEquals(dec.decode(it), max);

        // TEST 11: Should throw an exception, since the boolean can't be converted to an integer.
        it = JsonIterator.parse("true");
        try{
            dec.decode(it);
            fail();
        }catch(JsonException e){
            assertEquals(e.getMessage().contains("expect int, but found BOOLEAN"), true);
        }
    }

    /**
        Testing float type.
    */
    public void test_createDecoder_float() throws IOException{
        GsonCompatibilityMode gson = new GsonCompatibilityMode.Builder().build();

        // TEST 12: Should return the same float value as contained in the testFloat variable.
        float testFloat = 3.333f;
        JsonIterator it = JsonIterator.parse(Float.toString(testFloat));
        Decoder dec = gson.createDecoder("", float.class);
        assertEquals(dec.decode(it), testFloat);

        // TEST 13: Should throw an exception, since the boolean can't be converted to a float.
        it = JsonIterator.parse("true");
        try{
            dec.decode(it);
            fail();
        }catch(JsonException e){
            assertEquals(e.getMessage().contains("expect float, but found BOOLEAN"), true);
        }
    }

    /**
        Testing double type.
    */
    public void test_createDecoder_double() throws IOException{
        GsonCompatibilityMode gson = new GsonCompatibilityMode.Builder().build();

        // TEST 14: Should return the same double value as contained in the testDouble variable..
        double testDouble = 17.2385842;
        JsonIterator it = JsonIterator.parse(Double.toString(testDouble));
        Decoder dec = gson.createDecoder("", double.class);
        assertEquals(dec.decode(it), testDouble);

        // TEST 15: Should throw an exception, since the boolean can't be converted to a double.
        it = JsonIterator.parse("true");
        try{
            dec.decode(it);
            fail();
        }catch(JsonException e){
            assertEquals(e.getMessage(), "expect float, but found BOOLEAN");
        }
    }


    /**
    Test a certain character as input for the encoder. The character was not tested in previous suites.
    The expected result is, including quotation marks: "\u2028"
    */
    public void test_encoder_special_character_1() throws IOException{
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
    public void test_encoder_special_character_2() throws IOException{
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
    public void test_encoder_GT_SURR1_LAST() throws IOException{
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
    public void test_encoder_LT_SURR2_FIRST_OR_GT_SURR2_LAST() throws IOException{
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
