package com.epicode.Spring.security.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PayPalAccessTokenDto {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("app_id")
    private String applicationId;
    @JsonProperty("expires_in")
    private int expiresIn;
    private String nonce;
}
