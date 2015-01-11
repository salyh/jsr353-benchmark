package de.saly.json.jsr353.benchmark.visual;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.io.FileUtils;
import org.apache.johnzon.core.JsonProviderImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

public class ResultViewer extends JFrame 
{
   
    private Map<String, Benchmark> benchmarks;

    public ResultViewer() throws IOException {
        super("Json Parser Benchmark");

        readData();

        JPanel panel = new JPanel(new GridLayout(20, 3)); // split the panel in 1 rows and 2 cols
        
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");

        for (Iterator iterator = benchmarks.keySet().iterator(); iterator.hasNext();) {
            
            String bname = iterator.next().toString();
            System.out.println("Chart for "+bname);
            
            JFreeChart barChart = ChartFactory.createBarChart("Json Parser Benchmark (more is better)", "Benchmark", "ops/s",
                    createDataset(bname), PlotOrientation.VERTICAL, true, true, false);

            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new Dimension(400, 250));
            
            int width = 640; 
            int height = 480; 
            
            if(!new File("charts").exists()) {
                new File("charts").mkdir();
            }
            
            ChartUtilities.saveChartAsJPEG( new File("charts/"+bname+".jpg") , barChart , width , height );
            sb.append("<img border=0 width="+width+" height="+height+" src=\""+bname+".jpg"+"\">");
            sb.append("<body><html>");
            
            FileUtils.write(new File("charts/index.html"), sb.toString());
            
            //getContentPane().add(chartPanel);
            //setContentPane(chartPanel);
            panel.add(chartPanel);
        }
        JScrollPane scrollFrame = new JScrollPane(panel);
        getContentPane().add(scrollFrame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    
    

    public static void main(String[] args) throws IOException {
        ResultViewer chart = new ResultViewer();
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

    private CategoryDataset createDataset(String bname) throws FileNotFoundException {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Iterator iterator = benchmarks.get(bname).getValues().entrySet().iterator(); iterator.hasNext();) {
            Entry<String, Double> entry = (Entry<String, Double>) iterator.next();
            dataset.addValue(entry.getValue(), entry.getKey(), bname);

        }

        return dataset;
    }

    private void readData() throws FileNotFoundException {
        benchmarks = new TreeMap<String, ResultViewer.Benchmark>();

        File[] files = new File(".").listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {

                return name.startsWith("jmh_benchmark_thrpt_result_");
            }
        });

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            String filename = file.getName();
            String parser = filename.split("_")[4];
            parser = parser.substring(parser.lastIndexOf(".") + 1);
            parser = parser.substring(0, parser.indexOf("Parser"));
            System.out.println(" -"+parser);
            javax.json.JsonArray array = new JsonProviderImpl().createReader(new FileReader(file)).readArray();

            
            for (int k = 0; k < array.size(); k++) {
                String benchmarkName = ((javax.json.JsonObject) array.get(k)).getString("benchmark");
                benchmarkName = benchmarkName.substring(benchmarkName.lastIndexOf(".") + 1);

                System.out.println("   -"+benchmarkName);
                
                Benchmark benchmark = benchmarks.get(benchmarkName);

                if (benchmark == null) {
                    benchmark = new Benchmark(benchmarkName);
                    benchmarks.put(benchmarkName, benchmark);
                }

                double score = ((javax.json.JsonObject) array.get(k)).getJsonObject("primaryMetric").getJsonNumber("score").doubleValue();
                benchmark.addValue(parser, score);
            }

            
            
            
        }

    }

    private static class Benchmark {
        private String name;
        private Map<String, Double> values = new TreeMap<String, Double>();

        public String getName() {
            return name;
        }

        Benchmark(String name) {
            super();
            this.name = name;
        }

        public void addValue(String parser, double score) {
            values.put(parser, score);
        }

        public Map<String, Double> getValues() {
            return values;
        }

    }

}
