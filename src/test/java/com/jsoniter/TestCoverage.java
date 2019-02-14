package com.jsoniter;

import junit.framework.TestCase;

import java.io.IOException;

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
	double coverage = count/size;
	System.out.println("Codegen::get_Decoder() branch coverage:");
        System.out.print(coverage*100);
	System.out.println("%");
    }

    /**
    Computes the percentage of branches covered for function readStringSlowPath
    in IterImplForStreaming.java and prints them to standard output
    */
    public void test_coverage_readStringSlowPath(){
        boolean[] branch = IterImplForStreaming.cover_readStringSlowPath;
    	int size = branch.length;
    	int count = 0;
    	for (int i = 0; i<size; i++){
    	    if (branch[i]){
    	        count++;
    	    }
    	}
    	double coverage = ((double)count)/size;
    	System.out.print("IterImplForStreaming::readStringSlowPath() branch coverage: ");
        System.out.print(coverage*100);
    	System.out.println("%");
    }
}
