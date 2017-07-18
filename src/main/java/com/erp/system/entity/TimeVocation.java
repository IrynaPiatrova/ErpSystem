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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTimeVocation;

    //    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_worker")
    private Worker worker;

    @Column(name = "start_voc_date")
    private String startVocDate;

    @Column(name = "end_voc_date")
    private Date endVocDate;

    public long getIdTimeVocation() {
        return idTimeVocation;
    }

    public void setIdTimeVocation(long idTimeVocation) {
        this.idTimeVocation = idTimeVocation;
    }

    public Worker getIdWorker() {
        return worker;
    }

    public void setIdWorker(Worker idWorker) {
        this.worker = idWorker;
    }

    public String getStartVocDate() {
        return startVocDate;
    }

    public void setStartVocDate(String startVocDate) {
        this.startVocDate = startVocDate;
    }

    public Date getEndVocDate() {
        return endVocDate;
    }

    public void setEndVocDate(Date endVocDate) {
        this.endVocDate = endVocDate;
    }
}
