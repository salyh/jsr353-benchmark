/*
Copyright (C) 2014 Hendrik Saly

This file is part of JSR 353 Benchmark Suite.

JSR 353 Benchmark Suite is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License Version 2 as published by
the Free Software Foundation.

JSR 353 Benchmark Suite is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with JSR 353 Benchmark Suite. If not, see <http://www.gnu.org/licenses/>.
*/
package de.saly.json.jsr353.benchmark.jmh;

import java.io.File;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.json.Json;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

import de.saly.json.jsr353.benchmark.data.Buffers;
import de.saly.json.jsr353.benchmark.data.CreateJsonTestFiles;
import de.saly.json.jsr353.benchmark.nonjmh.JsonInMemoryParsing;
import de.saly.json.jsr353.benchmark.nonjmh.ParseHugeJsonFile;
import de.saly.json.jsr353.benchmark.util.ClassloaderUtils;

public class BenchmarkMain {

    private static final String IMPL;
    private static final String IMPL_FOLDER_NAME;
    private static final File[] JARS;
    static {

        try {
            IMPL_FOLDER_NAME = System.getProperty("de.saly.json.jsr353.benchmark.jmh.impl");
            
            if(IMPL_FOLDER_NAME == null || IMPL_FOLDER_NAME.length()==0) {
                throw new Exception("-Dde.saly.json.jsr353.benchmark.jmh.impl=xxx");
            }
            JARS = ClassloaderUtils.loadJarsFromDir(new File("extlib/"+IMPL_FOLDER_NAME));
            IMPL = Json.createArrayBuilder().getClass().getPackage().getName();
            CreateJsonTestFiles.create(null);
            // initialize Buffers
            Buffers.init();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

    }

    private BenchmarkMain() {

    }

    private static final String REGEX = ".*";
    private static final String MEMORY = "2048m";

    public static void main(final String[] args) {
        
        

        System.out.println("Start benchmark on " + new Date());
        System.out.println("Currently loaded implementation:  " + IMPL+ " ("+IMPL_FOLDER_NAME+")");
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("GC Info:");
       
        List<GarbageCollectorMXBean> gcMxBeans = ManagementFactory.getGarbageCollectorMXBeans();

        for (GarbageCollectorMXBean gcMxBean : gcMxBeans) {
                System.out.println(gcMxBean.getName());
                System.out.println(gcMxBean.getObjectName());
                System.out.println();
        }
        
        System.out.println();
        System.out.println();
        
        System.out.println("Env:");
        
        Map<String, String> env = System.getenv();
        
        for (Iterator<Entry<String, String>> iterator = env.entrySet().iterator(); iterator.hasNext();) {
            Entry<String, String> e = iterator.next();
            System.out.println(e.getKey()+"="+e.getValue());
            
        }
        
        System.out.println();
        System.out.println();
        System.out.println("Props");
        
        Properties prop = System.getProperties();
        
        for (Iterator<Entry<Object, Object>> iterator = prop.entrySet().iterator(); iterator.hasNext();) {
            Entry<Object, Object> e = iterator.next();
            System.out.println(e.getKey()+"="+e.getValue());
            
        }
        
        System.out.println();
        System.out.println();
        final long start = System.currentTimeMillis();

        try {
            runJMH(1, 1, 3, 3);
            // runJMH(1, 2, 3, 5); //1 fork, 2 threads, 3 warmup, 5 measurement
            // runJMH(1, 4, 2, 5);
            // runJMH(2,8,5,10);
            // runJMH(2,16,3,5);
        } catch (final Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        
        try {
            final File file = new File("./generated/generated_benchmark_test_file_UTF-8_10000000.json");
            ParseHugeJsonFile.run(file);
        } catch (final Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        
        try {
            final File file = new File("./generated/generated_benchmark_test_file_UTF-8_10000000.json");
            JsonInMemoryParsing.main(null);
        } catch (final Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        //if (!Boolean.getBoolean("skipNonJmh")) {

            /*try {
                final File file = new File("./generated/generated_benchmark_test_file_UTF-8_10000000.json");
                ParseHugeJsonFile.run(file);
            } catch (final Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }*/

            /*try {
                final File file = new File("./generated/generated_benchmark_test_file_bigstack_UTF-8_100000000.json");
                ParseHugeJsonFile.run(file);
            } catch (final Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }*/
        
        /*
         try {
        final File file = new File("./generated/generated_benchmark_test_file_UTF-8_10000000.json");
        JsonInMemoryParsing.run(file);
    } catch (final Exception e) {
        System.out.println(e);
        e.printStackTrace();
    }*/

        //}

        final long end = System.currentTimeMillis();

        System.out.println("End. Benchmark Runtime was approx " + ((end - start) / (60 * 1000)) + " min.");
    }

    private static void runJMH(final int forks, final int threads, final int warmupit, final int measureit) throws Exception {

       
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< JARS.length; i++) {
            
            sb.append(JARS[i].getAbsolutePath()+File.pathSeparator);
            
        }
        
        sb.deleteCharAt(sb.length()-1);
        
        System.setProperty("java.class.path", System.getProperty("java.class.path")+File.pathSeparator+sb.toString());
        
        final String tss = new SimpleDateFormat("yyyyMMdd-HHmm").format(new Date());
        final Options opt = new OptionsBuilder()
             
                .include(REGEX)
                .forks(forks)
                .warmupIterations(warmupit)
                .measurementIterations(measureit)
                .threads(threads)
                .mode(Mode.Throughput)
                .timeUnit(TimeUnit.SECONDS)
                .verbosity(VerboseMode.EXTRA)
                .jvmArgs("-Xmx" + MEMORY, "-Dfile.encoding=utf-8")
                .resultFormat(ResultFormatType.TEXT)
                .result(String.format("jmh_benchmark_thrpt_result_%s_f%d_t%d_w%d_i%d_t%s.txt", IMPL, forks, threads, warmupit, measureit,
                        tss))
                .output(String.format("jmh_benchmark_thrpt_log_%s_f%d_t%d_w%d_i%d_t%s.txt", IMPL, forks, threads, warmupit, measureit, tss))
                //.addProfiler(GCProfiler.class)    // report GC time
                //.addProfiler(StackProfiler.class) // report method stack execution profile
                .build();

        new Runner(opt).run();

    }

}
