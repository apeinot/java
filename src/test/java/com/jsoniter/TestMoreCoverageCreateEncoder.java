package com.jsoniter;

import junit.framework.TestCase;

import java.io.IOException;

import java.lang.reflect.Type;

import com.jsoniter.extra.GsonCompatibilityMode;

import com.jsoniter.output.JsonStream;

import java.io.OutputStream;

public class TestMoreCoverageCreateEncoder extends TestCase {

    public void test_special_character_1() throws IOException{
        Type t = String.class;
        JsonStream js = new JsonStream(null, 100);
        GsonCompatibilityMode g = new GsonCompatibilityMode.Builder().build();
        String testString = "" + '\u2028';
        g.createEncoder("hello",t).encode(testString, js);
        assertEquals(js.buffer().toString(), "\"\\u2028\"");
    }

    public void test_special_character_2() throws IOException{
        Type t = String.class;
        JsonStream js = new JsonStream(null, 100);
        GsonCompatibilityMode g = new GsonCompatibilityMode.Builder().build();
        String testString = "" + '\u2029';
        g.createEncoder("hello",t).encode(testString, js);
        assertEquals(js.buffer().toString(), "\"\\u2029\"");
    }
}
