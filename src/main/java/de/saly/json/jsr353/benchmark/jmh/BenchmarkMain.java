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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

import de.saly.json.jsr353.benchmark.data.Buffers;
import de.saly.json.jsr353.benchmark.data.CreateJsonTestFiles;
import de.saly.json.jsr353.benchmark.visual.ResultViewer;

public class BenchmarkMain {

    private static final String TSS = new SimpleDateFormat("yyyyMMdd-HHmm").format(new Date());
    private static final Map<String, String> parsers = new LinkedHashMap<String, String>();

    static {
        parsers.put("Jackson (pgelinas)", "de.saly.json.jsr353.benchmark.jsr353.JacksonPgelinasJSR353");
        parsers.put("Jackson", "de.saly.json.jsr353.benchmark.jackson.JacksonParser");
        parsers.put("GSON", "de.saly.json.jsr353.benchmark.gson.GsonParser");
        parsers.put("Apache Johnzon", "de.saly.json.jsr353.benchmark.jsr353.JohnzonParser");
        //parsers.put("Boon", "de.saly.json.jsr353.benchmark.boon.BoonParser");
        parsers.put("Glassfish JSR 353 RI (JSONP)", "de.saly.json.jsr353.benchmark.jsr353.JsonpRiParser");
        parsers.put("Genson", "de.saly.json.jsr353.benchmark.jsr353.GensonParser");
    }
    
    private BenchmarkMain() {

    }

    private static final String REGEX = ".*";
    private static final String MEMORY = "2048m";

    public static void main(final String[] args) throws Exception {
        
        CreateJsonTestFiles.create(null);
        // initialize Buffers
        Buffers.init();
        
        File[] files = new File(".").listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {

                return name.startsWith("jmh_benchmark_");
            }
        });
        
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            file.delete();
        }
        
        PrintStream orig = System.out;
        orig.println("Running...");
        PrintStream pf = outputFile(new File("jmh_benchmark_sysout_log_t"+TSS+".txt"));
        System.setOut(pf);

        System.out.println("Start benchmark on " + new Date());
        //System.out.println("Currently loaded implementation:  " + IMPL);
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("GC Info:");

        final List<GarbageCollectorMXBean> gcMxBeans = ManagementFactory.getGarbageCollectorMXBeans();

        for (final GarbageCollectorMXBean gcMxBean : gcMxBeans) {
            System.out.println(gcMxBean.getName());
            System.out.println(gcMxBean.getObjectName());
            System.out.println();
        }

        System.out.println();
        System.out.println();

        System.out.println("Env:");

        final Map<String, String> env = System.getenv();

        for (final Iterator<Entry<String, String>> iterator = env.entrySet().iterator(); iterator.hasNext();) {
            final Entry<String, String> e = iterator.next();
            System.out.println(e.getKey() + "=" + e.getValue());

        }

        System.out.println();
        System.out.println();
        System.out.println("Props:");

        final Properties prop = System.getProperties();

        for (final Iterator<Entry<Object, Object>> iterator = prop.entrySet().iterator(); iterator.hasNext();) {
            final Entry<Object, Object> e = iterator.next();
            System.out.println(e.getKey() + "=" + e.getValue());

        }

        System.out.println();
        System.out.println();
        pf.flush();
        
        final long start = System.currentTimeMillis();

        for (Iterator<Entry<String, String>> it = parsers.entrySet().iterator(); it.hasNext();) {
            Entry<String, String> entry= it.next();
            
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("Currently benchmarking: "+entry.getKey());
            System.out.println("------------------------------------------------------------------------------------");
            
            orig.println("------------------------------------------------------------------------------------");
            orig.println("Currently benchmarking: "+entry.getKey());
            orig.println("------------------------------------------------------------------------------------");
            
            
            try {
                runJMH(entry.getValue(), 1, 1, 3, 3);
                // runJMH(1, 2, 3, 5); //1 fork, 2 threads, 3 warmup, 5 measurement
                // runJMH(1, 4, 2, 5);
                // runJMH(2,8,5,10);
                // runJMH(2,16,3,5);
            } catch (final Exception e) {
                System.out.println(e);
                orig.println(e);
                e.printStackTrace(System.out);
                e.printStackTrace(orig);
            }
            
        }
        
        
        orig.flush();
        System.setOut(pf);
        
        /*
        if (System.getProperty("jsr353.skip") == null) {

            try {
                final File file = new File("./generated/generated_benchmark_test_file_UTF-8_10000000.json");
                ParseHugeJsonFile.run(file);
            } catch (final Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }

            System.out.println();
            System.out.println();

            try {
                final File file = new File("./generated/generated_benchmark_test_file_UTF-8_10000000.json");
                JsonInMemoryParsing.main(null);
            } catch (final Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }

        }

        try {
            final File file = new File("./generated/generated_benchmark_test_file_bigstack_UTF-8_100000000.json");
            ParseHugeJsonFile.run(file);
        } catch (final Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }*/

        final long end = System.currentTimeMillis();
        String endMsg = "End. Benchmark Runtime was approx " + ((end - start) / (60 * 1000)) + " min.";
        orig.println(endMsg);
        System.out.println(endMsg);
        pf.flush();
        orig.flush();
        
        ResultViewer.main(null);
    }

    private static void runJMH(final String parserClasss, final int forks, final int threads, final int warmupit, final int measureit) throws Exception {

        
        
        final Options opt = new OptionsBuilder()

        .include(REGEX)
        .forks(forks)
        .warmupIterations(warmupit)
        .measurementIterations(measureit)
        .threads(threads)
        .mode(Mode.Throughput)
        .timeUnit(TimeUnit.SECONDS)
        .verbosity(VerboseMode.EXTRA)
        .jvmArgs("-Xmx" + MEMORY, "-Dfile.encoding=utf-8", "-Dbenchmark.impl="+parserClasss)
        .resultFormat(ResultFormatType.JSON)
        .result(String.format("jmh_benchmark_thrpt_result_%s_f%d_t%d_w%d_i%d_t%s.txt", parserClasss, forks, threads, warmupit, measureit,
                TSS))
                .output(String.format("jmh_benchmark_thrpt_log_%s_f%d_t%d_w%d_i%d_t%s.txt", parserClasss, forks, threads, warmupit, measureit, TSS))
                //.addProfiler(GCProfiler.class)    // report GC time
                //.addProfiler(StackProfiler.class) // report method stack execution profile
                .build();

        new Runner(opt).run();

    }
    
    private static PrintStream outputFile(File name) throws FileNotFoundException {
        return new PrintStream(new BufferedOutputStream(new FileOutputStream(name)));
    }

}
