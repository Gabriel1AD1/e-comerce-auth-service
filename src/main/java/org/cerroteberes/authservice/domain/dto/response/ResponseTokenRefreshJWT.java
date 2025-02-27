package org.cerroteberes.authservice.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTokenRefreshJWT {
    @JsonProperty("token")
    private String token;
    @JsonProperty("expiration")
    private Long milliseconds;
}
