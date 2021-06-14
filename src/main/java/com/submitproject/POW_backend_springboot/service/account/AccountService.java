package com.submitproject.POW_backend_springboot.service.account;

import com.submitproject.POW_backend_springboot.dto.AccountDto.PasswordRequest;

public interface AccountService {
    void deleteAccount();
    void updatePassword(PasswordRequest request);
}
