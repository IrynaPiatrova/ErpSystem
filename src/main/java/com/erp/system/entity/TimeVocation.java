package com.erp.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by klinster on 25.06.2017.
 */
@Entity
@Table(name = "time_vocations")
public class TimeVocation implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTimeVocation;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_worker")
    private Worker idWorker;

    @Column(name = "start_voc_date")
    private Date startVocDate;

    @Column(name = "end_voc_date")
    private Date endVocDate;

    public long getIdTimeVocation() {
        return idTimeVocation;
    }

    public void setIdTimeVocation(long idTimeVocation) {
        this.idTimeVocation = idTimeVocation;
    }

    public Worker getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(Worker idWorker) {
        this.idWorker = idWorker;
    }

    public Date getStartVocDate() {
        return startVocDate;
    }

    public void setStartVocDate(Date startVocDate) {
        this.startVocDate = startVocDate;
    }

    public Date getEndVocDate() {
        return endVocDate;
    }

    public void setEndVocDate(Date endVocDate) {
        this.endVocDate = endVocDate;
    }
}
