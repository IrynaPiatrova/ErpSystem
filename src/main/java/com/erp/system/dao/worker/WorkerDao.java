package com.erp.system.dao.worker;

import java.util.List;

/**
 * Created by klinster on 25.06.2017.
 */
public interface WorkerDao<T> {
  //  public List<T> findAll();
    public List<T> getAll();
}
