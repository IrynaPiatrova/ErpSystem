package com.erp.system.dao.profile.impl;

import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.dao.worker.impl.WorkerDaoImpl;
import com.erp.system.entity.Profile;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by klinster on 25.06.2017.
 */
@Component
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

    @Override
    public void createProfile(Profile profile) {
        LOGGER.info("ProfileDaoImpl createProfile start");
        sessionFactory.getCurrentSession().save(profile);
        LOGGER.info("ProfileDaoImpl createProfile end");
    }


}
