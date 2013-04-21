package com.parthparekh.hadoop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

/**
 * Job to solve single word anagrams
 *
 * @author: Parth Parekh
 */
public class AnagramSolverJob {
	public static void main(String[] args) {		
		if(args.length!=4) {
			System.err.println("Usage: hadoop -jar <Hadoop-AnagramSolver.jar> com.parthparekh.hadoop.AnagramSolverJob" +
					"<numberOfMaps> <wordlistFilePath> <outputFilePath> <anagramWord>");
			return;
		}
			
		JobConf conf = new JobConf(AnagramSolverJob.class);
		conf.setJobName("anagramsolver");
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);
		conf.setMapperClass(AnagramMapper.class);
		conf.setReducerClass(AnagramReducer.class);
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
		
		Integer numberOfMaps = Integer.parseInt(args[0]);
		String wordListPath = args[1];
		String outputFilePath = args[2];
		String anagramWord = args[3];		

		FileInputFormat.setInputPaths(conf, new Path(wordListPath));
		FileOutputFormat.setOutputPath(conf, new Path(outputFilePath));
		conf.setNumMapTasks(numberOfMaps);
		conf.setNumReduceTasks(1);
		char[] wordChars = anagramWord.toCharArray();
		Arrays.sort(wordChars);
		conf.set("sortedAnagramWord", String.valueOf(wordChars));

		try {
			JobClient.runJob(conf);
			FileSystem fileSys = FileSystem.get(conf);
			Path outputFile = new Path(outputFilePath+"/part-00000");
			FSDataInputStream in = fileSys.open(outputFile);
			if(in==null) {
				System.out.println("no anagrams found..");
				return;
			}
			BufferedReader br = new BufferedReader (new InputStreamReader (in));
			System.out.println("Single word anagrams for: " + anagramWord + " are: " );
			String line = br.readLine();
			while(line!=null) {
				System.out.println(line.replaceAll("\\s", "\n"));
				line = br.readLine();
			}
			
			br.close();
			in.close();
		} catch (IOException e) {
			System.out.println("Job failed with exception: ");
			e.printStackTrace();
		}
	}
}