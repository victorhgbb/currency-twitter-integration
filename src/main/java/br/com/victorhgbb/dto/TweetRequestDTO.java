package br.com.victorhgbb.dto;

import br.com.victorhgbb.model.Tweet;

public class TweetRequestDTO {
    public String text;

    public TweetRequestDTO(Tweet tweet) {
        this.text = tweet.text();
    }

    public String getText() {
        return text;
    }
}
