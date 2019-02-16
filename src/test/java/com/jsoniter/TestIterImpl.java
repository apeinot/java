package com.jsoniter;

import junit.framework.TestCase;
import com.jsoniter.spi.JsonException;

public class TestIterImpl extends TestCase {

	public void testEscapeCode_b() throws Exception{
	    String escape = "\\b\"";
	    JsonIterator iter = JsonIterator.parse(escape);
	    int j = 0;
	    int test = IterImpl.readStringSlowPath(iter, j);
	    assertEquals(test, 1);
        } 

	public void testEscapeCode_n() throws Exception{
            String escape = "\\n\"";
            JsonIterator iter = JsonIterator.parse(escape);
            int j = 0;
            int test = IterImpl.readStringSlowPath(iter, j);
            assertEquals(test, 1);
        }

	public void testEscapeCode_f() throws Exception{
            String escape = "\\f\"";
            JsonIterator iter = JsonIterator.parse(escape);
            int j = 0;
            int test = IterImpl.readStringSlowPath(iter, j);
            assertEquals(test, 1);
        }

	public void testEscapeCode_r() throws Exception{
            String escape = "\\r\"";
            JsonIterator iter = JsonIterator.parse(escape);
            int j = 0;
            int test = IterImpl.readStringSlowPath(iter, j);
            assertEquals(test, 1);
        }

	public void testLowSurrogate() throws Exception{
            String escape = "\\ud832\\udc84\"";
            JsonIterator iter = JsonIterator.parse(escape);
            int j = 0;
            int test = IterImpl.readStringSlowPath(iter, j);
            assertEquals(test, 2);
        }

	public void testErrorSurrogate() throws Exception{
	    try{	
                String escape = "\\ud832\\u30a6\"";
                JsonIterator iter = JsonIterator.parse(escape);
		int j = 0;
                int test = IterImpl.readStringSlowPath(iter, j);
		fail();
	    } catch (JsonException e){
                assertEquals(e.getMessage(), "invalid surrogate");
	    }
        }

	public void testSplitSurrogate_reusablefull() throws Exception{
	    byte b1 = (byte) -16;
	    byte b2 = (byte) -18;
	    byte b3 = (byte) -18;
	    byte b4 = (byte) -18;
	    String str = "\"";
	    byte[] b5 = str.getBytes();
	    byte[] text = new byte[5];
	    text[0] = b1;
	    text[1] = b2;
	    text[2] = b3;
	    text[3] = b4;
	    text[4] = b5[0];
            JsonIterator iter = JsonIterator.parse(text);
            int j = 32;
            int test = IterImpl.readStringSlowPath(iter, j);
            assertEquals(test, 34);
        }

	public void testSplitSurrogate_reusableOneplaceleft() throws Exception{
            byte b1 = (byte) -16;
            byte b2 = (byte) -18;
            byte b3 = (byte) -18;
            byte b4 = (byte) -18;
            String str = "\"";
            byte[] b5 = str.getBytes();
            byte[] text = new byte[5];
            text[0] = b1;
            text[1] = b2;
            text[2] = b3;
            text[3] = b4;
            text[4] = b5[0];
            JsonIterator iter = JsonIterator.parse(text);
            int j = 31;
            int test = IterImpl.readStringSlowPath(iter, j);
            assertEquals(test, 33);
        }
}

