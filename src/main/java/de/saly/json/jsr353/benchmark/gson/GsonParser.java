package de.saly.json.jsr353.benchmark.gson;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

import org.openjdk.jmh.infra.Blackhole;

import com.google.gson.Gson;

import de.saly.json.jsr353.benchmark.BenchmarkEnabledParser;

public class GsonParser implements BenchmarkEnabledParser{

    
    private Gson gson;
    
    public GsonParser() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(Map<String, String> config) {
        gson = new Gson();
        
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
        bh.consume(gson.fromJson(new FileReader(file), Map.class));
        
    }

    @Override
    public void readToStructure(Reader reader, Blackhole bh) throws Exception {
        bh.consume(gson.fromJson(reader, Map.class));
        
    }

    @Override
    public void readToStructure(InputStream in, Blackhole bh) throws Exception {
        bh.consume(gson.fromJson(new InputStreamReader(in, "UTF-8"), Map.class));
        
    }

}
