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
public class ResponseJWT {
    @JsonProperty("token")
    private String token;
    @JsonProperty("expiration")
    private Long milliseconds;
    @JsonProperty("refresh_token")
    private String refreshToken;
}
