package com.profile.controller;

import com.profile.dto.request.RegistrationRequest;
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
    ProfileResponse register(@RequestBody RegistrationRequest request) {
        log.info("Creating user profile : {}", request);
        return profileService.createProfile(request);
    }

    @GetMapping("/{id}")
    ProfileResponse getUserProfile(@PathVariable("id") String id) {
        return profileService.getProfile(id);
    }

    @GetMapping("/profiles")
    List<ProfileResponse> getUserProfiles() {
        return profileService.getProfiles();
    }
}
