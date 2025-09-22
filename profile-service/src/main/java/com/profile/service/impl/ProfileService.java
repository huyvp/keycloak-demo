package com.profile.service.impl;

import com.profile.dto.request.RegistrationRequest;
import com.profile.dto.response.ProfileResponse;
import com.profile.mapper.ProfileMapper;
import com.profile.repo.ProfileRepo;
import com.profile.service.IProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileService implements IProfileService {
    ProfileRepo profileRepo;
    ProfileMapper profileMapper;

    @Override
    public ProfileResponse createProfile(RegistrationRequest request) {
        var profile = profileMapper.toProfile(request);
        profile = profileRepo.save(profile);
        return profileMapper.toProfileResponse(profile);
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
