package testng;

import org.testng.annotations.*;

public class Topic_01_Annotation {
    @BeforeSuite
    public static void m1() {
        System.out.println("Using @BeforeSuite  ");
    }

    @BeforeTest
    public static void m2() {
        System.out.println("Using @BeforeTest , executed before all test cases ");
    }

    @BeforeClass
    public static void m3() {
        System.out.println("Using @BeforeClass , executed before all test cases ");
    }

    @BeforeMethod
    public static void m4() {
        System.out.println("@BeforeMethod, executed ....");
    }

    @Test
    public static void m5() {
        System.out.println("@Test, executed test case ....");
    }

    @AfterMethod
    public static void m6() {
        System.out.println("@AfterMethod, executed ....");
    }

    @AfterClass
    public static void m7() {
        System.out.println("Using @AfterClass ,executed after all test cases");
    }

    @AfterTest
    public static void m8() {
        System.out.println("Using @AfterTest ,..");
    }

    @AfterSuite
    public static void m9() {
        System.out.println("Using @AfterSuite ,..");
    }

}
