package lab.pkg8a.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lab8aSolution {

    private class ThreadedSort<T extends Comparable> extends Thread {

        private final List<T> list;

        public ThreadedSort(List<T> list) {
            this.list = list;
        }

        @Override
        public void run() {
            Collections.sort(list);
        }
    }

    public static void main(String[] args) {

        final int size = 1000000;

        ArrayList<Double> list1 = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list1.add(Math.random());
        }

        ArrayList<Double> list2 = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list2.add(Math.random());
        }

        ArrayList<Double> list3 = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list3.add(Math.random());
        }

        try {
            long time = new Lab8aSolution().sort(list1, list2, list3);
            long threadedTime = new Lab8aSolution().threadedTime(list1, list2, list3);         
            System.out.println("Sorting took " + time + " ms");
            System.out.println("Multi-Threaded Sorting took " + threadedTime + " ms");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public long sort(ArrayList<Double> list1, ArrayList<Double> list2,
            ArrayList<Double> list3) {

        long startTime = System.currentTimeMillis();
        Collections.sort(list1);
        Collections.sort(list2);
        Collections.sort(list3);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public long threadedTime(ArrayList<Double> list1, ArrayList<Double> list2,
            ArrayList<Double> list3) {
        
        ThreadedSort<Double> first = new ThreadedSort(list1);
        ThreadedSort<Double> second = new ThreadedSort(list2);
        ThreadedSort<Double> third = new ThreadedSort(list3);
        long startTime = System.currentTimeMillis();
        first.start();
        second.start();
        third.start();
        try {
            first.join();
            second.join();
            third.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
   
}
