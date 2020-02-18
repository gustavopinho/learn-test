package com.gustavopinho.java.example;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ExampleTest {
    @Deployment
    public static Archive deploy(){
        return ShrinkWrap.create(WebArchive.class, "test.war");
    }

    @Test
    public void passTest(){
        Assert.assertTrue(true);
    }

    @Test
    public void failTest() {
        Assert.assertTrue(true);
    }
}
