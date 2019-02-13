package com.jsoniter;

import junit.framework.TestCase;

import java.io.IOException;

import com.jsoniter.spi.OmitValue;

public class TestCoverage extends TestCase {


    public void test_coverage_getDecoder(){
	boolean[] branch = Codegen.cover_getDecoder;
	int size = branch.length;
	int count = 0;
	for (int i = 0; i<size; i++){
	    if (branch[i]){
	        count++;
	    }
	}
	double coverage = ((double)count)/size;
	System.out.println("Codegen::get_Decoder() branch coverage:");
        System.out.print(coverage*100);
	System.out.println("%");
    }


    public void test_coverage_parse(){
    	boolean[] branch = OmitValue.Parsed.cover_parse;
    	int size = branch.length;
    	int count = 0;
    	for (int i = 0; i<size; i++){
    	    if (branch[i]){
    	        count++;
    	    }
    	}
    	double coverage = ((double)count)/size;
    	System.out.println("OmitValue::parse() branch coverage:");
            System.out.print(coverage*100);
    	System.out.println("%");
    }
}
