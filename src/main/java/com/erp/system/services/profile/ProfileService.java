package com.erp.system.services.profile;

import com.erp.system.entity.Profile;

/**
 * Created by klinster on 05.07.2017
 */
public interface ProfileService {
    void createProfile(Profile profile);

    Profile getProfileById(long profileId);

    void updateProfile(Profile profile);
}
