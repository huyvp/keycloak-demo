package com.profile.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class CustomAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final static String REALM_ACCESS = "realm_access";
    private final static String ROLE_PREFIX = "ROLE_";
    private final static String ROLES = "roles";

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, Object> realmAccessMap = source.getClaimAsMap(REALM_ACCESS);
        log.info("realmAccessMap: {}", realmAccessMap);
        Object roles = realmAccessMap.get(ROLES);

        if (roles instanceof List stringRoles) {
            List<GrantedAuthority> stringList =  ((List<String>) stringRoles)
                    .stream()
                    .map(s -> new SimpleGrantedAuthority(String.format("%s%s", ROLE_PREFIX, s)))
                    .collect(Collectors.toList());
            log.info("stringList: {}", stringList);
            return stringList;
        }
        return List.of();
    }
}
