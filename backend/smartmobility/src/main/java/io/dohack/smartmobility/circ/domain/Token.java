package io.dohack.smartmobility.circ.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {
    String access_token;
    int expires_in;
    String token_type;

    public Token() {

    }

    public String getAccessToken(){
        return access_token;
    }

}


