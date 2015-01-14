Json Parser Benchmark
=====================

JMH based benchmark suite for benchmarking various JSON parsers (currently: Jackson, Genson, GSON, Glassfish JSR 353 RI and Apache Johnzon). Does also generate benchmark result charts.

<pre>Usage: java -Xmx2048m -Xms2048m -jar json-parser-benchmark-1.0-SNAPSHOT-jar-with-dependencies.jar</pre>

Chart generator viewer: (see also charts/ folder after starting the ResultViewer)

<pre>Usage: java -cp json-parser-benchmark-1.0-SNAPSHOT-jar-with-dependencies.jar de.saly.json.jsr353.benchmark.visual.ResultViewer</pre>

The result is stored in three files:

* <pre>jmh_benchmark_thrpt_log_..._tTIMESTAMP.txt</pre> contains the detailed logs and results from the JMH micro benchmarks for each parser.

* <pre>jmh_benchmark_thrpt_result_..._tTIMESTAMP.txt</pre> contains the final results (json format) from the JMH micro benchmarks for each parser.

* <pre>jmh_benchmark_sysout_log_tTIMESTAMP.txt</pre> contains system informations and the results from the non JMH benchmarks.

Please note: The benchmark may run up to 40 min., so be patient. Especially the first run needs more time, because the benchmark test data needs to be created. This happens only once, so subsequent runs should be slightly faster. Do not work on your computer while benchmarking to get comparable results. You also should not run other processes with high CPU and I/O load.

<b>Do not interrupt the benchmark once started!</b> This may lead to truncated test data files and may cause errors or give wrong results. Delete all data in the <pre>generated</pre> folder after a interruption to prevent this and to restart from scratch.

<b>Please make sure that you have enough RAM available so that the JVM not has to swap.</b>