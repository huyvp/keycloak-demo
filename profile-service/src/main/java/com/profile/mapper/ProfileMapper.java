package com.profile.mapper;

import com.profile.dto.request.RegistrationRequest;
import com.profile.dto.response.ProfileResponse;
import com.profile.entity.Profile;
import org.mapstruct.Mapper;

@Mapper
public interface ProfileMapper {
    Profile toProfile(RegistrationRequest request);
    ProfileResponse toProfileResponse(Profile profile);
}
