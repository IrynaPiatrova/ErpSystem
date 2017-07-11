package com.erp.system.dao.profile;

import com.erp.system.entity.Profile;

import java.util.List;

/**
 * Created by klinster on 25.06.2017.
 */
public interface ProfileDao {

    void createProfile(Profile profile);

    Profile getProfileById(long profileId);

    void updateProfile(Profile profile);

    boolean isEmailUnique(String profileEmail);

    boolean isTelephoneUnique(String profileTelephone);
}
