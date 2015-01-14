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
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import de.saly.json.jsr353.benchmark.BenchmarkEnabledParser;
import de.saly.json.jsr353.benchmark.data.Buffers;
import de.saly.json.jsr353.benchmark.data.model.Collection;
import de.saly.json.jsr353.benchmark.data.model.Simple;

@State(Scope.Benchmark)
public class BenchmarkSerialize {
    
    private BenchmarkEnabledParser parser;
    
    
    public BenchmarkSerialize(){
        super();
        
        try {
            parser = (BenchmarkEnabledParser) Class.forName(System.getProperty("benchmark.impl")).newInstance();
            parser.init(null);
            
            
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    public void deserializeSimpleBytes(final Blackhole bh) throws Exception {
        parser.deserialize(new ByteArrayInputStream(Buffers.B_SIMPLE), Simple.class, bh);
    }
    
    @Benchmark
    public void deserializeSimpleChars(final Blackhole bh) throws Exception {
        parser.deserialize(new CharArrayReader(Buffers.C_SIMPLE), Simple.class, bh);
    }
    
    @Benchmark
    public void deserializeSimpleFile(final Blackhole bh) throws Exception {
        parser.deserialize(new File("./generated/generated_benchmark_test_file_" + StandardCharsets.UTF_8.name() + "_mapping_simple.json"), Simple.class, bh);
    }
    
    @Benchmark
    public void serializeSimpleBytes(final Blackhole bh) throws Exception {
        parser.serialize(new ByteArrayOutputStream(), Buffers.SIMPLE, bh);
    }
    
    @Benchmark
    public void serializeSimpleChars(final Blackhole bh) throws Exception {
        parser.serialize(new CharArrayWriter(), Buffers.SIMPLE, bh);
    }
    
    //@Benchmark
    public void serializeSimpleFile(final Blackhole bh) throws Exception {
        File tmp = File.createTempFile("serialize", "bench");
        parser.serialize(tmp, Buffers.SIMPLE, bh);
        tmp.delete();
    }
    
    
    
    
    
    
    @Benchmark
    public void deserializeCollectionBytes(final Blackhole bh) throws Exception {
        parser.deserialize(new ByteArrayInputStream(Buffers.B_COLLECTION), Collection.class, bh);
    }
    
    @Benchmark
    public void deserializeCollectionChars(final Blackhole bh) throws Exception {
        parser.deserialize(new CharArrayReader(Buffers.C_COLLECTION), Collection.class, bh);
    }
    
    @Benchmark
    public void deserializeCollectionFile(final Blackhole bh) throws Exception {
        parser.deserialize(new File("./generated/generated_benchmark_test_file_" + StandardCharsets.UTF_8.name() + "_mapping_collection.json"), Collection.class, bh);
    }
    
    @Benchmark
    public void serializeCollectionBytes(final Blackhole bh) throws Exception {
        parser.serialize(new ByteArrayOutputStream(), Buffers.COLLECTION, bh);
    }
    
    @Benchmark
    public void serializeCollectionChars(final Blackhole bh) throws Exception {
        parser.serialize(new CharArrayWriter(), Buffers.COLLECTION, bh);
    }
    
    //@Benchmark
    public void serializeCollectionFile(final Blackhole bh) throws Exception {
        File tmp = File.createTempFile("serialize", "bench");
        parser.serialize(tmp, Buffers.COLLECTION,  bh);
        tmp.delete();
    }

}
