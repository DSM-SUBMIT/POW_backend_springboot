package com.submitproject.POW_backend_springboot.service.account;

import com.submitproject.POW_backend_springboot.dto.AccountDto.PasswordRequest;
import com.submitproject.POW_backend_springboot.entity.club.Club;
import com.submitproject.POW_backend_springboot.entity.club.ClubRepository;
import com.submitproject.POW_backend_springboot.exception.ClubNotFoundException;
import com.submitproject.POW_backend_springboot.exception.NotLoggedInException;
import com.submitproject.POW_backend_springboot.exception.PasswordIncorrectException;
import com.submitproject.POW_backend_springboot.security.jwt.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final ClubRepository clubRepository;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public void deleteAccount() {
        if(!authenticationFacade.isLogin()) {
            throw new NotLoggedInException();
        }

        Club club = clubRepository.findById(Integer.parseInt(authenticationFacade.getClubId()))
                .orElseThrow(ClubNotFoundException::new);

        clubRepository.delete(club);
    }

    @Override
    public void updatePassword(PasswordRequest request) {
        Club club = clubRepository.findById(Integer.parseInt(authenticationFacade.getClubId()))
                .orElseThrow(ClubNotFoundException::new);

        if(club.getPassword().equals(request.getExistingPassword())) {
            club.updatePassword(request.getNewPassword());
            clubRepository.save(club);
        }
        else {
            throw new PasswordIncorrectException();
        }


    }

}
