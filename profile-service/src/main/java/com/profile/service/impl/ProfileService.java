package com.profile.service.impl;

import com.profile.client.IdentityClient;
import com.profile.dto.request.Credential;
import com.profile.dto.request.RegistrationRequest;
import com.profile.dto.request.TokenExchangeParam;
import com.profile.dto.request.UserCreationParam;
import com.profile.dto.response.ProfileResponse;
import com.profile.exception.KeyCloakErrorNormalizer;
import com.profile.mapper.ProfileMapper;
import com.profile.repo.ProfileRepo;
import com.profile.service.IProfileService;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileService implements IProfileService {
    ProfileRepo profileRepo;
    ProfileMapper profileMapper;
    IdentityClient identityClient;
    KeyCloakErrorNormalizer keyCloakErrorNormalizer;

    @Value("${idp.client-id}")
    @NonFinal
    String clientId;

    @Value("${idp.client-secret}")
    @NonFinal
    String clientSecret;

    @Override
    public ProfileResponse createProfile(RegistrationRequest request) {
        // 1. Create account in keycloak
        try {
            // 1.1 Exchange client token
            var token = identityClient.exchangeClientToken(TokenExchangeParam.builder()
                    .grant_type("client_credentials")
                    .client_id(clientId)
                    .client_secret(clientSecret)
                    .scope("openid")
                    .build());

            log.info("client token: {}", token);
            // 1.2 Create user with client token and given info

            var response = identityClient.createUser(
                    "Bearer " + token.getAccessToken(),
                    UserCreationParam.builder()
                            .username(request.getUsername())
                            .firstName(request.getFirstName())
                            .lastName(request.getLastName())
                            .email(request.getEmail())
                            .enabled(true)
                            .emailVerified(false)
                            .credentials(
                                    List.of(Credential.builder()
                                            .type("password")
                                            .value(request.getPassword())
                                            .temporary(false)
                                            .build())
                            )
                            .build());

            // 1.3 Get userId of keycloak account
            String userId = extractUserId(response);
            log.info("userId: {}", userId);

            var profile = profileMapper.toProfile(request);
            profile.setUserId(userId);
            profile = profileRepo.save(profile);
            return profileMapper.toProfileResponse(profile);
        } catch (FeignException exception) {
            throw keyCloakErrorNormalizer.handleException(exception);
        }
    }

    private String extractUserId(ResponseEntity<?> response) {
        String location = response.getHeaders().getFirst("Location");
        String[] split = location.split("/");
        return split[split.length - 1];
    }

    @Override
    public ProfileResponse getProfile(String id) {
        var profile = profileRepo.findById(id).orElse(null);
        return profileMapper.toProfileResponse(profile);
    }

    @Override
    public List<ProfileResponse> getProfiles() {
        var profiles = profileRepo.findAll();
        return profiles.stream().map(profileMapper::toProfileResponse).toList();
    }
}
