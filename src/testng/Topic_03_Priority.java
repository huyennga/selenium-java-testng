package testng;

import org.testng.annotations.Test;

public class Topic_03_Priority {

    @Test(priority = 2, enabled = true, description = "test priority 02")
    public void Test_01() {
        System.out.println("priority = 2");
    }

    @Test(priority = 1, enabled = true, description = "test priority 01")
    public void Test_02() {
        System.out.println("priority = 1");
    }

    @Test(priority = 3, enabled = false)
    public void Test_03() {
        System.out.println("priority = 3");
    }

}
