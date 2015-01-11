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
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonParserFactory;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import de.saly.json.jsr353.benchmark.data.Buffers;

@State(Scope.Benchmark)
public class BenchmarkStreamParser {

    protected Charset utf8Charset = Charset.forName("UTF8");

    protected Map<String, ?> getConfig() {
        return Collections.EMPTY_MAP;
    }

    public BenchmarkStreamParser() {
        super();
        if (!Charset.defaultCharset().equals(Charset.forName("UTF-8"))) {
            throw new RuntimeException("Default charset is " + Charset.defaultCharset() + ", must must be UTF-8");
        }
    }

    protected final JsonParserFactory parserFactory = Json.createParserFactory(getConfig());

    protected Object parse(final InputStream stream, final Blackhole bh) throws Exception {
        final JsonParser parser = parserFactory.createParser(stream);

        while (parser.hasNext()) {
            final Event e = parser.next();
            bh.consume(e);
        }
        parser.close();
        return parser;
    }

    protected Object parse(final Reader reader, final Blackhole bh) throws Exception {
        final JsonParser parser = parserFactory.createParser(reader);

        while (parser.hasNext()) {
            final Event e = parser.next();
            bh.consume(e);
        }
        parser.close();
        return parser;
    }

    // -- parse bytes

    @Benchmark
    public void parseOnly1kBytes(final Blackhole bh) throws Exception {
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_1K), bh));
    }

    @Benchmark
    public void parseOnly10kBytes(final Blackhole bh) throws Exception {
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_10K), bh));
    }

    @Benchmark
    public void parseOnly100kBytes(final Blackhole bh) throws Exception {
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_100K), bh));
    }

    @Benchmark
    public void parseOnly1000kBytes(final Blackhole bh) throws Exception {
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_1000K), bh));
    }

    @Benchmark
    public void parseOnly1000kBytesUTF16(final Blackhole bh) throws Exception {
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_UTF16_1000K), bh));
    }

    @Benchmark
    public void parseOnly10000kBytes(final Blackhole bh) throws Exception {
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_10000K), bh));
    }

    @Benchmark
    public void parseOnly10000kBytesUTF16(final Blackhole bh) throws Exception {
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_UTF16_10000K), bh));
    }
    
    @Benchmark
    public void parseOnly50000kBytes(final Blackhole bh) throws Exception {
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_10000K), bh));
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_10000K), bh));
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_10000K), bh));
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_10000K), bh));
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_10000K), bh));
    }

    @Benchmark
    public void parseOnly50000kBytesUTF16(final Blackhole bh) throws Exception {
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_UTF16_10000K), bh));
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_UTF16_10000K), bh));
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_UTF16_10000K), bh));
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_UTF16_10000K), bh));
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_UTF16_10000K), bh));
    }

    // -- parse chars

    @Benchmark
    public void parseOnly1kChars(final Blackhole bh) throws Exception {
        bh.consume(parse(new CharArrayReader(Buffers.C_1K), bh));
    }

    @Benchmark
    public void parseOnly10kChars(final Blackhole bh) throws Exception {
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
    }

    @Benchmark
    public void parseOnly100kChars(final Blackhole bh) throws Exception {
        bh.consume(parse(new CharArrayReader(Buffers.C_100K), bh));
    }

    @Benchmark
    public void parseOnly1000kChars(final Blackhole bh) throws Exception {
        bh.consume(parse(new CharArrayReader(Buffers.C_1000K), bh));
    }

    @Benchmark
    public void parseOnly1000kCharsUTF16(final Blackhole bh) throws Exception {
        bh.consume(parse(new CharArrayReader(Buffers.C_UTF16_1000K), bh));
    }

    @Benchmark
    public void parseOnly10000kChars(final Blackhole bh) throws Exception {
        bh.consume(parse(new CharArrayReader(Buffers.C_10000K), bh));
    }

    @Benchmark
    public void parseOnly10000kCharsUTF16(final Blackhole bh) throws Exception {
        bh.consume(parse(new CharArrayReader(Buffers.C_UTF16_10000K), bh));
    }

    @Benchmark
    public void parseOnlyCombinedChars200(final Blackhole bh) throws Exception {
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10K), bh));

    }

    @Benchmark
    public void parseOnlyCombinedChars500(final Blackhole bh) throws Exception {
        bh.consume(parse(new CharArrayReader(Buffers.C_100K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_100K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_100K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_100K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_100K), bh));

    }

    @Benchmark
    public void parseOnlyCombinedChars5000(final Blackhole bh) throws Exception {
        bh.consume(parse(new CharArrayReader(Buffers.C_1000K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_1000K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_1000K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_1000K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_1000K), bh));

    }
    
    @Benchmark
    public void parseOnlyCombinedChars50000(final Blackhole bh) throws Exception {
        bh.consume(parse(new CharArrayReader(Buffers.C_10000K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10000K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10000K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10000K), bh));
        bh.consume(parse(new CharArrayReader(Buffers.C_10000K), bh));

    }

    @Benchmark
    public void parseOnlyCombinedBytes500(final Blackhole bh) throws Exception {
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_100K), bh));
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_100K), bh));
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_100K), bh));
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_100K), bh));
        bh.consume(parse(new ByteArrayInputStream(Buffers.B_100K), bh));

    }
    
    @Benchmark
    public void parseOnlyCombinedBytes500FileInput(final Blackhole bh) throws Exception {
        int count = 100;
        bh.consume(parse(new FileInputStream((new File("./generated/generated_benchmark_test_file_" + StandardCharsets.UTF_8.name() + "_" + count
                    + ".json"))), bh));
        bh.consume(parse(new FileInputStream((new File("./generated/generated_benchmark_test_file_" + StandardCharsets.UTF_8.name() + "_" + count
                + ".json"))), bh));
        bh.consume(parse(new FileInputStream((new File("./generated/generated_benchmark_test_file_" + StandardCharsets.UTF_8.name() + "_" + count
                + ".json"))), bh));
        bh.consume(parse(new FileInputStream((new File("./generated/generated_benchmark_test_file_" + StandardCharsets.UTF_8.name() + "_" + count
                + ".json"))), bh));
        bh.consume(parse(new FileInputStream((new File("./generated/generated_benchmark_test_file_" + StandardCharsets.UTF_8.name() + "_" + count
                + ".json"))), bh));

    }
}
