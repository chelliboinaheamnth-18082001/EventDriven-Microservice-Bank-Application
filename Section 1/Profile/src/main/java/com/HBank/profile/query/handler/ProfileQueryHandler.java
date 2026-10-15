package com.HBank.profile.query.handler;



import com.HBank.profile.dto.ProfileDto;
import com.HBank.profile.query.FindProfileQuery;
import com.HBank.profile.service.IProfileService;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileQueryHandler {

    private final IProfileService iProfileService;

    @QueryHandler
    public ProfileDto findProfile(FindProfileQuery findProfileQuery) {
        return iProfileService.fetchProfile(findProfileQuery.getMobileNumber());
    }

}
