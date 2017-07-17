package com.erp.system.dao.profile.impl;

import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.entity.Profile;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by klinster on 25.06.2017
 */
@Component
@Repository()
@Transactional
public class ProfileDaoImpl implements ProfileDao {

    protected static final Logger LOGGER = Logger.getLogger(ProfileDaoImpl.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * get Profile By Id
     *
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

    @Override
    public void updateProfile(Profile profile) {
        LOGGER.info("ProfileDaoImpl updateProfile start");
        sessionFactory.getCurrentSession().update(profile);
        LOGGER.info("ProfileDaoImpl updateProfile end");
    }

    @Override
    public void createProfile(Profile profile) {
        LOGGER.info("ProfileDaoImpl createProfile start");
        sessionFactory.getCurrentSession().save(profile);
        LOGGER.info("ProfileDaoImpl createProfile end");
    }

    @Override
    public boolean isEmailUnique(String profileEmail) {
        LOGGER.info("ProfileDaoImpl getEmails start");
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Profile WHERE email = :email");
        query.setParameter("email", profileEmail);
        LOGGER.info("ProfileDaoImpl getEmails end");
        return query.getResultList().size() != 0;
    }

    @Override
    public boolean isTelephoneUnique(String profileTelephone) {
        LOGGER.info("ProfileDaoImpl getTelephones start");
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Profile WHERE telephone = :telephone");
        query.setParameter("telephone", profileTelephone);
        LOGGER.info("ProfileDaoImpl getTelephones end");
        return query.getResultList().size() != 0;
    }
}
