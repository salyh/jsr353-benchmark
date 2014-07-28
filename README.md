jsr353-benchmark
================

JMH based benchmark suite for benchmarking JSR 353 (Java API for JSON Processing) implementations.

<pre>Usage: mvn compile exec:exec -Dimpljar=fleece-core-1.0-SNAPSHOT</pre>
<pre>Usage: mvn compile exec:exec -Dimpljar=javax.json-1.0.5-SNAPSHOT.jar</pre>
<pre>Usage: mvn compile exec:exec -Dimpljar=another-impl-of-jsr353.jar</pre>

The .jar file with the implementation must reside in the same directory as the pom.xml

The result is stored in two files:

* <pre>jmh_benchmark_thrpt_log_f1_t1_w3_i3_tTIMESTAMP.txt</pre> contains the log and results from the JMH micro benchmarks

* <pre>JSR353-benchmark-result_TIMESTAMP.txt</pre> contains the results from the non JMH benchmarks.

Please note: The benchmark may run up to 30 min, so be patient. Especially the first run needs more time, because the benchmark test data needs to be created. This happens only once, so subsequent runs should be slightly faster. Do not work on your computer while benchmarking to get comparable results. You also should not run other processes with high CPU and I/O load.

<b>Do not interrupt the benchmark once started!</b> This may lead to truncated test data files and may cause errors or give wrong results. Delete all data in the <pre>generated</pre> folder after a interruption to prevent this und to restart from scratch.

<b>Please make sure that you have enough RAM available so that the JVM not has to swap. </b>

<a href=„https://github.com/salyh/jsr353-benchmark/wiki“>Results are here</a>