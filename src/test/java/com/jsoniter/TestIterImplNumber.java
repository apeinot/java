package com.jsoniter;

import junit.framework.TestCase;
import com.jsoniter.spi.*;

/**
    Testing IterImplNumber::readInt and by extension also IterImpl::readInt,
    since the first's functionality is based on the latter's.
*/
public class TestIterImplNumber extends TestCase{
    public void test_readInt() throws Exception{
        // Should yield an exception, since no int can be parsed from this iterator.
        JsonIterator iter = JsonIterator.parse("h");
        try{
            IterImplNumber.readInt(iter);
            fail();
        }catch(JsonException e){
            assertEquals(e.getMessage().contains("expect 0~9"), true);
        }
    }
}
