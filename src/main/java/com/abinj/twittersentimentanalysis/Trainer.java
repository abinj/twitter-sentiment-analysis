package com.abinj.twittersentimentanalysis;

import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.LMClassifier;
import com.aliasi.corpus.ObjectHandler;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Compilable;
import com.aliasi.util.Files;

import java.io.File;
import java.io.IOException;

public class Trainer {
    private String charset;
    private String trainDirectory;
    private String classifierPath;

    public Trainer() {
        this.charset = ConfigConstants.CHARSET;
        this.trainDirectory = ConfigConstants.TRAIN_DIRECTORY;
        this.classifierPath = ConfigConstants.CLASSIFIER_PATH;
    }

    public Trainer(String charset, String trainDirectory, String classifierPath) {
        this.charset = charset;
        this.trainDirectory = trainDirectory;
        this.classifierPath = classifierPath;
    }

    public void train() throws IOException, ClassNotFoundException {
        File trainDir = new File(trainDirectory);
        String[] categories = trainDir.list();
        int nGram = 7;
        LMClassifier lmClassifier = DynamicLMClassifier.createNGramProcess(categories, nGram);
        for (int i = 0; i < categories.length; i++) {
            Classification classification = new Classification(categories[i]);
            File file = new File(trainDir, categories[i]);
            File[] trainFiles = file.listFiles();
            for (int j = 0; j< trainFiles.length; j++) {
                String reveiew = Files.readFromFile(trainFiles[j], charset);
                Classified classified = new Classified(reveiew, classification);
                ((ObjectHandler) lmClassifier).handle(classified);
            }
        }
        AbstractExternalizable.compileTo((Compilable) lmClassifier, new File(classifierPath));
    }
}
