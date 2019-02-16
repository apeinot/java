package com.jsoniter;

import junit.framework.TestCase;

import java.util.Map;

import com.jsoniter.CodegenImplNative;
import com.jsoniter.spi.*;
import com.jsoniter.spi.JsonException;

/**
Ensure better test coverage of genReadOp().
*/

public class TestCodegenImplNative extends TestCase{

    /**
    This functions tests the branch (valueType == boolean.class)
    One outcome is the standard string, if no correct decoder has
    been set by the test environment. Otherwise a JsonExecption
    is expected.
    */
    public void test_genReadOp_boolean(){
        String key = "jsoniter_codegen.cfg1830135045.decoder.boolean";
        Map<Class, Decoder> map = CodegenImplNative.NATIVE_DECODERS;
        JsoniterSpi.addNewDecoder(key, map.get(boolean.class));
        try{
            String s = CodegenImplNative.genReadOp(boolean.class);
            // some test runs do not build up the entire system, which would
            // be needed for the exeception
            // In this case, we check for the standard string
            assertEquals(s.contains("(boolean)iter.readBoolean()"),true);
        }
        catch(JsonException e){
            // expect certain message in the execption
            assertEquals(e.getMessage().contains("must implement"), true);
        }
    }

    /**
    This functions tests the branch (valueType == float.class)
    One outcome is the standard string, if no correct decoder has
    been set by the test environment. Otherwise a JsonExecption
    is expected.
    */
    public void test_genReadOp_float(){
        String key = "jsoniter_codegen.cfg9223372034806973682.decoder.float";
        Map<Class, Decoder> map = CodegenImplNative.NATIVE_DECODERS;
        JsoniterSpi.addNewDecoder(key, map.get(float.class));
        try{
            String s = CodegenImplNative.genReadOp(float.class);
            // some test runs do not build up the entire system, which would
            // be needed for the exeception
            // In this case, we check for the standard string
            assertEquals(s.contains("(float)iter.readFloat()"),true);
        }
        catch(JsonException e){
            // expect certain message in the execption
            assertEquals(e.getMessage().contains("must implement"), true);
        }
    }

    /**
    This functions tests the branch (valueType == double.class)
    One outcome is the standard string, if no correct decoder has
    been set by the test environment. Otherwise a JsonExecption
    is expected.
    */
    public void test_genReadOp_double(){
        String key = "jsoniter_codegen.cfg9223372034806973682.decoder.double";
        Map<Class, Decoder> map = CodegenImplNative.NATIVE_DECODERS;
        JsoniterSpi.addNewDecoder(key, map.get(double.class));
        try{
            String s = CodegenImplNative.genReadOp(double.class);
            // some test runs do not build up the entire system, which would
            // be needed for the exeception
            // In this case, we check for the standard string
            assertEquals(s.contains("(double)iter.readDouble()"),true);
        }
        catch(JsonException e){
            // expect certain message in the execption
            assertEquals(e.getMessage().contains("must implement"), true);
        }
    }    

}