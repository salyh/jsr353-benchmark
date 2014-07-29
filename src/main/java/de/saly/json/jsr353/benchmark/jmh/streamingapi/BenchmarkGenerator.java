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

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class BenchmarkGenerator {

    protected Map<String, ?> getConfig() {
        return Collections.EMPTY_MAP;
    }

    public BenchmarkGenerator() {
        super();
        if (!Charset.defaultCharset().equals(Charset.forName("UTF-8"))) {
            throw new RuntimeException("Default charset is " + Charset.defaultCharset() + ", must must be UTF-8");
        }
    }

    protected final JsonGeneratorFactory generatorFactory = Json.createGeneratorFactory(getConfig());

    protected Object generate(final Writer writer, final int count, final Blackhole bh) throws Exception {
        final JsonGenerator generator = generatorFactory.createGenerator(writer);
        final Random rand = new Random(System.currentTimeMillis());

        generator.writeStartObject();

        for (int i = 0; i < count; i++) {

            generator.write("bd" + i, new BigDecimal(rand.nextInt()));
            generator.write("bi" + i, new BigInteger(String.valueOf(rand.nextInt())));
            generator.write("int" + i, rand.nextInt());
            generator.write("bool" + i, true);
            generator.write("double" + i, 13412.4432);
            generator.write("long" + i, 761879998712657L);
            generator.write("string" + i, "This is just a normal string, nothing special");
            generator.write("escstring" + i, "This is not a normal\t\tstring,\nnsomething special ??????????????");
            generator
                    .write("unicodetring" + i,
                            "This is not a normal\u0001\u0001\u0001string,\nnsomething special \uFFFF\uFFFF\udbff\udfff\udbff\udfff ??????????????");
            generator.writeStartObject("nested");
            generator.write("bd" + i, new BigDecimal(rand.nextInt()));
            generator.write("bi" + i, new BigInteger(String.valueOf(rand.nextInt())));
            generator.write("int" + i, rand.nextInt());
            generator.write("bool" + i, true);
            generator.write("double" + i, 13412.4432);
            generator.write("long" + i, 761879998712657L);
            generator.write("string" + i, "This is just a normal string, nothing special");
            generator.write("escstring" + i, "This is not a normal\t\tstring,\nnsomething special ??????????????");
            generator
                    .write("unicodetring" + i,
                            "This is not a normal\u0001\u0001\u0001string,\nnsomething special \uFFFF\uFFFF\udbff\udfff\udbff\udfff ??????????????");
            generator.writeEnd();
            generator.writeStartArray("nested array");
            generator.write(new BigDecimal(rand.nextInt()));
            generator.write(new BigInteger(String.valueOf(rand.nextInt())));
            generator.write(rand.nextInt());
            generator.write(true);
            generator.write(13412.4432);
            generator.write(761879998712657L);
            generator.write("This is just a normal string, nothing special");
            generator.write("This is not a normal\t\tstring,\nnsomething special ??????????????");
            generator
                    .write("This is not a normal\u0001\u0001\u0001string,\nnsomething special \uFFFF\uFFFF\udbff\udfff\udbff\udfff ??????????????");
            generator.writeEnd();

        }

        generator.writeEnd();

        generator.close();
        return generator;
    }

    @Benchmark
    public void gen1InMem(final Blackhole bh) throws Exception {
        final StringWriter sw = new StringWriter();
        bh.consume(generate(sw, 1, bh));
        bh.consume(sw);
    }

    @Benchmark
    public void gen10InMem(final Blackhole bh) throws Exception {
        final StringWriter sw = new StringWriter();
        bh.consume(generate(sw, 10, bh));
        bh.consume(sw);
    }

    @Benchmark
    public void gen100InMem(final Blackhole bh) throws Exception {
        final StringWriter sw = new StringWriter();
        bh.consume(generate(sw, 100, bh));
        bh.consume(sw);
    }

    @Benchmark
    public void gen1000InMem(final Blackhole bh) throws Exception {
        final StringWriter sw = new StringWriter();
        bh.consume(generate(sw, 1000, bh));
        bh.consume(sw);
    }

    @Benchmark
    public void gen10000InMem(final Blackhole bh) throws Exception {
        final StringWriter sw = new StringWriter();
        bh.consume(generate(sw, 10000, bh));
        bh.consume(sw);
    }

    @Benchmark
    public void gen1InFile(final Blackhole bh) throws Exception {
        final File tmp = File.createTempFile("json", "bench");
        tmp.deleteOnExit();
        final FileWriter fw = new FileWriter(tmp);
        bh.consume(generate(fw, 1, bh));
    }

    @Benchmark
    public void gen10InFile(final Blackhole bh) throws Exception {
        final File tmp = File.createTempFile("json", "bench");
        tmp.deleteOnExit();
        final FileWriter fw = new FileWriter(tmp);
        bh.consume(generate(fw, 10, bh));
    }

    @Benchmark
    public void gen100InFile(final Blackhole bh) throws Exception {
        final File tmp = File.createTempFile("json", "bench");
        tmp.deleteOnExit();
        final FileWriter fw = new FileWriter(tmp);
        bh.consume(generate(fw, 100, bh));
    }

    @Benchmark
    public void gen1000InFile(final Blackhole bh) throws Exception {
        final File tmp = File.createTempFile("json", "bench");
        tmp.deleteOnExit();
        final FileWriter fw = new FileWriter(tmp);
        bh.consume(generate(fw, 1000, bh));
    }

    @Benchmark
    public void gen10000InFile(final Blackhole bh) throws Exception {
        final File tmp = File.createTempFile("json", "bench");
        tmp.deleteOnExit();
        final FileWriter fw = new FileWriter(tmp);
        bh.consume(generate(fw, 10000, bh));
    }

    @Benchmark
    public void gen100000InFile(final Blackhole bh) throws Exception {
        final File tmp = File.createTempFile("json", "bench");
        tmp.deleteOnExit();
        final FileWriter fw = new FileWriter(tmp);
        bh.consume(generate(fw, 100000, bh));

    }
}
