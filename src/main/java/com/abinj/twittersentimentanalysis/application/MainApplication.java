package com.abinj.twittersentimentanalysis.application;

import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.LMClassifier;
import com.aliasi.util.AbstractExternalizable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MainApplication {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File trainDir;
        String[] categories;
        LMClassifier classy;
        trainDir = new File("trainDirectory");
        categories = trainDir.list();
        int nGram = 7; //the nGram level, any value between 7 and 12 works
        classy = DynamicLMClassifier.createNGramProcess(categories, nGram);
        for (int i = 0; i < categories.length; ++i) {
            String category = categories[i];
            Classification classification = new Classification(category);
            File file = new File(trainDir, categories[i]);
            File[] trainFiles = file.listFiles();
            for (int j = 0; j < trainFiles.length; ++j) {
                File trainFile = trainFiles[j];
                String review = Files.readFromFile(trainFile, "ISO-8859-1");
                Classified classified = new Classified(review, classification);
                ((ObjectHandler>) class).handle(classified);
            }
        }
        AbstractExternalizable.compileTo((Compilable) class, new File("classifier.txt"));
    }
}
