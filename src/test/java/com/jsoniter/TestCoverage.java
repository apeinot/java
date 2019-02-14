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
    Computes the percentage of covered branches of the function readNumber in
    IterImplForStreaming.java and prints it to standard output.
    }
    */
    public void test_coverage_readNumber(){
        boolean[] branch = IterImplForStreaming.cover_readNumber;
        int size = branch.length;
        int count = 0;
        for (int i = 0; i < size; i++){
            if (branch[i]){
                count++;
            }
        }
        double coverage = ((double)count)/size;
        System.out.println("IterImplForStreaming::readNumber() branch coverage:");
        System.out.print(coverage*100);
        System.out.println("%");
    }
}
