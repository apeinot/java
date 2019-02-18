package com.jsoniter.extra;

import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import junit.framework.TestCase;

public class TestBase64 extends TestCase {
    static {
        Base64Support.enable();
    }

    public void test_encode() {
        assertEquals("\"YWJj\"", JsonStream.serialize("abc".getBytes()));
    }

    public void test_decode() {
        assertEquals("abc", new String(JsonIterator.deserialize("\"YWJj\"", byte[].class)));
    }

    /**
    Test the case of an empty array in decodeFast()
    Increase coverage of decodeFast() and findEnd()
    */
    public void test_decodeFast_nullLen() {
	byte[] test = new byte[0];
	int start = 0;
	int end = Base64.findEnd(test, start);
	assertEquals(end, 0);
	byte [] result = Base64.decodeFast(test, start, end);
	assertEquals(result.length, 0);
    }
    
}
