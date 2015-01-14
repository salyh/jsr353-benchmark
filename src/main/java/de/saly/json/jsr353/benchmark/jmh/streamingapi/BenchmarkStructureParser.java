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

import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import de.saly.json.jsr353.benchmark.data.Buffers;
import de.saly.json.jsr353.benchmark.jsr353.Jsr353Parser;

@State(Scope.Benchmark)
public class BenchmarkStructureParser {

    private Jsr353Parser parser;

    public BenchmarkStructureParser(){
        super();
        
        try {
            parser = (Jsr353Parser) Class.forName(System.getProperty("benchmark.impl")).newInstance();
            parser.init(null);
        } catch (ClassCastException e) {
            //ignore
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected Object parse(final JsonObject jo, final Blackhole bh) throws Exception {
        final JsonParser jparser = parser.getParserFactory().createParser(jo);

        while (jparser.hasNext()) {
            final Event e = jparser.next();
            bh.consume(e);
        }
        jparser.close();
        return jparser;
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
