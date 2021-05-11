package com.webservice.base;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

public class APITestCase {

    public static  String API_ROOT = "";


    @BeforeClass
    public void beforeClass(ITestContext context) {
        this.API_ROOT = context.getCurrentXmlTest().getParameter("BaseURI");

    }
}
