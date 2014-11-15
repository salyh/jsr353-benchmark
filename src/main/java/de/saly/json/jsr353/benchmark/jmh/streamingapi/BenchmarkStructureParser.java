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

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonParserFactory;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import de.saly.json.jsr353.benchmark.data.Buffers;

@State(Scope.Benchmark)
public class BenchmarkStructureParser {

    protected Charset utf8Charset = Charset.forName("UTF8");

    protected Map<String, ?> getConfig() {
        return Collections.EMPTY_MAP;
    }

    public BenchmarkStructureParser() {
        super();
        if (!Charset.defaultCharset().equals(Charset.forName("UTF-8"))) {
            throw new RuntimeException("Default charset is " + Charset.defaultCharset() + ", must must be UTF-8");
        }
    }

    protected final JsonParserFactory parserFactory = Json.createParserFactory(getConfig());

    protected Object parse(final JsonObject jo, final Blackhole bh) throws Exception {
        final JsonParser parser = parserFactory.createParser(jo);

        while (parser.hasNext()) {
            final Event e = parser.next();
            bh.consume(e);
        }
        parser.close();
        return parser;
    }

    // -- parse in memory

    @Benchmark
    public void parseOnly1kIM(final Blackhole bh) throws Exception {
        bh.consume(parse(Buffers.O_1K, bh));
    }

    @Benchmark
    public void parseOnly10kIM(final Blackhole bh) throws Exception {
        bh.consume(parse(Buffers.O_10K, bh));
    }

    @Benchmark
    public void parseOnly100kIM(final Blackhole bh) throws Exception {
        bh.consume(parse(Buffers.O_100K, bh));
    }

    @Benchmark
    public void parseOnly1000kIM(final Blackhole bh) throws Exception {
        bh.consume(parse(Buffers.O_1000K, bh));
    }

    @Benchmark
    public void parseOnly10000kIM(final Blackhole bh) throws Exception {
        bh.consume(parse(Buffers.O_10000K, bh));
    }

    @Benchmark
    public void parseOnlyCombinedIM200(final Blackhole bh) throws Exception {
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));
        bh.consume(parse(Buffers.O_10K, bh));

    }

    @Benchmark
    public void parseOnlyCombinedIM500(final Blackhole bh) throws Exception {
        bh.consume(parse(Buffers.O_100K, bh));
        bh.consume(parse(Buffers.O_100K, bh));
        bh.consume(parse(Buffers.O_100K, bh));
        bh.consume(parse(Buffers.O_100K, bh));
        bh.consume(parse(Buffers.O_100K, bh));

    }

    @Benchmark
    public void parseOnlyCombinedIM5000(final Blackhole bh) throws Exception {
        bh.consume(parse(Buffers.O_1000K, bh));
        bh.consume(parse(Buffers.O_1000K, bh));
        bh.consume(parse(Buffers.O_1000K, bh));
        bh.consume(parse(Buffers.O_1000K, bh));
        bh.consume(parse(Buffers.O_1000K, bh));

    }
    
    @Benchmark
    public void parseOnlyCombinedIM50000(final Blackhole bh) throws Exception {
        bh.consume(parse(Buffers.O_10000K, bh));
        bh.consume(parse(Buffers.O_10000K, bh));
        bh.consume(parse(Buffers.O_10000K, bh));
        bh.consume(parse(Buffers.O_10000K, bh));
        bh.consume(parse(Buffers.O_10000K, bh));
        bh.consume(parse(Buffers.O_10000K, bh));

    }

}
