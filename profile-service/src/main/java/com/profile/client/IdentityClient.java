package com.profile.client;

import com.profile.dto.request.TokenExchangeParam;
import com.profile.dto.request.UserCreationParam;
import com.profile.dto.response.ExchangeTokenResponse;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "identity-client", url = "${idp.url}")
public interface IdentityClient {
    @PostMapping(value = "realms/web-app/protocol/openid-connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ExchangeTokenResponse exchangeClientToken(@QueryMap TokenExchangeParam tokenExchangeParam);

    @PostMapping(value = "admin/realms/web-app/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createUser(@RequestHeader("Authorization") String token, @RequestBody UserCreationParam userCreationParam);
}
