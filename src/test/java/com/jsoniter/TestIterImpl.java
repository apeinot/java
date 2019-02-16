package com.jsoniter;

import junit.framework.TestCase;

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
}
