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
package de.saly.json.jsr353.benchmark.jmh.objectmodelapi;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import de.saly.json.jsr353.benchmark.BenchmarkEnabledParser;
import de.saly.json.jsr353.benchmark.data.Buffers;

@State(Scope.Benchmark)
public class BenchmarkJsonReader {


    private BenchmarkEnabledParser parser;

    public BenchmarkJsonReader(){
        super();
        
        try {
            parser = (BenchmarkEnabledParser) Class.forName(System.getProperty("benchmark.impl")).newInstance();
            parser.init(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // -- read bytes to structure

    @Benchmark
    public void read1kBytes(final Blackhole bh) throws Exception {
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_1K), bh);
    }

    @Benchmark
    public void read10kBytes(final Blackhole bh) throws Exception {
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_10K), bh);
    }

    @Benchmark
    public void read100kBytes(final Blackhole bh) throws Exception {
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_100K), bh);
    }

    @Benchmark
    public void read1000kBytesUTF16(final Blackhole bh) throws Exception {
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_UTF16_1000K), bh);
    }

    @Benchmark
    public void read1000kBytes(final Blackhole bh) throws Exception {
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_1000K), bh);
    }

    @Benchmark
    public void read10000kBytesUTF16(final Blackhole bh) throws Exception {
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_UTF16_10000K), bh);
    }

    @Benchmark
    public void read10000kBytes(final Blackhole bh) throws Exception {
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_10000K), bh);
    }

    // -- read chars to structure

    @Benchmark
    public void read1kChars(final Blackhole bh) throws Exception {
        parser.readToStructure(new CharArrayReader(Buffers.C_1K), bh);
    }

    @Benchmark
    public void read10kChars(final Blackhole bh) throws Exception {
        parser.readToStructure(new CharArrayReader(Buffers.C_10K), bh);
    }

    @Benchmark
    public void read100kChars(final Blackhole bh) throws Exception {
        parser.readToStructure(new CharArrayReader(Buffers.C_100K), bh);
    }

    @Benchmark
    public void read1000kChars(final Blackhole bh) throws Exception {
        parser.readToStructure(new CharArrayReader(Buffers.C_1000K), bh);
    }

    @Benchmark
    public void read1000kCharsUTF16(final Blackhole bh) throws Exception {
        parser.readToStructure(new CharArrayReader(Buffers.C_UTF16_1000K), bh);
    }

    @Benchmark
    public void read10000kChars(final Blackhole bh) throws Exception {
        parser.readToStructure(new CharArrayReader(Buffers.C_10000K), bh);
    }

    @Benchmark
    public void read10000kCharsUTF16(final Blackhole bh) throws Exception {
        parser.readToStructure(new CharArrayReader(Buffers.C_UTF16_10000K), bh);
    }

    @Benchmark
    public void readCombinedChars500(final Blackhole bh) throws Exception {
        parser.readToStructure(new CharArrayReader(Buffers.C_100K), bh);
        parser.readToStructure(new CharArrayReader(Buffers.C_100K), bh);
        parser.readToStructure(new CharArrayReader(Buffers.C_100K), bh);
        parser.readToStructure(new CharArrayReader(Buffers.C_100K), bh);
        parser.readToStructure(new CharArrayReader(Buffers.C_100K), bh);

    }

    @Benchmark
    public void readCombinedChars5000(final Blackhole bh) throws Exception {
        parser.readToStructure(new CharArrayReader(Buffers.C_1000K), bh);
        parser.readToStructure(new CharArrayReader(Buffers.C_1000K), bh);
        parser.readToStructure(new CharArrayReader(Buffers.C_1000K), bh);
        parser.readToStructure(new CharArrayReader(Buffers.C_1000K), bh);
        parser.readToStructure(new CharArrayReader(Buffers.C_1000K), bh);

    }
    
    @Benchmark
    public void readCombinedChars50000(final Blackhole bh) throws Exception {
        parser.readToStructure(new CharArrayReader(Buffers.C_10000K), bh);
        parser.readToStructure(new CharArrayReader(Buffers.C_10000K), bh);
        parser.readToStructure(new CharArrayReader(Buffers.C_10000K), bh);
        parser.readToStructure(new CharArrayReader(Buffers.C_10000K), bh);
        parser.readToStructure(new CharArrayReader(Buffers.C_10000K), bh);

    }

    @Benchmark
    public void readCombinedBytes500(final Blackhole bh) throws Exception {
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_100K), bh);
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_100K), bh);
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_100K), bh);
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_100K), bh);
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_100K), bh);

    }
    
    @Benchmark
    public void readCombinedBytes5000(final Blackhole bh) throws Exception {
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_1000K), bh);
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_1000K), bh);
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_1000K), bh);
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_1000K), bh);
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_1000K), bh);

    }
    
    @Benchmark
    public void readCombinedBytes50000(final Blackhole bh) throws Exception {
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_10000K), bh);
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_10000K), bh);
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_10000K), bh);
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_10000K), bh);
        parser.readToStructure(new ByteArrayInputStream(Buffers.B_10000K), bh);

    }

}
