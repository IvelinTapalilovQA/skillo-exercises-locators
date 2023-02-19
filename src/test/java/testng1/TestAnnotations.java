package testng1;

import org.testng.annotations.*;

public class TestAnnotations {


    @BeforeSuite
    public void beforeSuiteAnnotation() {
        System.out.println("Before suite test annotation.");
        System.out.println();
    }
    @BeforeTest
    public void beforeTestAnnotation() {
        System.out.println("Before test annotation.");
        System.out.println();
    }
    @BeforeClass
    public void beforeClassAnnotation() {
        System.out.println("Before class annotation.");
        System.out.println();
    }
    @BeforeMethod
    public void beforeMethodAnnotation() {
        System.out.println("Before method annotation.");
        System.out.println();
    }
    @Test
    public void testName() {
        System.out.println("Test one implementation.");
        System.out.println();

        long id = Thread.currentThread().threadId();
        System.out.println(id);
    }
    @Test
    public void testName1() {
        System.out.println("Test two implementation.");
        System.out.println();

        long id = Thread.currentThread().threadId();
        System.out.println(id);
    }
    @AfterMethod
    public void afterMethodAnnotation(){
        System.out.println("After method annotation.");
        System.out.println();
    }
    @AfterClass
    public void afterClassAnnotation() {
        System.out.println("After class annotation.");
        System.out.println();
    }
    @AfterTest
    public void afterTestAnnotation() {
        System.out.println("After test annotation.");
        System.out.println();
    }
    @AfterSuite
    public void afterSuiteAnnotation() {
        System.out.println("After suite annotation.");
        System.out.println();
    }
}