package de.saly.json.jsr353.benchmark;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;

import org.openjdk.jmh.infra.Blackhole;

public interface BenchmarkEnabledParser {
    
    void init(Map<String, String> config);
    
    void parseOnly(File file, Blackhole bh) throws Exception;
    void parseOnly(Reader reader, Blackhole bh)throws Exception;
    void parseOnly(InputStream in, Blackhole bh)throws Exception;
    
    void readToStructure(File file, Blackhole bh) throws Exception;
    void readToStructure(Reader reader, Blackhole bh)throws Exception;
    void readToStructure(InputStream in, Blackhole bh)throws Exception;
}
