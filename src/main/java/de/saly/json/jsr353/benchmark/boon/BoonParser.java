package de.saly.json.jsr353.benchmark.boon;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;

import org.boon.json.JsonParserAndMapper;
import org.boon.json.JsonParserFactory;
import org.openjdk.jmh.infra.Blackhole;

import de.saly.json.jsr353.benchmark.BenchmarkEnabledParser;

public class BoonParser implements BenchmarkEnabledParser{

    private JsonParserAndMapper parser;
    
    public BoonParser() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(Map<String, String> config) {
        parser = new JsonParserFactory().create();
        
    }

    @Override
    public void parseOnly(File file, Blackhole bh) throws Exception {
        throw new Exception("not implemented");
        
    }

    @Override
    public void parseOnly(Reader reader, Blackhole bh) throws Exception {
        throw new Exception("not implemented");
        
    }

    @Override
    public void parseOnly(InputStream in, Blackhole bh) throws Exception {
        throw new Exception("not implemented");
        
    }

    @Override
    public void readToStructure(File file, Blackhole bh) throws Exception {
        bh.consume(parser.parseFile(file.getAbsolutePath()));
        
    }

    @Override
    public void readToStructure(Reader reader, Blackhole bh) throws Exception {
        bh.consume(parser.parse (reader));
        
    }

    @Override
    public void readToStructure(InputStream in, Blackhole bh) throws Exception {
        bh.consume(parser.parse (in));
        
    }

}
