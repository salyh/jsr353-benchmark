package de.saly.json.jsr353.benchmark.nonjmh;

import java.io.File;
import java.io.FileInputStream;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonParserFactory;

public class JsonInMemoryParsing {

    final static JsonParserFactory parserFactory = Json.createParserFactory(Collections.EMPTY_MAP);
    final static JsonReaderFactory readerFactory = Json.createReaderFactory(Collections.EMPTY_MAP);

    public JsonInMemoryParsing() {
        // TODO Auto-generated constructor stub
    }

    public static void main(final String[] args) throws Exception {

        final String IMPL = Json.createArrayBuilder().getClass().getPackage().getName();

        System.out.println("Start benchmark on " + new Date());
        System.out.println("Currently loaded implementation:  " + IMPL);

        final File file = new File("./generated/generated_benchmark_test_file_UTF-8_100000.json");
        final JsonReader reader = readerFactory.createReader(new FileInputStream(file));
        System.out.println("Totalmem: " + (Runtime.getRuntime().totalMemory() / (1024 * 1024)) + " mb");
        final JsonObject jo = reader.readObject();
        System.out.println("Totalmem: " + (Runtime.getRuntime().totalMemory() / (1024 * 1024)) + " mb");
        System.out.println("File created: " + new Date(file.lastModified()));
        System.out.println("Filesize: " + file.length() + " bytes");
        System.out.println("Filesize: " + (file.length() / (1024 * 1024)) + " mb");

        System.out.println("Data loaded, warmup ...");

        JsonParser parser = parserFactory.createParser(jo);

        int arrays = 0;
        int objects = 0;
        int keys = 0;
        int literals = 0;
        int numbers = 0;
        int strings = 0;

        while (parser.hasNext()) {
            final Event evt = parser.next();

            if (evt == null) {
                throw new RuntimeException();
            }

            switch (evt) {
                case START_ARRAY:
                    arrays++;
                    break;
                case START_OBJECT:
                    objects++;
                    break;
                case KEY_NAME:
                    keys++;
                    break;

                case VALUE_FALSE:
                    literals++;
                    break;
                case VALUE_TRUE:
                    literals++;
                    break;
                case VALUE_NULL:
                    literals++;
                    break;

                case VALUE_NUMBER:
                    numbers++;
                    break;

                case VALUE_STRING:
                    strings++;
                    break;
            }

        }

        parser.close();

        System.out.println("arrays " + NumberFormat.getInstance().format(arrays));
        System.out.println("objects " + NumberFormat.getInstance().format(objects));
        System.out.println("keys " + NumberFormat.getInstance().format(keys));
        System.out.println("literals " + NumberFormat.getInstance().format(literals));
        System.out.println("numbers " + NumberFormat.getInstance().format(+numbers));
        System.out.println("strings " + NumberFormat.getInstance().format(strings));

        parser = parserFactory.createParser(jo);

        while (parser.hasNext()) {
            final Event evt = parser.next();

            if (evt == null) {
                throw new RuntimeException();
            }

        }

        parser.close();

        System.out.println("Start parsing...");
        final long start = System.currentTimeMillis();

        parser = parserFactory.createParser(jo);

        while (parser.hasNext()) {
            final Event evt = parser.next();

            if (evt == null) {
                throw new RuntimeException();
            }

        }

        parser.close();

        parser = parserFactory.createParser(jo);

        while (parser.hasNext()) {
            final Event evt = parser.next();

            if (evt == null) {
                throw new RuntimeException();
            }

        }

        parser.close();

        parser = parserFactory.createParser(jo);

        while (parser.hasNext()) {
            final Event evt = parser.next();

            if (evt == null) {
                throw new RuntimeException();
            }

        }

        parser.close();

        parser = parserFactory.createParser(jo);

        while (parser.hasNext()) {
            final Event evt = parser.next();

            if (evt == null) {
                throw new RuntimeException();
            }

        }

        parser.close();

        System.out.println("Totalmem: " + (Runtime.getRuntime().totalMemory() / (1024 * 1024)) + " mb");

        final long end = System.currentTimeMillis();

        System.out.println("Duration: " + ((end - start)) + " ms");
        //System.out.println("String Events: " + sevents);
        //System.out.println("Integral Number Events: " + ievents);
        //System.out.println("Big Decimal Events: " + bevents);
        System.out.println("Parsing speed: " + ((file.length() * 4) / (end - start)) + " bytes/ms");
        System.out.println("Parsing speed: " + ((file.length() * 4) / ((end - start) / 1000)) + " bytes/sec");
        System.out.println("Parsing speed: " + (((file.length() * 4) / 1024) / ((end - start) / 1000)) + " kbytes/sec");
        System.out.println("Parsing speed: " + (((file.length() * 4) / (1024 * 1024)) / ((end - start) / 1000)) + " mb/sec");
        System.out.println("Parsing speed: " + (((file.length() * 4 * 8) / (1024 * 1024)) / ((end - start) / 1000)) + " mbit/sec");

    }

}
