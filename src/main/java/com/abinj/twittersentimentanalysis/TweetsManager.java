package com.abinj.twittersentimentanalysis;


import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TweetsManager {

    SentimentClassifier sentClassifier;
    int LIMIT= 100; //the number of retrieved tweets
    private Twitter twitter;

    public TweetsManager(String OAuthConsumerKey, String OAuthConsumerSecret, String OAuthAccessToken
            , String OAuthAccessTokenSecret) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(OAuthConsumerKey);
        cb.setOAuthConsumerSecret(OAuthConsumerSecret);
        cb.setOAuthAccessToken(OAuthAccessToken);
        cb.setOAuthAccessTokenSecret(OAuthAccessTokenSecret);
        twitter = new TwitterFactory(cb.build()).getInstance();
        sentClassifier = new SentimentClassifier();
    }

    public List<Status> performQuery(String keyword) throws InterruptedException, IOException {
        Query query = new Query(keyword + " -filter:retweets -filter:links -filter:replies -filter:images");
        query.setCount(LIMIT);
        query.setLocale("en");
        query.setLang("en");
        try {
            QueryResult queryResult = twitter.search(query);
            return queryResult.getTweets();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
