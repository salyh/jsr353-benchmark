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

import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.json.JsonObject;
import javax.json.JsonReaderFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.johnzon.core.JsonProviderImpl;

import de.saly.json.jsr353.benchmark.data.model.Collection;
import de.saly.json.jsr353.benchmark.data.model.Simple;

public class Buffers {

    private Buffers() {

    }
    
    public static final Simple SIMPLE = new Simple();
    public static final Collection COLLECTION = new Collection();
    
    static {
        SIMPLE.init();
        COLLECTION.init();
    }
    

    public static JsonReaderFactory FACTORY = new JsonProviderImpl().createReaderFactory(null);

    public static final byte[] B_1K = readBytes("1", StandardCharsets.UTF_8);
    public static final byte[] B_10K = readBytes("10", StandardCharsets.UTF_8);
    public static final byte[] B_100K = readBytes("100", StandardCharsets.UTF_8);
    public static final byte[] B_1000K = readBytes("1000", StandardCharsets.UTF_8);
    public static final byte[] B_UTF16_1000K = readBytes("1000", StandardCharsets.UTF_16);
    public static final byte[] B_10000K = readBytes("10000", StandardCharsets.UTF_8);
    public static final byte[] B_UTF16_10000K = readBytes("10000", StandardCharsets.UTF_16);
    
    public static final byte[] B_SIMPLE = readBytes("mapping_simple", StandardCharsets.UTF_8);
    public static final byte[] B_COLLECTION = readBytes("mapping_collection", StandardCharsets.UTF_8);

    public static final char[] C_1K = readChars("1", StandardCharsets.UTF_8);
    public static final char[] C_10K = readChars("10", StandardCharsets.UTF_8);
    public static final char[] C_100K = readChars("100", StandardCharsets.UTF_8);
    public static final char[] C_1000K = readChars("1000", StandardCharsets.UTF_8);
    public static final char[] C_UTF16_1000K = readChars("1000", StandardCharsets.UTF_16);
    public static final char[] C_10000K = readChars("10000", StandardCharsets.UTF_8);
    public static final char[] C_UTF16_10000K = readChars("10000", StandardCharsets.UTF_16);
    
    public static final char[] C_SIMPLE = readChars("mapping_simple", StandardCharsets.UTF_8);
    public static final char[] C_COLLECTION = readChars("mapping_collection", StandardCharsets.UTF_8);

    public static final JsonObject O_1K = FACTORY.createReader(new CharArrayReader(C_1K)).readObject();
    public static final JsonObject O_10K = FACTORY.createReader(new CharArrayReader(C_10K)).readObject();
    public static final JsonObject O_100K = FACTORY.createReader(new CharArrayReader(C_100K)).readObject();
    public static final JsonObject O_1000K = FACTORY.createReader(new CharArrayReader(C_1000K)).readObject();
    public static final JsonObject O_10000K = FACTORY.createReader(new CharArrayReader(C_10000K)).readObject();
    
    

    private static byte[] readBytes(final String count, final Charset charset) {

        try {
            return FileUtils.readFileToByteArray(new File("./generated/generated_benchmark_test_file_" + charset.name() + "_" + count
                    + ".json"));
        } catch (final IOException e) {
            return null;
        }

    }

    private static char[] readChars(final String count, final Charset charset) {

        InputStream in = null;
        try {
            in = new FileInputStream("./generated/generated_benchmark_test_file_" + charset.name() + "_" + count + ".json");
            return IOUtils.toCharArray(in, charset);
        } catch (final IOException e) {
            return null;
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    public static void init() {

    }

}
