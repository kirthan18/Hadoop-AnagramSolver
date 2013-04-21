package com.parthparekh.hadoop;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 * This mapper class maps all the valid words from the word list files that are valid anagrams
 *
 * @author: Parth Parekh
 */
public class AnagramMapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, Text> {
	private JobConf conf;
	private static Logger logger = Logger.getLogger(AnagramMapper.class.getName());
	
    public void configure(JobConf job) {
    	conf = job;
    }
	
	public void map(LongWritable key, Text value,
			OutputCollector<Text, Text> outputCollector, Reporter reporter)
			throws IOException {
		String sortedAnagramWord = conf.get("sortedAnagramWord");
		String inputWord = value.toString();
		char[] inputWordChars = inputWord.toCharArray();
		Arrays.sort(inputWordChars);
		String sortedInputWord = new String(inputWordChars);
		if(sortedInputWord.equals(sortedAnagramWord)) {
			logger.info("input word matched with Anagram word: " + inputWord);
			Text outputKey = new Text();
			outputKey.set(sortedAnagramWord);
			Text outputValue = new Text();
			outputValue.set(inputWord);
			outputCollector.collect(outputKey, outputValue);
		}
	}
}
