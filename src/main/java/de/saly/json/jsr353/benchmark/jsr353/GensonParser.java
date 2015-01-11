package de.saly.json.jsr353.benchmark.jsr353;

import java.util.Map;

import javax.json.spi.JsonProvider;

import com.owlike.genson.ext.jsr353.GensonJsonProvider;

public class GensonParser extends Jsr353Parser{

    @Override
    public void init(Map<String, String> config) {
        JsonProvider provider = new GensonJsonProvider();
        
        parserFactory = provider.createParserFactory(config);
        readerFactory = provider.createReaderFactory(config);
    }

}
