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
}

