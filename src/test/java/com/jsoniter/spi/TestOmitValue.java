package com.jsoniter.spi;

import junit.framework.TestCase;

import java.io.IOException;

import com.jsoniter.spi.OmitValue;

/**
This class tests the OmitValue interface. Currently there is only one test
*/
public class TestOmitValue extends TestCase {

    /**
        This test provides really bad input data for the parse function.
        Forcing it to throw an error.
    */
    public void test_parse_trow_statement(){
        try {
            OmitValue.Parsed.parse(null, "987936481736059"); //Special code for error message identification
            fail("The wrong message");
        }catch(Exception e){
            assertEquals(true, e.getMessage().contains("failed to parse defaultValueToOmit: 987936481736059"));
        }
    }
}
