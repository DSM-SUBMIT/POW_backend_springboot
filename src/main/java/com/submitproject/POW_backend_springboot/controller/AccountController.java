package com.submitproject.POW_backend_springboot.controller;

import com.submitproject.POW_backend_springboot.dto.AccountDto.*;
import com.submitproject.POW_backend_springboot.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/account")
@RestController
public class AccountController {

    private final AccountService accountService;

    @DeleteMapping
    public void deleteAccount() {
        accountService.deleteAccount();
    }

    @PutMapping // PUT은 리소스의 모든 것을 업데이트하며, 보내지지 않은 값은 null로 채운다. PATCH는 기존의 데이터를 유지하는 방식으로 한다.
    @ResponseStatus(HttpStatus.NO_CONTENT) // 보통 수정이 성공하면 204를 보내준다.
    public void updatePassword(@RequestBody @Valid PasswordRequest request) { //Validated는 그룹화해서 유효성을 검사할 때 사용한다. 보통은 @Valid 사용하기
        accountService.updatePassword(request);
    }

}
