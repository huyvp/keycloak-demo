package com.profile.service;

import com.profile.dto.request.RegistrationRequest;
import com.profile.dto.response.ProfileResponse;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface IProfileService {
    ProfileResponse createProfile(RegistrationRequest request);

    ProfileResponse getMyProfile();

    @PreAuthorize("hasRole('ADMIN')")
    List<ProfileResponse> getProfiles();
}
