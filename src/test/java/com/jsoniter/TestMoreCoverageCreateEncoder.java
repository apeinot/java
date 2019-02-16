package com.jsoniter;

import junit.framework.TestCase;

import java.io.IOException;

import java.lang.reflect.Type;

import com.jsoniter.extra.GsonCompatibilityMode;

import com.jsoniter.spi.JsonException;

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
