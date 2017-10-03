package com.abinj.twittersentimentanalysis;

import java.util.List;

public class Result {
    private String keyword;
    private List<TweetWithSentiment> sentiments;

    public Result() {
        // TODO Auto-generated constructor stub
    }

    public Result(String keyword, List<TweetWithSentiment> sentiments) {
        super();
        this.keyword = keyword;
        this.sentiments = sentiments;
    }

    public String getKeyword() {
        return keyword;
    }

    public List<TweetWithSentiment> getSentiments() {
        return sentiments;
    }
}
