package com.jsoniter;

import junit.framework.TestCase;

import com.jsoniter.CodegenImplNative;

import java.io.IOException;

import com.jsoniter.spi.Config;
import com.jsoniter.spi.OmitValue;
import com.jsoniter.extra.GsonCompatibilityMode;

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
	System.out.print("Codegen::get_Decoder() branch coverage: ");
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
	System.out.print("IterImplSkip::skip branch coverage:");
	System.out.println(coverage*100 + "%");
    }

    /**
    Computes the percentage of covered branches of the function createEncoder in
    extra/GsonCompatibilityMode.java and prints it to standard output.
    */
    public void test_coverage_createEncoder(){
	boolean[] branch = GsonCompatibilityMode.cover_createEncoder;
	int size = branch.length;
	int count = 0;
	for (int i = 0; i<size; i++){
	    if (branch[i]){
	        count++;
	    }
	}
	double coverage = ((double)count)/size;
	System.out.print("GsonCompatibilityMode::createEncoder() branch coverage:");
        System.out.print(coverage*100);
	System.out.println("%");
    }

    public void test_coverage_createDecoder(){
        boolean[] branch = GsonCompatibilityMode.cover_createDecoder;
	int size = branch.length;
        int count = 0;
        for (int i = 0; i<size; i++){
            if (branch[i]){
                count++;
            }
        }
        double coverage = ((double) count) / size;
        System.out.print("GsonCompatibilityMode::createDecoder() branch coverage: ");
        System.out.println(coverage*100 + "%");
    }

    public void test_coverage_updateBindings(){
	boolean[] branch = Config.cover_updateBindings;
	int size = branch.length;
	int count = 0;
	for(int i = 0; i < size; i++){
	    if(branch[i]){
		count++;
	    }
	}
	double coverage = ((double) count)/size;
	System.out.print("Config::updateBindings() branch coverage:");
	System.out.println(coverage*100 + "%");
    }

    /**
    Computes the percentage of covered branches of the function parse in
    spi/OmitValue.java and prints it to standard output.
    */
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
        System.out.print("OmitValue::parse() branch coverage:");
        System.out.print(coverage*100);
        System.out.println("%");
    }

    /**
    Computes the percentage of covered branches of the function readNumber in
    IterImplForStreaming.java and prints it to standard output.
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
        System.out.print("IterImplForStreaming::readNumber() branch coverage: ");
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
    
    public void test_coverage_genReadOp(){
        boolean[] branch = CodegenImplNative.cover_genReadOp;
        int size = branch.length;
    	int count = 0;
    	for (int i = 0; i<size; i++){
    	    if (branch[i]){
    	        count++;
    	    }
    	}
    	double coverage = ((double) count) / size;
    	System.out.print("CodegenImplNative::genReadOp() branch coverage:");
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
        System.out.print("Codegen::chooseImpl() branch coverage: ");
        System.out.print(coverage*100);
        System.out.println("%");
    }
}
