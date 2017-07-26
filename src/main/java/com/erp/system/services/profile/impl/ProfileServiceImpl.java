package com.erp.system.services.profile.impl;

import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.entity.Profile;
import com.erp.system.services.profile.ProfileService;
import com.erp.system.services.project.ticket.ProjectTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by klinster on 05.07.2017
 */
@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    ProfileDao profileDao;

    @Override
    @Transactional
    public void createProfile(Profile profile) {
        profileDao.createProfile(profile);
    }

    @Override
    @Transactional
    public Profile getProfileById(long profileId) {
        return profileDao.getProfileById(profileId);
    }

    @Override
    @Transactional
    public void updateProfile(Profile profile) {
        profileDao.updateProfile(profile);
    }
}
