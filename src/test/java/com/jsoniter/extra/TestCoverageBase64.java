package com.jsoniter.extra;
import junit.framework.TestCase;


import java.io.IOException;


public class TestCoverageBase64 extends TestCase {

    public void test_coverage_decodeFast(){
        boolean[] branch = Base64.cover_decodeFast;
        int size = branch.length;
        int count = 0;
        for (int i = 0; i<size; i++){
            if (branch[i]){
                count++;
            }
        }
        double coverage = ((double) count)/size;
        System.out.print("Base64::decodeFast() branch coverage: ");
        System.out.print(coverage*100);
        System.out.println("%");
    }
}
