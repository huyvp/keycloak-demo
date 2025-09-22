package com.profile.service;

import com.profile.dto.request.RegistrationRequest;
import com.profile.dto.response.ProfileResponse;

import java.util.List;

public interface IProfileService {
    ProfileResponse createProfile(RegistrationRequest request);

    ProfileResponse getProfile(String id);

    List<ProfileResponse> getProfiles();
}
