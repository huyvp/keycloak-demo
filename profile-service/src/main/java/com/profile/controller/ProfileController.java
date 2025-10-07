package com.profile.controller;

import com.profile.dto.request.RegistrationRequest;
import com.profile.dto.response.CommonResponse;
import com.profile.dto.response.ProfileResponse;
import com.profile.service.IProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProfileController {
    IProfileService profileService;

    @PostMapping("/register")
    CommonResponse<ProfileResponse> register(@RequestBody RegistrationRequest request) {
        log.info("Creating user profile : {}", request);
        return CommonResponse.<ProfileResponse>builder()
                .result(profileService.createProfile(request))
                .build();
    }

    @GetMapping("/{id}")
    CommonResponse<ProfileResponse>  getUserProfile(@PathVariable("id") String id) {
        return CommonResponse.<ProfileResponse>builder()
                .result(profileService.getProfile(id))
                .build();
    }

    @GetMapping("/profiles")
    CommonResponse<List<ProfileResponse>> getUserProfiles() {
        return CommonResponse.<List<ProfileResponse>>builder()
                .result(profileService.getProfiles())
                .build();
    }
}
