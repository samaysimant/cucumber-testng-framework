package com.automationframework.stepdefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Hello world!
 *
 */
public class Hooks 
{
    @Before
    public void before() {
    	System.out.println("Initiate Driver");
    }
    
    @After
    public void after() {
    	System.out.println("Close Driver");
    }
}
