package com.pe.HeoComisiones.Auditable;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
    @CreatedBy
    private String creadopor;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @LastModifiedBy
    protected String ultimaactualizacionpor;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fechaultimaactualizacion;
}
