package utils.extend.reports;

import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

    static Map extentTestMap = new HashMap<String, ExtentTest>();

    public static synchronized ExtentTest getTest(String testName) {
        return (ExtentTest) extentTestMap.get(testName);
    }


    public static synchronized ExtentTest setTest(String testName, ExtentTest test) {
        extentTestMap.put(testName, test);
        return test;
    }
}
