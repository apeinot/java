package com.jsoniter;
import com.jsoniter.spi.*;

import junit.framework.TestCase;

public class MoreIterImplForStreamingTest extends TestCase {

    /**
    Test case for readStringSlowPath of IterImplForStreaming for escaped characters
    \b, \n, \f and \r. Expected result is 4 as 4 characters should be written
    into the iterators buffer.
    */
	public void testReadStringSlowPath() throws Exception {
        JsonIterator iter = JsonIterator.parse("\\b\\n\\f\\r\"");
        int number = IterImplForStreaming.readStringSlowPath(iter, 0);
        assertEquals(number, 4);
    }

    /**
    Test case for readNumber of IterImplForStreaming where the buffer of the
    iterator overflows. Expected result is that the returned class contains the
    same string as the iterator was reading.
    */
    public void testReadNumber() throws Exception {
        String num = "123456789012345678901234567890123";
        JsonIterator iter = JsonIterator.parse(num);
        IterImplForStreaming.numberChars numberChars = IterImplForStreaming.readNumber(iter);
        String num2 = new String(numberChars.chars, 0, numberChars.charsLength);
        assertEquals(num, num2);
    }

    /**
    A test case for readStringSlowPath where the buffer of the iterator overflows,
    and the newly created larger buffer overflows as well. As the string being
    input is a unicode multibyte character, there should be 2 bytes written.
    */
    public void testReadStringSlowPath_multibyte() throws Exception {
        byte[] b = new byte[]{(byte)0xf0, (byte)0x91, (byte)0x9a, (byte)0x83, '"'};
        JsonIterator iter = JsonIterator.parse(b);
        iter.reusableChars = new char[1];
        int num = IterImplForStreaming.readStringSlowPath(iter, 1);
        assertEquals(num, 3);
    }

    /**
    Test case for readStringSlowPath when an illegal unicode multibyte character
    is entered. In this case an exception with the message invalid unicode
    character should be thrown.
    */
    public void testReadStringSlowPath_multibyte_2() throws Exception {
        byte[] b = new byte[]{(byte)0xf8, (byte)0x76, (byte)0x55, (byte)0x23, '"'};
        JsonIterator iter = JsonIterator.parse(b);
        try {
            int num = IterImplForStreaming.readStringSlowPath(iter, 0);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage().contains("invalid unicode character"), true);
        }

        //System.out.println("hejsan " + num);
    }
}
