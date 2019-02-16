package com.jsoniter.extra;

import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import junit.framework.TestCase;
//import junit.framework.Assert;

import com.jsoniter.spi.JsonException;
import com.jsoniter.spi.Decoder;
import java.util.Date;
import java.io.IOException;

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
    public void test_createDecoder_boolean(){
        GsonCompatibilityMode gson = new GsonCompatibilityMode.Builder().build();
    }
}
