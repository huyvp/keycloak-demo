package com.profile.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.profile.dto.request.KeyCloakError;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class KeyCloakErrorNormalizer {
    private final ObjectMapper objectMapper;
    private final Map<String, ErrorCode> errorCodeMap;

    public KeyCloakErrorNormalizer() {
        objectMapper = new ObjectMapper();
        errorCodeMap = new HashMap<>();
        errorCodeMap.put("User exists with same username", ErrorCode.USER_EXISTED);
        errorCodeMap.put("User exists with same email", ErrorCode.EMAIL_EXISTED);
        errorCodeMap.put("User name is missing", ErrorCode.USERNAME_IS_MISSING);
    }

    public ServiceException handleException(FeignException exception) {
        log.error("Exception: {}", exception.getMessage());
        try {
            var error = objectMapper.readValue(exception.contentUTF8(), KeyCloakError.class);
            log.info(error.getErrorMessage());
            if (Objects.nonNull(error.getErrorMessage()) && Objects.nonNull(errorCodeMap.get(error.getErrorMessage()))) {
                return new ServiceException(errorCodeMap.get(error.getErrorMessage()));
            }
        } catch (JsonProcessingException e) {
            log.info("Caught exception while processing KeyCloakError");
        }

        return new ServiceException(ErrorCode.UNCATEGORIZED_EXCEPTION);
    }
}
