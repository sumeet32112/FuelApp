package com.noxfilers.fuelApp.entities;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "common_details")
public class CommonDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "created_by", length = 35, nullable = true)
    private String createdBy = " Owner " ;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_by", length = 35)
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name="last_modified_date")
    private Date lastModifiedDate;

    @Version
    @Column(name = "version",nullable = false)
    private Long version =1L;
}
