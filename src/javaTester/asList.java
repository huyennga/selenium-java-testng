package javaTester;

import java.util.Arrays;
import java.util.List;

public class asList {

    public static void main(String[] args) {
//        Integer [] ia = {1,2,3,4};
//        System.out.println("Array : "+Arrays.toString(ia));
//        List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(ia));  // new ArrayList object is created , no connection between existing Array Object
//        list1.add(5);
//        list1.add(6);
//        list1.remove(0);
//        list1.remove(0);
//        System.out.println("list1: " + list1);
//        System.out.println("Array: " + Arrays.toString(ia));

        Integer [] ia = {1,2,3,4};
        System.out.println("Array: " + Arrays.toString(ia));
        List<Integer> list2 = Arrays.asList(ia); // Creates only a (new) List reference to the existing Array object (and NOT a new ArrayList Object)
//        list2.add(5); // It will throw java.lang.UnsupportedOperationException - invalid operation (as Array size is fixed)
        list2.set(0,10);  // Making changes in the existing Array object using the List reference - valid
        list2.set(1,11);
        ia[2]=12;     // Making changes in the existing Array object using the Array reference - valid
        System.out.println("list2: " + list2);
        System.out.println("Array: " + Arrays.toString(ia));

    }


}
