1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
71
72
73
74
75
76
77
78
79
80
81
82
83
84
85
86
87
88
89
90
91
92
93
94
95
96
97
package org.apache.hadoop.ramapo;
import java.io.IOException;
import java.util.StringTokenizer;
import java.lang.*;
 
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
 
public class MaxTemp{
     
    public static class MyMapper
            extends Mapper<LongWritable, Text, Text, IntWritable>{                    
         
        private Text word = new Text();
 
        public void map(LongWritable key, Text value, Context context           
                        ) throws IOException, InterruptedException{
            StringTokenizer itr = new StringTokenizer(value.toString());  
            if(itr.hasMoreTokens() == false){
                return;
            }
                         
            Text month = new Text(itr.nextToken());     
            IntWritable temp = new IntWritable(Integer.parseInt(itr.nextToken()));                      
            context.write(month, temp); 
                                 
 
            }   
    }
 
    public static class IntSumReducer
            extends Reducer<Text, IntWritable, Text, IntWritable>{
        private IntWritable result = new IntWritable();
                 
        public void reduce(Text key, Iterable<IntWritable> values, Context context
                            ) throws IOException, InterruptedException{
            int max = 0;
            for(IntWritable val : values){
                max = Math.max(max, val.get());
            }
            result.set(max);
            context.write(key, result);
        }
             
    }   
             
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        Job job =Job.getInstance(conf, "MaxTemp");
        job.setJarByClass(MaxTemp.class);
        job.setMapperClass(MyMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0: 1);
    }
}
