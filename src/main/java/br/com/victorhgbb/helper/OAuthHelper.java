package br.com.victorhgbb.helper;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class OAuthHelper {

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private final String apiKey;
    private final String apiSecret;
    private final String accessToken;
    private final String accessTokenSecret;

    public OAuthHelper(String apiKey, String apiSecret, String accessToken, String accessTokenSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
    }

    public String generateAuthorizationHeader(String method, String url, Map<String, String> additionalParams) throws Exception {
        // Parâmetros OAuth básicos
        Map<String, String> oauthParams = new HashMap<>();
        oauthParams.put("oauth_consumer_key", apiKey);
        oauthParams.put("oauth_nonce", UUID.randomUUID().toString());
        oauthParams.put("oauth_signature_method", "HMAC-SHA1");
        oauthParams.put("oauth_timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        oauthParams.put("oauth_token", accessToken);
        oauthParams.put("oauth_version", "1.0");

        // Mesclar parâmetros adicionais
        if (additionalParams != null) {
            oauthParams.putAll(additionalParams);
        }

        // Criar a string base para assinatura
        String signatureBaseString = createSignatureBaseString(method, url, oauthParams);

        // Gerar a assinatura
        String signature = generateSignature(signatureBaseString, apiSecret, accessTokenSecret);
        oauthParams.put("oauth_signature", signature);

        // Montar o cabeçalho Authorization
        return buildAuthorizationHeader(oauthParams);
    }

    private String createSignatureBaseString(String method, String url, Map<String, String> params) throws Exception {
        // Ordenar os parâmetros alfabeticamente
        Map<String, String> sortedParams = new TreeMap<>(params);

        // Codificar os parâmetros
        StringBuilder encodedParams = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            if (encodedParams.length() > 0) {
                encodedParams.append("&");
            }
            encodedParams.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }

        // Montar a string base
        return method.toUpperCase() + "&" +
                URLEncoder.encode(url, StandardCharsets.UTF_8) + "&" +
                URLEncoder.encode(encodedParams.toString(), StandardCharsets.UTF_8);
    }

    private String generateSignature(String baseString, String apiSecret, String tokenSecret) throws Exception {
        String key = URLEncoder.encode(apiSecret, StandardCharsets.UTF_8) + "&" +
                (tokenSecret != null ? URLEncoder.encode(tokenSecret, StandardCharsets.UTF_8) : "");
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(baseString.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeBase64String(rawHmac).trim();
    }

    private String buildAuthorizationHeader(Map<String, String> oauthParams) {
        StringBuilder header = new StringBuilder("OAuth ");
        for (Map.Entry<String, String> entry : oauthParams.entrySet()) {
            if (header.length() > 6) {
                header.append(", ");
            }
            header.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                    .append("=\"")
                    .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                    .append("\"");
        }
        return header.toString();
    }
}

