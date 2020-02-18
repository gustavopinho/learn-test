package com.gustavopinho.java.beans;

import com.gustavopinho.java.entities.Cloud;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.io.Serializable;

@Named("cloudBean")
@ViewScoped
public class CloudBean implements Serializable {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    private String hello;

    @PostConstruct
    public void initBean() {
        try {
            hello = "Hello";

            utx.begin();
            Cloud cloud = new Cloud();
            cloud.setName("Cloud 01");
            em.persist(cloud);
            utx.commit();
            em.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
