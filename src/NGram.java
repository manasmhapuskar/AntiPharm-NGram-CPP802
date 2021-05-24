
import java.io.*;
import java.lang.*;
import java.util.*;

public class NGram extends Object {
  public String compare (String str1, String str2, int ng) {
    StringBuffer result = new StringBuffer("Result: Similarity of two strings: \n");
    if (ng < 2 | ng > 9) {
       result.append("Abort! This program does not support grams bigger than 9 or smaller than 2.");
    }
    String str = " _";
    char blank = str.charAt(0);
    char underline = str.charAt(1);
    str1 = str1.replace(blank, underline);
    str2 = str2.replace(blank, underline);
    StringBuffer computeStringDiff = new StringBuffer("");
    StringBuffer computeStringSum = new StringBuffer("");

    Hashtable gramList1 = new Hashtable();
    int len = str1.length();
    for (int i = 0; i <= (len - ng); i++) {
      String gr = str1.substring(i, i + ng);
      Gram gram = new Gram(gr,str1);
      if (gram.occurences > 0) { 
        gramList1.put(gram.gram, gram);
      }
    }
    Hashtable gramList2 = new Hashtable();
    len = str2.length();
    for (int i = 0; i <= (len - ng); i++) {
      String gr = str2.substring(i, i + ng);
      Gram gram = new Gram(gr,str2); 
      if (gram.occurences > 0) {
        gramList2.put(gram.gram, gram);
      }
    }
    double difference = 0;
    double sum = 0;
    Hashtable diffGrams = new Hashtable();

    result.append("Vector 1: (");
    Enumeration keys1 = gramList1.keys();
    while (keys1.hasMoreElements()) {
      String key = (String) keys1.nextElement();
      Gram g = (Gram) gramList1.get(key);
      result.append("\"" + g.gram + ":" + g.occurences);
      result.append("\", ");
      double plus = (g.occurences);
      computeStringSum.append("sq(\"" + g.gram + ":" + g.occurences + "\") + ");
      double squarePlus = Math.pow(plus, 2);
      sum = sum + squarePlus;
      if (gramList2.containsKey(key)) {
        Gram gr2 = (Gram) gramList2.get(key);
        double minus = (g.occurences - gr2.occurences);
        computeStringDiff.append("sq(\"" + g.gram + ":" + g.occurences + "\" - \"" + gr2.gram + ":" + gr2.occurences + "\") + ");
        double square = Math.pow(minus, 2); 
        difference = difference + square; 
      } else {
        double square = Math.pow(g.occurences, 2);
        difference = difference + square;
        computeStringDiff.append("sq(\"" + g.gram + ":" + g.occurences + " - 0) + ");
      }
    }
    result.append(")\n");
    result.append("Vector 2: (");
    Enumeration keys2 = gramList2.keys();
    while (keys2.hasMoreElements()) {
      String key = (String) keys2.nextElement();
      Gram gr2 = (Gram) gramList2.get(key);
      if (gramList1.containsKey(key)) {} else {
        diffGrams.put(key, gr2);
      }
      result.append("\"" + gr2.gram + ":" + gr2.occurences);
      result.append("\", ");
    }
    Enumeration diffKeys = diffGrams.keys();
    while (diffKeys.hasMoreElements()) {
      String key = (String) diffKeys.nextElement();
      Gram diffGram = (Gram) diffGrams.get(key);
      double square = Math.pow(diffGram.occurences, 2);
      computeStringDiff.append("sq(0 - \"" + diffGram.gram + ":" + diffGram.occurences + "\") + ");
      difference = difference + square;
      computeStringSum.append("sq(\"" + diffGram.occurences + "\") + ");
      double squarePlus = Math.pow(diffGram.occurences, 2);
      sum = sum + squarePlus;
    }

    int totalLength = str1.length() - ng + 1 + diffGrams.size();
    double threshold = 2.486 + 0.025 * totalLength;
    difference = Math.sqrt(difference);
    sum = Math.sqrt(sum);

    result.append(").\n");
    result.append("The difference of both vectors is: " + difference + " (sqrt(" +computeStringDiff + "))" + " \n");
    if (difference < threshold) {
       result.append("Both strings are similar, because the difference value is smaller than threshold (2.486 + 0.025 * " + totalLength + " = " + threshold + ") \n");
    } else {
       result.append("Both strings are not similar, because the difference value is bigger than threshold (2.486 + 0.025 * " + totalLength + " = " + threshold + ") \n");
    }
    result.append("\n");
    result.append("Similarity (normalized to values between 0 and 1; D is diference and T is threshold; threshold is fixed to 0.8):\n");
    result.append("Similarity = { 4/5 + ((T-D) / (5 * T)) if D < T; \n");
    result.append("               0.8 if D = T;\n");
    result.append("               4/5 - (((D - T) * 4) / ((1 + D - T) * 5) if  D > T;\n");
    double similarity = 0;
    if (difference < threshold)
      {similarity = 0.8 + ((threshold - difference) / (5 * threshold));};
    if (difference == threshold)
      {similarity = 0.8;};
    if (difference > threshold)
      {similarity = 0.8 - (((difference - threshold) * 4) / ((1 + difference - threshold) * 5));};   
    result.append("--> Similarity = " + similarity);
    return result.toString();
  }
}
