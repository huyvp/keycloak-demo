package com.profile.repo;

import com.profile.entity.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepo extends MongoRepository<Profile, String> {
    Optional<Profile> findByUserId(String id);
}
