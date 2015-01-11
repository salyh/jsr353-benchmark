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
package de.saly.json.jsr353.benchmark.jmh.streamingapi;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.File;
import java.nio.charset.StandardCharsets;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import de.saly.json.jsr353.benchmark.BenchmarkEnabledParser;
import de.saly.json.jsr353.benchmark.data.Buffers;
import de.saly.json.jsr353.benchmark.jsr353.GensonParser;

@State(Scope.Benchmark)
public class BenchmarkStreamParser {
    
    private BenchmarkEnabledParser parser;

    public BenchmarkStreamParser(){
        super();
        
        try {
            parser = (BenchmarkEnabledParser) Class.forName(System.getProperty("benchmark.impl")).newInstance();
            parser.init(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // -- parse bytes
    @Benchmark
    public void parseOnly1kBytes(final Blackhole bh) throws Exception {
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_1K), bh);
    }

    @Benchmark
    public void parseOnly10kBytes(final Blackhole bh) throws Exception {
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_10K), bh);
    }

    @Benchmark
    public void parseOnly100kBytes(final Blackhole bh) throws Exception {
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_100K), bh);
    }

    @Benchmark
    public void parseOnly1000kBytes(final Blackhole bh) throws Exception {
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_1000K), bh);
    }

    @Benchmark
    public void parseOnly1000kBytesUTF16(final Blackhole bh) throws Exception {
        
        if(parser instanceof GensonParser) throw new Exception("not implemented");
        
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_UTF16_1000K), bh);
    }

    @Benchmark
    public void parseOnly10000kBytes(final Blackhole bh) throws Exception {
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_10000K), bh);
    }

    @Benchmark
    public void parseOnly10000kBytesUTF16(final Blackhole bh) throws Exception {
        
        if(parser instanceof GensonParser) throw new Exception("not implemented");
        
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_UTF16_10000K), bh);
    }
    
    @Benchmark
    public void parseOnly50000kBytes(final Blackhole bh) throws Exception {
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_10000K), bh);
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_10000K), bh);
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_10000K), bh);
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_10000K), bh);
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_10000K), bh);
    }

    @Benchmark
    public void parseOnly50000kBytesUTF16(final Blackhole bh) throws Exception {
        
        if(parser instanceof GensonParser) throw new Exception("not implemented");
        
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_UTF16_10000K), bh);
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_UTF16_10000K), bh);
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_UTF16_10000K), bh);
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_UTF16_10000K), bh);
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_UTF16_10000K), bh);
    }

    // -- parse chars

    @Benchmark
    public void parseOnly1kChars(final Blackhole bh) throws Exception {
        parser.parseOnly(new CharArrayReader(Buffers.C_1K), bh);
    }

    @Benchmark
    public void parseOnly10kChars(final Blackhole bh) throws Exception {
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
    }

    @Benchmark
    public void parseOnly100kChars(final Blackhole bh) throws Exception {
        parser.parseOnly(new CharArrayReader(Buffers.C_100K), bh);
    }

    @Benchmark
    public void parseOnly1000kChars(final Blackhole bh) throws Exception {
        parser.parseOnly(new CharArrayReader(Buffers.C_1000K), bh);
    }

    @Benchmark
    public void parseOnly1000kCharsUTF16(final Blackhole bh) throws Exception {
        parser.parseOnly(new CharArrayReader(Buffers.C_UTF16_1000K), bh);
    }

    @Benchmark
    public void parseOnly10000kChars(final Blackhole bh) throws Exception {
        parser.parseOnly(new CharArrayReader(Buffers.C_10000K), bh);
    }

    @Benchmark
    public void parseOnly10000kCharsUTF16(final Blackhole bh) throws Exception {
        parser.parseOnly(new CharArrayReader(Buffers.C_UTF16_10000K), bh);
    }

    @Benchmark
    public void parseOnlyCombinedChars200(final Blackhole bh) throws Exception {
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10K), bh);

    }

    @Benchmark
    public void parseOnlyCombinedChars500(final Blackhole bh) throws Exception {
        parser.parseOnly(new CharArrayReader(Buffers.C_100K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_100K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_100K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_100K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_100K), bh);

    }

    @Benchmark
    public void parseOnlyCombinedChars5000(final Blackhole bh) throws Exception {
        parser.parseOnly(new CharArrayReader(Buffers.C_1000K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_1000K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_1000K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_1000K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_1000K), bh);

    }
    
    @Benchmark
    public void parseOnlyCombinedChars50000(final Blackhole bh) throws Exception {
        parser.parseOnly(new CharArrayReader(Buffers.C_10000K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10000K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10000K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10000K), bh);
        parser.parseOnly(new CharArrayReader(Buffers.C_10000K), bh);

    }

    @Benchmark
    public void parseOnlyCombinedBytes500(final Blackhole bh) throws Exception {
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_100K), bh);
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_100K), bh);
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_100K), bh);
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_100K), bh);
        parser.parseOnly(new ByteArrayInputStream(Buffers.B_100K), bh);

    }
    
    @Benchmark
    public void parseOnlyCombinedBytes500FileInput(final Blackhole bh) throws Exception {
        int count = 100;
        parser.parseOnly(((new File("./generated/generated_benchmark_test_file_" + StandardCharsets.UTF_8.name() + "_" + count
                    + ".json"))), bh);
        parser.parseOnly(((new File("./generated/generated_benchmark_test_file_" + StandardCharsets.UTF_8.name() + "_" + count
                + ".json"))), bh);
        parser.parseOnly(((new File("./generated/generated_benchmark_test_file_" + StandardCharsets.UTF_8.name() + "_" + count
                + ".json"))), bh);
        parser.parseOnly(((new File("./generated/generated_benchmark_test_file_" + StandardCharsets.UTF_8.name() + "_" + count
                + ".json"))), bh);
        parser.parseOnly(((new File("./generated/generated_benchmark_test_file_" + StandardCharsets.UTF_8.name() + "_" + count
                + ".json"))), bh);

    }
}
