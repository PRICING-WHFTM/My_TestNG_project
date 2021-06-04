package com.business.Utilities;

import com.business.Pages.HomePage;
import com.business.Pages.SalaryCalculator;

import java.net.MalformedURLException;

/**
 * 1. In Pages (utility)class in class level declare variable of application Page (Homepage, OrderPage etc) public .
 * 2. Initialize that variable inside constructor of Pages (utility) class
 * 3.In Base class in class level declare variable  of Pages(utility)class protected .
 * 4.In Base class inside before Method initialize the Pages(utility) variable.
 */

public class Pages{
    public HomePage homepage;
    public SalaryCalculator salaryCalculator;


    public Pages() throws MalformedURLException {
        homepage = new HomePage();
        salaryCalculator = new SalaryCalculator();
    }


}
