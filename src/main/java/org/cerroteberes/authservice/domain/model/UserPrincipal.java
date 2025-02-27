package org.cerroteberes.authservice.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cerroteberes.authservice.domain.entity.enums.NameRole;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPrincipal {
    @JsonProperty("user_id")
    private Long USERID;

    @JsonProperty("password_encoded")
    private String passwordEncoded;

    @JsonProperty("roles")
    private List<NameRole> roles;

    // funcion para obtener los roles como String
    public List<String> getRolesAsString() {
        return roles.stream()
                .map(NameRole::name) // Convierte cada NameRole a su nombre en String
                .toList();
    }
}
