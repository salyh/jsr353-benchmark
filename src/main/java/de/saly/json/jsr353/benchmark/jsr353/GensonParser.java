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

import org.openjdk.jmh.infra.Blackhole;

import com.owlike.genson.Genson;
import com.owlike.genson.ext.jsr353.GensonJsonProvider;

public class GensonParser extends Jsr353Parser{

    private Genson genson;
    private JsonGeneratorFactory generatorFactory;
    private JsonParserFactory parserFactory;
    private JsonReaderFactory readerFactory;
    
    @Override
    public void init(Map<String, String> config) {
        JsonProvider provider = new GensonJsonProvider();
        
        parserFactory = provider.createParserFactory(config);
        readerFactory = provider.createReaderFactory(config);
        generatorFactory = provider.createGeneratorFactory(config);
        genson = new Genson();
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
        genson.serialize(o, fw);
    }

    @Override
    public void serialize(CharArrayWriter writer, Object o, Blackhole bh) throws Exception {
        genson.serialize(o, writer);
    }

    @Override
    public void serialize(ByteArrayOutputStream out, Object o, Blackhole bh) throws Exception {
        genson.serialize(o, out);
    }

    @Override
    public void deserialize(File file, Class clazz, Blackhole bh) throws Exception {
        FileReader fr = new FileReader(file);
        bh.consume(genson.deserialize(fr, clazz));
        fr.close();
    }

    @Override
    public void deserialize(Reader reader, Class clazz, Blackhole bh) throws Exception {
        bh.consume(genson.deserialize(reader, clazz));
    }

    @Override
    public void deserialize(InputStream in, Class clazz, Blackhole bh) throws Exception {
        bh.consume(genson.deserialize(in, clazz));
    }

    
    
}
