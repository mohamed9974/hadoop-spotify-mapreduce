import org.apache.commons.lang3.math.NumberUtils;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

// Partition the songs by year; first partition is the songs that came out on or before 2002
// (year ≤ 2002), the second partition is for songs that came out between 2002 and 2012
// (2002 < year ≤ 2012) and final partition is the songs that came out later than 2012
// (2012 < year). Report the average danceability of these 3 partitions (part-r-00000 to
// part-r-00002). (dancebyyear).

public class dancebyyear {

    // job
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: dancebyyear <input path> <output path>");
            System.exit(-1);
        }
        Job job = Job.getInstance();
        job.setJarByClass(dancebyyear.class);
        job.setJobName("dancebyyear");

        job.setMapperClass(dancebyyearMapper.class);
        job.setReducerClass(dancebyyearReducer.class);
        job.setNumReduceTasks(3);
        job.setPartitionerClass(dancebyyearPartitioner.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FloatWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    // Mapper
    public static class dancebyyearMapper
            extends Mapper<LongWritable, Text, Text, FloatWritable> {

        private static final FloatWritable danceability = new FloatWritable(0);
        // the value of the key is the danceability of the song which is a float value
        // the key is the year
        private final Text year = new Text();

        public void map(LongWritable offset, Text lineText, Context context)
                throws IOException, InterruptedException {
            // Split the line into tokens using the tsv delimiter
            String line = lineText.toString();
            String[] tokens = line.split("\t");
            // Check if the line has the correct number of tokens
            // Check if the year is not empty
            if (NumberUtils.isParsable(tokens[6])) {
                // Set the year range
                if (Integer.parseInt(tokens[4]) <= 2002) {
                    year.set("Before2002");
                } else if (Integer.parseInt(tokens[4]) <= 2012) {
                    year.set("2002-2012");
                } else {
                    year.set("After2012");
                }
                // Set the danceability
                danceability.set(NumberUtils.toFloat(tokens[6]));
                // Write the key-value pair
                context.write(year, danceability);
            } else
                System.out.println("Invalid line: " + line);
        }
    }

    // reducer get the average of dancaebility of each year
    public static class dancebyyearReducer
            extends Reducer<Text, FloatWritable, Text, FloatWritable> {
        public void reduce(
                Text year,
                Iterable<FloatWritable> danceability,
                Context context)
                throws IOException, InterruptedException {
            float sum = 0;
            float average = 0;
            for (FloatWritable dance : danceability) {
                sum += dance.get();
                average++;
            }
            context.write(year, new FloatWritable(sum / average));
        }
    }

    // partitioner
    public static class dancebyyearPartitioner
            extends org.apache.hadoop.mapreduce.Partitioner<Text, FloatWritable> {

        public int getPartition(
                Text year,
                FloatWritable danceability,
                int numPartitions) {
            if (year.toString().equals("Before2002")) {
                return 0;
            } else if (year.toString().equals("2002-2012")) {
                return 1;
            } else {
                return 2;
            }
        }
    }
}
