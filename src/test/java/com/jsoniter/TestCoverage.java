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
	double coverage = ((double) count)/size;
	System.out.println("Codegen::get_Decoder() branch coverage:");
        System.out.print(coverage*100);
	System.out.println("%");
    }

    public void test_coverage_chooseImpl(){
        boolean[] branch = Codegen.cover_chooseImpl;
        int size = branch.length;
        int count = 0;
        for (int i = 0; i<size; i++){
            if (branch[i]){
                count++;
            }
        }
        double coverage = ((double) count)/size;
        System.out.println("Codegen::get_chooseImpl() branch coverage:");
        System.out.print(coverage*100);
        System.out.println("%");
    }

}
