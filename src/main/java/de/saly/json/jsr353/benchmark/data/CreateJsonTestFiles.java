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
package de.saly.json.jsr353.benchmark.data;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.johnzon.mapper.Mapper;
import org.apache.johnzon.mapper.MapperBuilder;

import de.saly.json.jsr353.benchmark.data.model.Collection;
import de.saly.json.jsr353.benchmark.data.model.Simple;

public class CreateJsonTestFiles {

    private CreateJsonTestFiles() {

    }

    public static void main(final String[] args) throws Exception {

        create("./generated/");
    }

    public static void create(String path) throws Exception {

        if (path == null || path.length() == 0) {
            path = "./generated/";
        }

        final File dir = new File(path).getAbsoluteFile();
        dir.mkdirs();

        System.out.println("Generating benchmark data " + dir.getAbsolutePath());

        create(path, 1, StandardCharsets.UTF_8);
        create(path, 1, StandardCharsets.UTF_16);

        create(path, 10, StandardCharsets.UTF_8);
        create(path, 10, StandardCharsets.UTF_16);

        create(path, 100, StandardCharsets.UTF_8);
        create(path, 100, StandardCharsets.UTF_16);

        create(path, 1000, StandardCharsets.UTF_8);
        create(path, 1000, StandardCharsets.UTF_16);

        create(path, 10000, StandardCharsets.UTF_8);
        create(path, 10000, StandardCharsets.UTF_16);

        create(path, 100000, StandardCharsets.UTF_8);
        create(path, 100000, StandardCharsets.UTF_16);

        create(path, 10000000, StandardCharsets.UTF_8); // 10gb
        createBigStack(path, 10000000 * 10, StandardCharsets.UTF_8);
        
        Mapper mapper = new MapperBuilder().build();
        File json = new File(path + "/" + "generated_benchmark_test_file_" + StandardCharsets.UTF_8.name() + "_mapping_simple.json");
        FileWriterWithEncoding sb = new FileWriterWithEncoding(json, StandardCharsets.UTF_8);
        Simple simple = new Simple();
        simple.init();
        mapper.writeObject(simple, sb);
        sb.close();
        
        json = new File(path + "/" + "generated_benchmark_test_file_" + StandardCharsets.UTF_8.name() + "_mapping_collection.json");
        sb = new FileWriterWithEncoding(json, StandardCharsets.UTF_8);
        Collection co = new Collection();
        co.init();
        mapper.writeObject(co, sb);
        sb.close();
        
        
        System.out.println("Finished.");
        System.out.println();
    }

