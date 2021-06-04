package com.business.Tests;

import org.testng.annotations.Test;

public class DependsOnMethods_test {


    @Test(dependsOnMethods = "logOut")
    public void logIn() {
        System.out.println("Dependant test running");
    }

    @Test
    public void logOut() {
        System.out.println("Priority test running ");
    }


}
