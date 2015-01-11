package de.saly.json.jsr353.benchmark.jsr353;

import java.util.Map;

import javax.json.spi.JsonProvider;

import org.apache.johnzon.core.JsonProviderImpl;

public class JohnzonParser extends Jsr353Parser{

    @Override
    public void init(Map<String, String> config) {
        JsonProvider provider = new JsonProviderImpl();
        
        parserFactory = provider.createParserFactory(config);
        readerFactory = provider.createReaderFactory(config);
    }

}
