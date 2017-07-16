package com.erp.system.dto;

import com.erp.system.entity.Worker;

import java.util.Date;

/**
 * Created by klinster on 16.07.2017
 */
public class ProjectTicketDTO {
    private long idProjectTicket;
    private String nameProjectTicket;
    private String specification;
    private String statusProjectTicket;
    private String perfomance;

    public ProjectTicketDTO(long idProjectTicket, String nameProjectTicket, String specification, String statusProjectTicket, String perfomance) {
        this.idProjectTicket = idProjectTicket;
        this.nameProjectTicket = nameProjectTicket;
        this.specification = specification;
        this.statusProjectTicket = statusProjectTicket;
        this.perfomance = perfomance;
    }

    public ProjectTicketDTO() {
    }

    public long getIdProjectTicket() {
        return idProjectTicket;
    }

    public void setIdProjectTicket(long idProjectTicket) {
        this.idProjectTicket = idProjectTicket;
    }

    public String getNameProjectTicket() {
        return nameProjectTicket;
    }

    public void setNameProjectTicket(String nameProjectTicket) {
        this.nameProjectTicket = nameProjectTicket;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getStatusProjectTicket() {
        return statusProjectTicket;
    }

    public void setStatusProjectTicket(String statusProjectTicket) {
        this.statusProjectTicket = statusProjectTicket;
    }

    public String getPerfomance() {
        return perfomance;
    }

    public void setPerfomance(String perfomance) {
        this.perfomance = perfomance;
    }
}
