/*
Copyright (C) 2014 Hendrik Saly

This file is part of JSR 353 Benchmark Suite.

JSR 353 Benchmark Suite is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License Version 2 as published by
the Free Software Foundation.

JSR 353 Benchmark Suite is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with JSR 353 Benchmark Suite. If not, see <http://www.gnu.org/licenses/>.
*/
package de.saly.json.jsr353.benchmark.nonjmh;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Date;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonParserFactory;

import de.saly.json.jsr353.benchmark.data.CreateJsonTestFiles;

public class ParseHugeJsonFile {

    final static JsonParserFactory parserFactory = Json.createParserFactory(Collections.EMPTY_MAP);

    public static void run(final File file) throws Exception {

        if (!Charset.defaultCharset().equals(Charset.forName("UTF-8"))) {
            throw new RuntimeException("Default charset is " + Charset.defaultCharset() + ", must be UTF-8");
        }

        if (!file.exists()) {
            System.out.println("ERROR -  " + file.getAbsolutePath() + " does not exist.");
            return;
        }

        CreateJsonTestFiles.create(null);

        System.out.println("Start benchmarking of " + file.getAbsolutePath());

        long start = 0;
        long end = 0;

        System.out.println("File created: " + new Date(file.lastModified()));
        System.out.println("Filesize: " + file.length() + " bytes");
        System.out.println("Filesize: " + (file.length() / (1024 * 1024)) + " mb");

        start = System.currentTimeMillis();
        final JsonParser parser = parserFactory.createParser((new FileInputStream(file)));
        long sevents = 0;
        long ievents = 0;
        long bevents = 0;

        while (parser.hasNext()) {
            final Event e = parser.next();

            if (e == null) {
                throw new RuntimeException("null event");
            }

            if (e == Event.KEY_NAME || e == Event.VALUE_STRING) {

                if (parser.getString() == null) {
                    throw new RuntimeException("null event");
                }

                sevents++;

            } else if (e == Event.VALUE_NUMBER) {

                if (parser.isIntegralNumber()) {
                    final long l = parser.getLong();
                    if (l == 12343) {
                        throw new RuntimeException("long event");
                    }
                    ievents++;

                } else {
                    final BigDecimal bd = parser.getBigDecimal();
                    if (bd == null) {
                        throw new RuntimeException("null event");
                    }
                    bevents++;
                }

            }

        }

        parser.close();
        end = System.currentTimeMillis();

        System.out.println("Duration: " + ((end - start)) + " ms");
        System.out.println("String Events: " + sevents);
        System.out.println("Integral Number Events: " + ievents);
        System.out.println("Big Decimal Events: " + bevents);
        System.out.println("Parsing speed: " + (file.length() / (end - start)) + " bytes/ms");
        System.out.println("Parsing speed: " + (file.length() / ((end - start) / 1000)) + " bytes/sec");
        System.out.println("Parsing speed: " + ((file.length() / 1024) / ((end - start) / 1000)) + " kbytes/sec");
        System.out.println("Parsing speed: " + ((file.length() / (1024 * 1024)) / ((end - start) / 1000)) + " mb/sec");
        System.out.println("Parsing speed: " + (((file.length() * 8) / (1024 * 1024)) / ((end - start) / 1000)) + " mbit/sec");

    }

}
