package com.erp.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by klinster on 25.06.2017
 */
@Entity
@Table(name = "time_vocations")
public class TimeVocation implements Serializable {
    @Id
    @Column(name = "id_time_vocation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTimeVocation;

    //    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_worker")
    private Worker worker;

    @Column(name = "start_voc_date")
    private String startVocDate;

    @Column(name = "end_voc_date")
    private String endVocDate;

    @Column(name = "type")
    private String type;

    @Column(name = "is_confirmed")
    private Integer isConfirmed;

    public long getIdTimeVocation() {
        return idTimeVocation;
    }

    public void setIdTimeVocation(long idTimeVocation) {
        this.idTimeVocation = idTimeVocation;
    }

    public String getStartVocDate() {
        return startVocDate;
    }

    public void setStartVocDate(String startVocDate) {
        this.startVocDate = startVocDate;
    }

    public String getEndVocDate() {
        return endVocDate;
    }

    public void setEndVocDate(String endVocDate) {
        this.endVocDate = endVocDate;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Integer confirmed) {
        isConfirmed = confirmed;
    }
}
