package br.com.victorhgbb.service;

import br.com.victorhgbb.client.TwitterClient;
import br.com.victorhgbb.dto.CurrencyDTO;
import br.com.victorhgbb.model.Tweet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TwitterService {
    private final TwitterClient twitterClient;

    public TwitterService(TwitterClient twitterClient) {
        this.twitterClient = twitterClient;
    }

    public ResponseEntity<?> createTweet(CurrencyDTO dto) {
        String tweetText = dto.getFormattedName() + dto.getFormattedValue() + dto.getFormattedDate();
        Tweet tweet = new Tweet(tweetText);
        return twitterClient.createTweet(tweet);
    }
}
