package com.poly.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(
        name = "Favorites",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"userId", "productId"})
        }
)
public class Favorites implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favoriteId")
    private Long favoriteId;

    // ===== Many Favorites -> One User =====
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    // ===== Many Favorites -> One Product =====
    @ManyToOne
    @JoinColumn(name = "productId")
    private Products product;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "likeDate")
    private Date likeDate;

    // ===== Constructor =====
    public Favorites() {
    }

    // ===== Getter & Setter =====
    public Long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
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

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }
}
