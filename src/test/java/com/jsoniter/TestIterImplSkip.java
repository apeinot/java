package com.jsoniter;

import junit.framework.TestCase;

import com.jsoniter.JsonIterator;

import java.io.IOException;

import com.jsoniter.IterImplSkip;
import com.jsoniter.spi.JsonException;

/**
Ensures (almost) full test coverage of skip().
*/

public class TestIterImplSkip extends TestCase{

    /**
    This function tests the only remaining branch in the skip function
    which has not been tested yet - the default branch, which leads
    the function to throw a JsonException
    * @throws IOException
    */
    public void test_skip_default() throws IOException{
        JsonIterator iter = JsonIterator.parse(new byte[]{'a', 9, 8, 7, 6});
        try{
            IterImplSkip.skip(iter);
            fail();
        }
        catch(JsonException e){
            assertEquals(e.getMessage().contains("do not know how to skip"), true);
        }
    }

}