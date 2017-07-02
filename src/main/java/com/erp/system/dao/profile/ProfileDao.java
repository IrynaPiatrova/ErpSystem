package com.erp.system.dao.profile;

import com.erp.system.entity.Profile;

/**
 * Created by klinster on 25.06.2017.
 */
public interface ProfileDao {
    Profile getProfileById(long profileId);
}
