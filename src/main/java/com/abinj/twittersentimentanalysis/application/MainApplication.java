package com.abinj.twittersentimentanalysis.application;

import com.abinj.twittersentimentanalysis.SentimentClassifier;

import java.io.IOException;

public class MainApplication {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Trainer trainer = new Trainer();
//        trainer.train();
        SentimentClassifier sentimentClassifier = new SentimentClassifier();
        System.out.println(sentimentClassifier.classify("Your text is a tragedy ..."));
    }
}
