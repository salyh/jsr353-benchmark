jsr353-benchmark
================

JMH based benchmark suite for benchmarking JSR 353 (Java API for JSON Processing) implementations.

<pre>Usage: java -Xmx2048m -Xms2048m -Djsr353.impl.folder=/folder/to/impl/jars -jar jsr353-benchmark-0.7.0-jar-with-dependencies.jar</pre>
<pre>Example: java -Xmx2048m -Xms2048m -Djsr353.impl.folder=/extlib/johnzon-0.2-incubating/ -jar jsr353-benchmark-0.7.0-jar-with-dependencies.jar</pre>

The result is stored in three files:

* <pre>jmh_benchmark_thrpt_log_..._tTIMESTAMP.txt</pre> contains the detailed logs and results from the JMH micro benchmarks.

* <pre>jmh_benchmark_thrpt_result_..._tTIMESTAMP.txt</pre> contains the final results from the JMH micro benchmarks.

* <pre>jmh_benchmark_sysout_log_tTIMESTAMP.txt</pre> contains system informations and the results from the non JMH benchmarks.

Please note: The benchmark may run up to 30 min., so be patient. Especially the first run needs more time, because the benchmark test data needs to be created. This happens only once, so subsequent runs should be slightly faster. Do not work on your computer while benchmarking to get comparable results. You also should not run other processes with high CPU and I/O load.

<b>Do not interrupt the benchmark once started!</b> This may lead to truncated test data files and may cause errors or give wrong results. Delete all data in the <pre>generated</pre> folder after a interruption to prevent this and to restart from scratch.

<b>Please make sure that you have enough RAM available so that the JVM not has to swap. </b>

<a href="https://github.com/salyh/jsr353-benchmark/wiki">Results are here</a><br>
