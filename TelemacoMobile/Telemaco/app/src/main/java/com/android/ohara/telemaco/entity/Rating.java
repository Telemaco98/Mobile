package com.android.ohara.telemaco.entity;

import java.util.Date;

/**
 *
 * @author  Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 29/06/2018
 */
public class Rating {

    private int id;
    private String comment;
    private int stars;
    private Date date;
    private User user;
    private int idSerie;

    public Rating() { }

    public Rating (int id, Date date , int stars, String comment, User user, int idSerie) {
        this.id = id;
        this.date = date;
        this.stars = stars;
        this.comment = comment;
        this.user = user;
        this.idSerie = idSerie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    @Override
    public String toString() {
        return "Avaliação{" + "Comentario=" + comment + "estrelas" + stars + '}';
    }
}
