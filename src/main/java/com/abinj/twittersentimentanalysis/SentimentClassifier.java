package com.abinj.twittersentimentanalysis;

import com.aliasi.classify.ConditionalClassification;
import com.aliasi.classify.LMClassifier;
import com.aliasi.util.AbstractExternalizable;

import java.io.File;
import java.io.IOException;

public class SentimentClassifier {
    LMClassifier lmClassifier;

    public SentimentClassifier() {
        try {
            lmClassifier = (LMClassifier) AbstractExternalizable.readObject(new File(ConfigConstants.CLASSIFIER_PATH));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String classify(String text) {
        ConditionalClassification classification = lmClassifier.classify(text);
        return classification.bestCategory();
    }
}
