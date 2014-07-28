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

import java.util.Date;
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
import de.saly.json.jsr353.benchmark.nonjmh.ParseHugeJsonFile;

public class BenchmarkMain {

    static {

        try {
            CreateJsonTestFiles.create(null);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        //initialize Buffers
        Buffers.init();
    }

    private BenchmarkMain() {

    }

    private static final String REGEX = ".*";
    private static final String MEMORY = "2048m";

    public static void main(final String[] args){
        
        System.out.println("Start benchmark on "+new Date());
        System.out.println("Currently loaded implementation:  "+Json.createArrayBuilder().getClass().toString());
        System.out.println(System.getenv());
        System.out.println(System.getProperties());
        final long start = System.currentTimeMillis();
        
        try {
            ParseHugeJsonFile.run();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        
        
        try {
            run(1, 1, 3, 3);
            //run(1, 2, 3, 5); //1 fork, 2 threads, 3 warmup, 5 measurement 
            //run(1, 4, 2, 5);
            //run(2,8,5,10);
            //run(2,16,3,5);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        
        final long end = System.currentTimeMillis();

        System.out.println("End. Benchmark Runtime was approx " + ((end - start) / (60 * 1000)) + " min.");
    }

    public static void run(final int forks, final int threads, final int warmupit, final int measureit) throws Exception {

        long ts = System.currentTimeMillis();
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
                .result(String.format("jmh_benchmark_thrpt_result_f%d_t%d_w%d_i%d_t%d.txt", forks, threads, warmupit, measureit,ts
                        ))
                .output(String.format("jmh_benchmark_thrpt_log_f%d_t%d_w%d_i%d_t%d.txt", forks, threads, warmupit, measureit,ts))

                .build();

        new Runner(opt).run();
        
        ParseHugeJsonFile.main(null);
        
        
        

    }

}
