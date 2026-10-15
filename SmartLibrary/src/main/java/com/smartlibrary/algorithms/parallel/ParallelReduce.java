package com.smartlibrary.algorithms.parallel;import java.util.*;
public final class ParallelReduce {private ParallelReduce(){}public static long sequentialSum(long[] a){long s=0;for(long x:a)s+=x;return s;}public static long parallelSum(long[] a){return Arrays.stream(a).parallel().sum();}}
