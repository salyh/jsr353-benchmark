package de.saly.json.jsr353.benchmark.jsr353;

import java.util.Map;

import javax.json.JsonReaderFactory;
import javax.json.spi.JsonProvider;
import javax.json.stream.JsonGeneratorFactory;
import javax.json.stream.JsonParserFactory;

import com.github.pgelinas.jackson.javax.json.spi.JacksonProvider;

public class JacksonPgelinasJSR353 extends Jsr353Parser {

    private JsonGeneratorFactory generatorFactory;
    private JsonParserFactory parserFactory;
    private JsonReaderFactory readerFactory;

    @Override
    public void init(final Map<String, String> config) {
        final JsonProvider provider = new JacksonProvider();

        parserFactory = provider.createParserFactory(config);
        readerFactory = provider.createReaderFactory(config);
        generatorFactory = provider.createGeneratorFactory(config);

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

}
