package br.com.victorhgbb.client;

import br.com.victorhgbb.client.config.TwitterFeignOAuthConfig;
import br.com.victorhgbb.model.Tweet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "TwitterClient", url = "https://api.twitter.com/2", configuration = TwitterFeignOAuthConfig.class)
public interface TwitterClient {

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    HttpEntity<?> send(@RequestBody Tweet tweet);

    @PostMapping("/tweets")
    ResponseEntity<?> createTweet(@RequestBody Tweet tweet);

}
