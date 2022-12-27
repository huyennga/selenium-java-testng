package testng;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;

public class Topic_02_Assert {
    ArrayList<String> list ;

    public void m5() {
        list.add("test");
        assertEquals(1, list.size());
    }


}
