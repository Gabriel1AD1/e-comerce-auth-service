package org.cerroteberes.authservice.infra.client.feing;

import jakarta.validation.Valid;
import org.cerroteberes.authservice.domain.dto.request.RequestRegisterUserDTO;
import org.cerroteberes.authservice.domain.entity.enums.TypeUserSignup;
import org.cerroteberes.authservice.domain.model.UserPrincipal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "userClient", url = "${user.client.url}")
public interface UserClient {
    @GetMapping("/")
    UserPrincipal getUserByEmail(@RequestParam("email") String email);

    @GetMapping("/test")
    String confirm();

    @PostMapping("/{type-user}")
    void registerUser(@RequestBody @Valid RequestRegisterUserDTO requestRegisterUserDT, @PathVariable("type-user") TypeUserSignup typeUser);

    @GetMapping("/{id}")
    UserPrincipal getUserById(@PathVariable("id") Long userId);
}
