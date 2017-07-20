package com.erp.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by klinster on 25.06.2017
 */
@Entity
@Table(name = "work_log")
public class WorkLog implements Serializable {
    @Id
    @Column(name = "id_work_log")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idWorkLog;

    //    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_project_ticket")
    private ProjectTicket ProjectTicket;

    //    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_worker")
    private Worker Worker;

    @Column(name = "start_log_date")
    private String startLogDate;

    @Column(name = "spent_time")
    private String spentTime;

    public long getIdWorkLog() {
        return idWorkLog;
    }

    public void setIdWorkLog(long idWorkLog) {
        this.idWorkLog = idWorkLog;
    }

    public ProjectTicket getProjectTicket() {
        return ProjectTicket;
    }

    public void setProjectTicket(ProjectTicket idProjectTicket) {
        this.ProjectTicket = idProjectTicket;
    }

    public Worker getWorker() {
        return Worker;
    }

    public void setWorker(Worker idWorker) {
        this.Worker = idWorker;
    }

    public String getStartLogDate() {
        return startLogDate;
    }

    public void setStartLogDate(String startLogDate) {
        this.startLogDate = startLogDate;
    }

    public String getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(String spentTime) {
        this.spentTime = spentTime;
    }
}
