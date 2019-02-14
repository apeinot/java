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
	computes the percentage of covered branches for the function skip
	and prints it to stdout
	*/
	public void test_coverage_skip(){
		boolean[] branch = IterImplSkip.cover_skip;
		int size = branch.length;
		int count = 0;
		for(int i = 0; i < size; i++){
			if(branch[i]){
				count++;
			}
		}
		double coverage = ((double) count)/size;
		System.out.println("IterImplSkip::skip branch coverage:");
		System.out.println(coverage*100 + "%");
	}
}
