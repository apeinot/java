package com.jsoniter;

import junit.framework.TestCase;
import com.jsoniter.spi.JsonException;

public class TestIterImpl extends TestCase {

	/**Test case that cover the switch case for escape code \b
	We read only one character so j is only incremented by 1 and we assert that
	*/
	public void testEscapeCode_b() throws Exception{
	    String escape = "\\b\"";
	    JsonIterator iter = JsonIterator.parse(escape);
	    int j = 0;
	    int test = IterImpl.readStringSlowPath(iter, j);
	    assertEquals(test, 1);
        } 

	/**Test case that cover the switch case for escape code \n
        We read only one character so j is only incremented by 1 and we assert that
        */
	public void testEscapeCode_n() throws Exception{
            String escape = "\\n\"";
            JsonIterator iter = JsonIterator.parse(escape);
            int j = 0;
            int test = IterImpl.readStringSlowPath(iter, j);
            assertEquals(test, 1);
        }

	/**Test case that cover the switch case for escape code \f
        We read only one character so j is only incremented by 1 and we assert that
        */
	public void testEscapeCode_f() throws Exception{
            String escape = "\\f\"";
            JsonIterator iter = JsonIterator.parse(escape);
            int j = 0;
            int test = IterImpl.readStringSlowPath(iter, j);
            assertEquals(test, 1);
        }

	/**Test case that cover the switch case for escape code \r
        We read only one character so j is only incremented by 1 and we assert that
        */
	public void testEscapeCode_r() throws Exception{
            String escape = "\\r\"";
            JsonIterator iter = JsonIterator.parse(escape);
            int j = 0;
            int test = IterImpl.readStringSlowPath(iter, j);
            assertEquals(test, 1);
        }

	/**Test case that cover the switch case for escape code u with a high surrogate
	followed by a low surrogate
        We read only two characters so j is only incremented by 2 and we assert that
        */
	public void testLowSurrogate() throws Exception{
            String escape = "\\ud832\\udc84\"";
            JsonIterator iter = JsonIterator.parse(escape);
            int j = 0;
            int test = IterImpl.readStringSlowPath(iter, j);
            assertEquals(test, 2);
        }

	/**Test case that cover the switch case for escape code u with a high surrogate
        followed by a random unicode (which is neither low or high surrogate)
        This should result in a JsonException and we assert that the message is the good one
        */
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

	/**Test case that cover the case of a valid unicode where we split surrogate 
	and the reusableChars array is supposed to be full j==length
        We read only two characters (one unicode and ") so j is only incremented by 2 
	and we assert that
        */
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

	/**Test case that cover the case of a valid unicode where we split surrogate
        and the reusableChars array is supposed to have only one place remaining j==length-1
        We read only two characters (one unicode and ") so j is only incremented by 2
        and we assert that
        */
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

