package com.abinj.twittersentimentanalysis.analyzer;

import com.abinj.twittersentimentanalysis.TweetWithSentiment;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

import java.util.Properties;

public class SentimentAnalyzer {

    public TweetWithSentiment findSentiment(String line) {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP stanfordCoreNLP = new StanfordCoreNLP(properties);
        int mainSentiment = 0;
        if (line != null && !line.isEmpty()) {
            int longest = 0;
            Annotation annotation = stanfordCoreNLP.process(line);
            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                Tree tree = sentence.get(SentimentCoreAnnotations.AnnotatedTree.class);
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                String partText = sentence.toString();
                if (partText.length() > longest) {
                    mainSentiment = sentiment;
                    longest = partText.length();
                }
            }
        }
        if (mainSentiment == 2 || mainSentiment > 4 || mainSentiment < 0) {
            return null;
        }
        TweetWithSentiment tweetWithSentiment = new TweetWithSentiment(line, toCss(mainSentiment));
        return tweetWithSentiment;
    }

    private String toCss(int sentiment) {
        switch (sentiment) {
            case 0:
                return "Very Negative";
            case 1:
                return "Negative";
            case 2:
                return "Neutral";
            case 3:
                return "Positive";
            case 4:
                return "Very Positive";
            default:
                return "";
        }
    }
}
