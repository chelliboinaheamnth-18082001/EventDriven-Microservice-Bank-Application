package com.HBank.profile.service.impl;


import com.HBank.profile.constants.ProfileConstants;
import com.HBank.profile.dto.ProfileDto;
import com.HBank.profile.entity.Profile;
import com.example.hbankbom.event.AccountDataChangedEvent;
import com.example.hbankbom.event.CustomerDataChangedEvent;
import com.example.hbankbom.event.LoanDataChangedEvent;
import com.example.hbankbom.event.CardDataChangedEvent;
import com.HBank.profile.exception.ResourceNotFoundException;
import com.HBank.profile.mapper.ProfileMapper;
import com.HBank.profile.repository.ProfileRepository;
import com.HBank.profile.service.IProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements IProfileService {

    private ProfileRepository profileRepository;

    @Override
    public void handleCustomerDataChangedEvent(CustomerDataChangedEvent customerDataChangedEvent) {
        Profile profile =profileRepository.findByMobileNumberAndActiveSw(customerDataChangedEvent.getMobileNumber(),
                        ProfileConstants.ACTIVE_SW).orElseGet(Profile::new);
        profile.setMobileNumber(customerDataChangedEvent.getMobileNumber());
        if (customerDataChangedEvent.getName() != null) {
            profile.setName(customerDataChangedEvent.getName());
        }
        profile.setActiveSw(customerDataChangedEvent.isActiveSw());
        System.out.println(
                "Saving profile for mobileNumber: "
                        + customerDataChangedEvent.getMobileNumber()
        );


        profileRepository.save(profile);
    }

    @Override
    public void handleAccountDataChangedEvent(AccountDataChangedEvent accountDataChangedEvent) {
        Profile profile = profileRepository.findByMobileNumberAndActiveSw(accountDataChangedEvent.getMobileNumber(),
                        ProfileConstants.ACTIVE_SW)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "mobileNumber", accountDataChangedEvent.getMobileNumber()));
        profile.setAccountNumber(accountDataChangedEvent.getAccountNumber());
        profileRepository.save(profile);
    }

    @Override
    public void handleLoanDataChangedEvent(LoanDataChangedEvent loanDataChangedEvent) {
        Profile profile = profileRepository.findByMobileNumberAndActiveSw(loanDataChangedEvent.getMobileNumber(),
                        ProfileConstants.ACTIVE_SW)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "mobileNumber", loanDataChangedEvent.getMobileNumber()));
        profile.setLoanNumber(loanDataChangedEvent.getLoanNumber());
        profileRepository.save(profile);
    }

    @Override

    public void handleCardDataChangedEvent(CardDataChangedEvent customerDataChangedEvent) {
        Profile profile = profileRepository.findByMobileNumberAndActiveSw(customerDataChangedEvent.getMobileNumber(),
                        ProfileConstants.ACTIVE_SW)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "mobileNumber", customerDataChangedEvent.getMobileNumber()));
        profile.setCardNumber(customerDataChangedEvent.getCardNumber());
        profileRepository.save(profile);
    }

    @Override
    public ProfileDto fetchProfile(String mobileNumber) {
        Profile profile = profileRepository.findByMobileNumberAndActiveSw(mobileNumber, true).orElseThrow(
                () -> new ResourceNotFoundException("Profile", "mobileNumber", mobileNumber)
        );
        ProfileDto profileDto = ProfileMapper.mapToProfileDto(profile, new ProfileDto());
        return profileDto;
    }

}
