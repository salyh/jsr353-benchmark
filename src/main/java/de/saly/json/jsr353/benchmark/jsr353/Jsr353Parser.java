package de.saly.json.jsr353.benchmark.jsr353;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;

import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonStructure;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonParserFactory;

import org.openjdk.jmh.infra.Blackhole;

import de.saly.json.jsr353.benchmark.BenchmarkEnabledParser;

public abstract class Jsr353Parser implements BenchmarkEnabledParser {

    protected JsonParserFactory parserFactory;
    protected JsonReaderFactory readerFactory;
    
    public Jsr353Parser() {
        
    }

    

    @Override
    public void parseOnly(File file, final Blackhole bh) throws Exception {
        bh.consume(parse(new FileInputStream(file), bh));

    }

    @Override
    public void parseOnly(Reader reader, final Blackhole bh) throws Exception {
        bh.consume(parse(reader, bh));

    }

    @Override
    public void parseOnly(InputStream in, final Blackhole bh) throws Exception {
        bh.consume(parse(in, bh));

    }

    @Override
    public void readToStructure(File file, Blackhole bh) throws Exception {
        bh.consume(read(new FileInputStream(file), bh));
        
    }


    @Override
    public void readToStructure(Reader reader, Blackhole bh) throws Exception {
        bh.consume(read(reader, bh));
        
    }

    @Override
    public void readToStructure(InputStream in, Blackhole bh) throws Exception {
        bh.consume(read(in, bh));
        
    }


    protected Object read(final InputStream stream, final Blackhole bh) throws Exception {
        final JsonReader jreader = readerFactory.createReader(stream);
        final JsonStructure js = jreader.read();
        bh.consume(js);
        jreader.close();
        return jreader;
    }

    protected Object read(final Reader reader, final Blackhole bh) throws Exception {
        final JsonReader jreader = readerFactory.createReader(reader);
        final JsonStructure js = jreader.read();
        bh.consume(js);
        jreader.close();
        return jreader;
    }
    

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

}
