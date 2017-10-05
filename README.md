# Twitter-Sentiment-Analysis

Analyse the sentiment of tweets using Java

**Requirements**
    
    1) twitter4j
    https://mvnrepository.com/artifact/org.twitter4j/twitter4j-core/4.0.6
    
    2) Stanford Core NLP
       -stanford-corenlp
       -stanford-corenlp-models
    


**What is Sentiment Analysis?**
    
    Sentiment Analysis is the process of determining whether a piece of writing is positive, negative or neutral.
    It’s also known as opinion mining, deriving the opinion or attitude of a speaker. 
    A common use case for this technology is to discover how people feel about a particular topic.
     
    
**Phase 1 - Creating a Twitter App**
    
    -Gto to https://apps.twitter.com and create an app
    -Get the following informations
      oauth.consumerKey=<api-key-for-your-app>
      oauth.consumerSecret=<api-secret-for-your-app>
      oauth.accessToken=<access-token>
      oauth.accessTokenSecret=<access-token-secret>
    
**Phase 2 - Setup Tweets Manager**

    By using the creadentials we can query and fetch tweets with the help of twitter4j library.
    
**Phase 3 - Sentiment Analyzer**

    This class initializes the pipeline and findSentiment which takes in a tweet as input 
    and returns it’s sentiment score.
    
    scale of 0 = very negative, 1 = negative, 2 = neutral, 3 = positive, and 4 = very positive.

    
    