package com.jsoniter.extra;

import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import junit.framework.TestCase;

import com.jsoniter.spi.JsonException;
import com.jsoniter.spi.Decoder;
import java.util.Date;
import java.io.IOException;

/**
    Increasing test coverage of createDecoder().
*/
public class TestGsonCompatibilityMode extends TestCase {
    /**
        Testing Date type.
    */
    public void test_createDecoder_Date() throws IOException{
        GsonCompatibilityMode gson = new GsonCompatibilityMode.Builder().build();

        // TEST 1: Should throw ParseException, since it shouldn't be able to parse the JSON below.
        JsonIterator it = JsonIterator.parse("{dfssdf,,.,.}");
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

        // TEST 3: Should return null, since the string contains a null object.
        it = JsonIterator.parse("null");
        assertEquals(dec.decode(it), null);

        // TEST 4: Should throw an error, since the array can't be converted to a string.
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

        // TEST 5: Should return a boolean with value true.
        JsonIterator it = JsonIterator.parse("true");
        Decoder dec = gson.createDecoder("", boolean.class);
        assertEquals(dec.decode(it), true);

        // TEST 6: Should throw an exception, since the int can't be converted to a boolean.
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

        // TEST 5: Should return the largest long possible.
        long max = Long.MAX_VALUE;
        JsonIterator it = JsonIterator.parse(Long.toString(max));
        Decoder dec = gson.createDecoder("", long.class);
        assertEquals(dec.decode(it), max);

        // TEST 6: Should throw an exception, since the boolean can't be converted to a long.
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

        // TEST 5: Should return the largest int possible.
        int max = Integer.MAX_VALUE;
        JsonIterator it = JsonIterator.parse(Integer.toString(max));
        Decoder dec = gson.createDecoder("", int.class);
        assertEquals(dec.decode(it), max);

        // TEST 6: Should throw an exception, since the boolean can't be converted to an integer.
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

    }

    /**
        Testing double type.
    */
    public void test_createDecoder_double() throws IOException{

    }
}
