package com.gustavopinho.java.entities;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import java.util.List;

@RunWith(Arquillian.class)
public class CloudTest {
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(Cloud.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    public static final String[] LIST_CLOUDS = {
            "Cloud 01",
            "Cloud 02",
            "Cloud 03",
            "Cloud 04"
    };

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    @Before
    public void preparePersistenceTest() throws Exception {
        clearData();
        insertData();
        startTransaction();
    }

    private void clearData() throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Dumping old records...");
        em.createQuery("delete from Cloud").executeUpdate();
        utx.commit();
    }

    private void insertData() throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Inserting records...");
        for (String name : LIST_CLOUDS) {
            Cloud cloud = new Cloud();
            cloud.setName(name);
            em.persist(cloud);
        }
        utx.commit();
        em.clear();
    }

    private void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }

    @After
    public void commitTransaction() throws Exception {
        utx.commit();
    }

    @Test
    public void insertionTest() {
        List<Cloud> cloudList = em.createQuery("select e from Cloud e").getResultList();
        Assert.assertEquals(LIST_CLOUDS.length, cloudList.size());
    }

    @Test
    public void singleResultTest() {
        Cloud cloud = (Cloud) em.createQuery("select e from Cloud e where e.name = 'Cloud 01'").getSingleResult();
        Assert.assertEquals("Cloud 01", cloud.getName());
    }

    @Test
    public void deletionTest() {
        List<Cloud> cloudList = em.createQuery("select e from Cloud e").getResultList();
        for(Cloud cloud : cloudList) {
            em.remove(cloud);
        }
        em.flush();
        em.clear();

        cloudList = em.createQuery("select e from Cloud e").getResultList();
        Assert.assertNotEquals(LIST_CLOUDS.length, cloudList.size());
        Assert.assertEquals(0, cloudList.size());
    }
}
