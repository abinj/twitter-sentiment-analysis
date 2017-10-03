package com.abinj.twittersentimentanalysis.application;


import com.abinj.twittersentimentanalysis.Result;
import com.abinj.twittersentimentanalysis.TweetWithSentiment;
import com.abinj.twittersentimentanalysis.TweetsManager;
import com.abinj.twittersentimentanalysis.analyzer.SentimentAnalyzer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import twitter4j.Status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainApplication {

    private static TweetsManager tweetsManager = new TweetsManager("W86NVli4BSeDpcRLenPT1If1J"
            , "Aig6w4iN7kv9pQ3koK6AoS0XieJjOHAZ456Ax5xyPpZgwXcQ94"
            , "4267078812-mMxh9SnPqliUDlNYjFVr6zCKfCugu2WLaZ0h3jU"
            , "yMqE4Fs8Axv7Ru3wAzTD3BfXPnVcgoYe0OJeHOwasbwQ2");
    private static SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        String searchKeywords = "Good Morning";
        List<Result> results = new ArrayList<>();
        if (searchKeywords == null || searchKeywords.length() == 0) {
            return;
        }

        Set<String> keywords = new HashSet<>();
        for (String keyword : searchKeywords.split(",")) {
            keywords.add(keyword.trim().toLowerCase());
        }
        if (keywords.size() > 3) {
            keywords = new HashSet<>(new ArrayList<>(keywords).subList(0, 3));
        }
        for (String keyword : keywords) {
            List<Status> statuses = tweetsManager.performQuery(keyword);
            System.out.println("Found statuses ... " + statuses.size());
            List<TweetWithSentiment> sentiments = new ArrayList<>();
            for (Status status : statuses) {
                TweetWithSentiment tweetWithSentiment = sentimentAnalyzer.findSentiment(status.getText());
                if (tweetWithSentiment != null) {
                    sentiments.add(tweetWithSentiment);
                }
            }
            System.out.println(keyword + ": " + gson.toJson(sentiments));
            Result result = new Result(keyword, sentiments);
            results.add(result);
        }
        return;
    }
}