    private static File create(final String path, final int count, final Charset charset) throws Exception {

        if (count < 0 || path == null || path.length() == 0) {
            throw new IllegalArgumentException();
        }

        final File json = new File(path + "/" + "generated_benchmark_test_file_" + charset.name() + "_" + count + ".json");

        if (json.exists()) {
            System.out.println("File already exists: " + json.getAbsolutePath());
            return json;
        }

        final FileWriterWithEncoding sb = new FileWriterWithEncoding(json, charset);

        sb.append("{\n");

        for (int i = 0; i < count; i++) {

            sb.append("\t\"special-" + i + "\":" + "\"" + "\\\\f\\n\\r\\t\\u6532\uDC00\uD800" + "\",\n");
            sb.append("\t\"unicode-\\u0000- " + i + "\":\"\\u5656\uDC00\uD800\",\n");
            sb.append("\t\"\u6532bigdecimal" + i + "\":7817265.00000111,\n");
            sb.append("\t\"bigdecimal-2-" + i + "\":127655512123456.761009E-123,\n");
            sb.append("\t\"string-" + i + "\":\"lorem ipsum, ????????????.-,<!$%&/()9876543XXddddJJJJJJhhhhhhhh\uDC00\uD800\",\n");
            sb.append("\t\t\t\t\"int" + i + "\":[1,-4543,112,0,1,10,100,87,34112, true, false, null],\n");
            sb.append("\t\"\u6532ints" + i + "\":0,\n");
            sb.append("\t\"\u6532false" + i + "\":false,\n");
            sb.append("\t\"\u6532nil" + i + "\":false,\n");
            sb.append("\t\"\u6532n" + i + "\":      null                ,\n");
            sb.append("\t\"obj" + i + "\":\n");

            sb.append("\t\t{\n");

            sb.append("\t\t\t\"special-" + i + "\":" + "\"" + "\\\\f\\n\\r\\t\\u6532" + "\",\n");
            sb.append("\t\t\t\"unicode-\\u0000- " + i + "\":\"\\u5656\",\n");
            sb.append(" \"bigdecimal" + i + "\":7817265.00000111,\n");
            sb.append("\t\t\t\"bigdecimal-2-" + i + "\":127655512123456.761009E-123,\n");
            sb.append("\t\t\t\"string-" + i + "\":\"lorem ipsum, ????????????.-,<!$%&/()9876543XXddddJJJJJJhhhhhhhh\",\n");
            sb.append("\t\t\t\t\"int" + i + "\":[1,-4543,112,0,1,10,100,87,34112, true, false, null],\n");
            sb.append("\t\t\t\"ints" + i + "\":0,\n");
            sb.append("\t\t\t\"false" + i + "\":false,\n");
            sb.append("\t\t\t\"nil" + i + "\":false,\n");
            sb.append("\t\t\t\"obj" + i + "\":      null                ,\n");
            sb.append("\t\t\t\"obj" + i + "\":\n");
            sb.append("\t\t\t\t[    true, \"normal string ascii only normal string ascii only\"    ,\n");

            sb.append("\t\t\t\t{\n");

            sb.append("\t\t\t\t\"special-" + i + "\":" + "\"" + "\\\\f\\n\\r\\t\\u6532" + "\",\n");
            sb.append("\t\t\t\t\"unicode-\\u0000- " + i + "\":\"\\u5656\",\n");
            sb.append("\t\t\t\t\"bigdecimal" + i + "\":7817265.00000111,\n");
            sb.append("\t\t\t\t\"bigdecimal-2-" + i + "\":127655512123456.761009E-123,\n");
            sb.append("\t\t\t\t\"string-" + i + "\":\"lorem ipsum, ????????????.-,<!$%&/()9876543XXddddJJJJJJhhhhhhhh\",\n");
            sb.append("\t\t\t\t\"int" + i + "\":[1,-4543,112,0,1,10,100,87,34112, true, false, null],\n");
            sb.append("\t\t\t\t\"ints" + i + "\":0,\n");
            sb.append("\t\t\t\t\"false" + i + "\":false,\n");
            sb.append("\t\t\t\t\"nil" + i + "\":false,\n");
            sb.append("\t\t\t\t\"obj" + i + "\":      null                \n");
            sb.append("\t\t\t\t\n}\n");

            sb.append("\t\t\t]\n");

            sb.append("\t\t\n}\n\n\n\n                 \t\r                                                      ");

            if (i < count - 1) {
                sb.append(",\n");
            } else {
                sb.append("\n");
            }
        }

        sb.append("\n}\n");

        sb.close();

        return json;

    }

    private static File createBigStack(final String path, final int count, final Charset charset) throws Exception {

        if (count < 0 || path == null || path.length() == 0) {
            throw new IllegalArgumentException();
        }

        final File json = new File(path + "/" + "generated_benchmark_test_file_bigstack_" + charset.name() + "_" + count + ".json");

        if (json.exists()) {
            System.out.println("File already exists: " + json.getAbsolutePath());
            return json;
        }

        final FileWriterWithEncoding sb = new FileWriterWithEncoding(json, charset);

        for (int i = 0; i < count; i++) {
            sb.append("{\"key\":");
            sb.append("[true" + (i == count - 1 ? "" : ","));
        }

        for (int i = 0; i < count; i++) {
            sb.append("]");
            sb.append("}");
        }

        sb.close();

        return json;

    }

}