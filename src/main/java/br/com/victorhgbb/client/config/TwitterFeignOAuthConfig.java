package br.com.victorhgbb.client.config;

import br.com.victorhgbb.helper.OAuthHelper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwitterFeignOAuthConfig {

    @Value("${twitter.key}")
    private final String API_KEY = "sua_api_key";

    @Value("${twitter.secret}")
    private final String API_SECRET = "sua_api_secret";

    @Value("${twitter.token}")
    private final String ACCESS_TOKEN = "seu_access_token";

    @Value("${twitter.accessToken}")
    private final String ACCESS_TOKEN_SECRET = "seu_access_token_secret";

    @Bean
    public RequestInterceptor oauthRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                try {
                    // Gerar o cabeçalho Authorization OAuth 1.0a
                    OAuthHelper oAuthHelper = new OAuthHelper(
                            API_KEY,
                            API_SECRET,
                            ACCESS_TOKEN,
                            ACCESS_TOKEN_SECRET
                    );
                    String authorizationHeader = oAuthHelper.generateAuthorizationHeader(template.method(), template.url(), null);
                    // Adicionar o cabeçalho de autorização à requisição
                    template.header("Authorization", authorizationHeader);
                } catch (Exception e) {
                    throw new RuntimeException("Erro ao gerar cabeçalho OAuth", e);
                }
            }
        };
    }
}

