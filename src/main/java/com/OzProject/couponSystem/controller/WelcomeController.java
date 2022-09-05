package com.OzProject.couponSystem.controller;

import com.OzProject.couponSystem.dto.*;
import com.OzProject.couponSystem.exception.*;
import com.OzProject.couponSystem.security.*;
import com.OzProject.couponSystem.servies.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/welcome")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WelcomeController {

    private final LoginManager loginManager;
    private final TokenManager tokenManager;
    private final WelcomeService welcomeService;
    private final CustomerService customerService;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody CustomerDto registerReqDto) throws CouponSecurityException {
        customerService.register(registerReqDto);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@RequestBody LoginReqDto loginReqDto) throws CouponSecurityException {
        UUID token = welcomeService.login(loginReqDto.getEmail(), loginReqDto.getPassword(), loginReqDto.getClientType());
        ClientType clientType = tokenManager.getClientType(token);
        return new LoginResDto(tokenManager.getUserId(token), tokenManager.getInformation(token).getName(), loginReqDto.getEmail(), token, clientType);

    }

    @PutMapping("logout/{uuid}")
    public void logout(@RequestParam UUID token) {
        welcomeService.logOut(token);
    }

}


