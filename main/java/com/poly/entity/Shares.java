package com.poly.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Shares")
public class Shares implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shareId")
    private Long shareId;

    // ===== Many Shares -> One User =====
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    // ===== Many Shares -> One Product =====
    @ManyToOne
    @JoinColumn(name = "productId")
    private Products product;

    @Column(name = "emails")
    private String emails;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "shareDate")
    private Date shareDate;

    // ===== Constructor =====
    public Shares() {
    }

    // ===== Getter & Setter =====
    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Date getShareDate() {
        return shareDate;
    }

    public void setShareDate(Date shareDate) {
        this.shareDate = shareDate;
    }
}
