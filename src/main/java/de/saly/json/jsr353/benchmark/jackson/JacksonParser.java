package de.saly.json.jsr353.benchmark.jackson;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;

import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.saly.json.jsr353.benchmark.BenchmarkEnabledParser;

public class JacksonParser implements BenchmarkEnabledParser {

    private JsonFactory jsonFactory;
    private ObjectMapper mapper;
    
    public JacksonParser() {
        
    }

    @Override
    public void parseOnly(File file, Blackhole bh) throws Exception{
        JsonParser jp = jsonFactory.createParser(file);
        bh.consume(parse(jp, bh));
    }

    @Override
    public void parseOnly(Reader reader, Blackhole bh)throws Exception {
        JsonParser jp = jsonFactory.createParser(reader);
        bh.consume(parse(jp, bh));
    }

    @Override
    public void parseOnly(InputStream in, Blackhole bh)throws Exception {
        JsonParser jp = jsonFactory.createParser(in);
        bh.consume(parse(jp, bh));
    }

    @Override
    public void init(Map<String, String> config) {
        jsonFactory = new JsonFactory();
        mapper = new ObjectMapper();
    }
    

    @Override
    public void readToStructure(File file, Blackhole bh) throws Exception {
        bh.consume(mapper.readValue(file, JsonNode.class));
    }


    @Override
    public void readToStructure(Reader reader, Blackhole bh) throws Exception {
        bh.consume(mapper.readValue(reader, JsonNode.class));
    }

    @Override
    public void readToStructure(InputStream in, Blackhole bh) throws Exception {
        bh.consume(mapper.readValue(in, JsonNode.class));
    }
    
    

    protected Object parse(JsonParser jParser, Blackhole bh) throws Exception {
        
        while (jParser.nextToken() != null) {
                JsonToken token = jParser.getCurrentToken();
                bh.consume(token);
          }
          jParser.close();
          return jParser;
    }


    @Override
    public void serialize(File file, Object o, Blackhole bh) throws Exception {
        mapper.writeValue(file, o);
        bh.consume(file);
        
    }


    @Override
    public void serialize(CharArrayWriter writer, Object o, Blackhole bh) throws Exception {
        mapper.writeValue(writer, o);
        bh.consume(writer);
    }


    @Override
    public void serialize(ByteArrayOutputStream out, Object o, Blackhole bh) throws Exception {
        mapper.writeValue(out, o);
        bh.consume(out);
        
    }


    @Override
    public void deserialize(File file, Class clazz, Blackhole bh) throws Exception {
        bh.consume(mapper.readValue(file, clazz));
        
    }

    @Override
    public void deserialize(Reader reader, Class clazz, Blackhole bh) throws Exception {
        bh.consume(mapper.readValue(reader, clazz));
        
    }

    @Override
    public void deserialize(InputStream in, Class clazz, Blackhole bh) throws Exception {
        bh.consume(mapper.readValue(in, clazz));
        
    }

    

}
