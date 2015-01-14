package de.saly.json.jsr353.benchmark.jsr353;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;

import javax.json.JsonReaderFactory;
import javax.json.spi.JsonProvider;
import javax.json.stream.JsonGeneratorFactory;
import javax.json.stream.JsonParserFactory;

import org.apache.johnzon.core.JsonProviderImpl;
import org.apache.johnzon.mapper.Mapper;
import org.apache.johnzon.mapper.MapperBuilder;
import org.openjdk.jmh.infra.Blackhole;

public class JohnzonParser extends Jsr353Parser{

    private Mapper mapper;
    private JsonGeneratorFactory generatorFactory;
    private JsonParserFactory parserFactory;
    private JsonReaderFactory readerFactory;
    
    @Override
    public void init(Map<String, String> config) {
        JsonProvider provider = new JsonProviderImpl();
        
        parserFactory = provider.createParserFactory(config);
        readerFactory = provider.createReaderFactory(config);
        generatorFactory = provider.createGeneratorFactory(config);
        mapper = new MapperBuilder().build();
    }

    @Override
    public JsonGeneratorFactory getGeneratorFactory() {
        return generatorFactory;
    }

    @Override
    public JsonParserFactory getParserFactory() {
        return parserFactory;
    }

    @Override
    public JsonReaderFactory getReaderFactory() {
       return readerFactory;
    }
    
    @Override
    public void serialize(File file, Object o, Blackhole bh) throws Exception {
        FileWriter fw = new FileWriter(file);
        mapper.writeObject(o, fw);
        fw.close();
    }

    @Override
    public void serialize(CharArrayWriter writer, Object o, Blackhole bh) throws Exception {
        mapper.writeObject(o, writer);
    }

    @Override
    public void serialize(ByteArrayOutputStream out, Object o, Blackhole bh) throws Exception {
        mapper.writeObject(o, out);
    }

    @Override
    public void deserialize(File file, Class clazz, Blackhole bh) throws Exception {
        FileReader fr = new FileReader(file);
        bh.consume(mapper.readObject(fr, clazz));
        fr.close();
    }

    @Override
    public void deserialize(Reader reader, Class clazz, Blackhole bh) throws Exception {
        bh.consume(mapper.readObject(reader, clazz));
    }

    @Override
    public void deserialize(InputStream in, Class clazz, Blackhole bh) throws Exception {
        bh.consume(mapper.readObject(in, clazz));
    }

    
    
}
