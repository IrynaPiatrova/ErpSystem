package com.erp.system.dao.profile.impl;

import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.dao.worker.impl.WorkerDaoImpl;
import com.erp.system.entity.Profile;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by klinster on 25.06.2017.
 */
@Repository()
@Transactional
public class ProfileDaoImpl implements ProfileDao {

    protected static final Logger LOGGER = Logger.getLogger(WorkerDaoImpl.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * get Profile By Id
     * @param profileId
     * @return Profile
     */
    @Override
    public Profile getProfileById(long profileId) {
        LOGGER.info("ProfileDaoImpl getProfileById start");
        Profile profile = sessionFactory.getCurrentSession().get(Profile.class, profileId);
        LOGGER.info("ProfileDaoImpl getProfileById end");
        return profile;
    }
}