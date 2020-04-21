package HW12_a;

import dto.User;

import java.util.Comparator;

public class AgeSort implements Comparator<User> {


    public int compare(User u1, User u2) {

        return u1.getAge() == u2.getAge() ? 0 :u1.getAge() > u2.getAge() ? 1 : -1;
    }




}
