package de.saly.json.jsr353.benchmark.gson;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Map;

import org.openjdk.jmh.infra.Blackhole;

import com.google.gson.Gson;
import com.google.gson.JsonStreamParser;

import de.saly.json.jsr353.benchmark.BenchmarkEnabledParser;

public class GsonParser implements BenchmarkEnabledParser{

    
    private Gson gson;
    
    public GsonParser() {
    }

    @Override
    public void init(Map<String, String> config) {
        gson = new Gson();
        
    }

    @Override
    public void parseOnly(File file, Blackhole bh) throws Exception {
        JsonStreamParser jsp = new JsonStreamParser(new FileReader(file));
        while (jsp.hasNext()) {
            bh.consume(jsp.next());
          }
    }

    @Override
    public void parseOnly(Reader reader, Blackhole bh) throws Exception {
        JsonStreamParser jsp = new JsonStreamParser(reader);
        while (jsp.hasNext()) {
            bh.consume(jsp.next());
          }
        
    }

    @Override
    public void parseOnly(InputStream in, Blackhole bh) throws Exception {
        JsonStreamParser jsp = new JsonStreamParser(new InputStreamReader(in));
        while (jsp.hasNext()) {
            bh.consume(jsp.next());
          }
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

    @Override
    public void serialize(File file, Object o, Blackhole bh) throws Exception {
        FileWriter fw = new FileWriter(file);
        gson.toJson(o, fw);
        fw.close();
    }

    @Override
    public void serialize(CharArrayWriter writer, Object o, Blackhole bh) throws Exception {
        gson.toJson(o, writer);
    }

    @Override
    public void serialize(ByteArrayOutputStream out, Object o, Blackhole bh) throws Exception {
        gson.toJson(o, new OutputStreamWriter(out));
        
    }

    @Override
    public void deserialize(File file, Class clazz, Blackhole bh) throws Exception {
        FileReader fr = new FileReader(file);
        bh.consume(gson.fromJson(fr, clazz));
        fr.close();
        
    }

    @Override
    public void deserialize(Reader reader, Class clazz, Blackhole bh) throws Exception {
        bh.consume(gson.fromJson(reader, clazz));
        
    }

    @Override
    public void deserialize(InputStream in, Class clazz, Blackhole bh) throws Exception {
        bh.consume(gson.fromJson(new InputStreamReader(in), clazz));
    }
    
    

}
