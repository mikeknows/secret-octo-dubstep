/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author plymire
 */
public class Project1 {
    public static List<Media> list = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        String fileName = "file.bin";
        File file = new File(fileName);
        try (FileInputStream fis = new FileInputStream(file)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object tmp = ois.readObject();
            if (tmp instanceof List) {
                list.addAll((List<Media>) tmp);
            }
        } catch (Exception e) {
            System.out.println("Failed to read file");
        }

        while (true) {
            System.out.println("1. Insert new media item");
            System.out.println("2. Mark an item as on loan");
            System.out.println("3. Mark an item as returned");
            System.out.println("4. List the items");
            System.out.println("5. Remove a media item");
            System.out.println("6. Quit\n");
            while (true) {

                switch (getOption()) {
                    case "1": {
                        insertItem(list);
                        break;
                    }
                    case "2": {
                        markItemLoan(list);
                        break;
                    }

                    case "3": {
                        markReturn(list);
                        break;
                    }
                    case "4": {
                        listItems(list);
                        break;
                    }
                    case "5": {
                        removeItem(list);
                        break;
                    }
                    case "6": {
                        quitAndSave(list);
                        break;
                    }
                    default:
                        break;
                }
                break;
            }
        }
    }

    public static boolean add(String title, String format, List<Media> list) {
        if (mediaFinder(title, list)) {
            return false;
        } else {
            list.add(new Media(title, format));
            return true;
        }

    }

    public static void insertItem(List<Media> list) {
        Scanner sc = new Scanner(System.in);
        String title, format;

        System.out.println("What is the title?: ");
        title = sc.nextLine();
        System.out.println("What is the format?: ");
        format = sc.nextLine();

        while (!add(title, format, list)) {
           System.out.println("Already in list.");
            System.out.print("What is the title?: ");
            title = sc.nextLine();
}

    }

    public static void markItemLoan(List<Media> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter title to mark on loan");
        String loanedtitle;
        loanedtitle = sc.nextLine();
        boolean found = false;
        for (Media x : list) {

            if (x.getTitle().equals(loanedtitle)) {
                found = true;
                if (x.getBuyerdata().notLoaned()) {
                     System.out.print("Enter renter name: ");
                    String buyername = sc.nextLine();
                    System.out.print("Enter date loaned: ");
                    String loandate = sc.nextLine();
                    x.setBuyerdata(new Buyer(loandate, buyername));

                } else {
                    System.out.println("Already loaned");
                    break;
                }
            }
        }
        if (found == false) {
            System.out.println("Item on loan or doesn't exist");
        }

    }

    public static void markReturn(List<Media> list) {

        Scanner sc = new Scanner(System.in);
        boolean found = false;
        System.out.print("Enter title of returned item: ");
        String returnedtitle;
        returnedtitle = sc.nextLine();
        for (Media x : list) {
            if (x.getTitle().equals(returnedtitle)) {
                found = true;
                if (x.getBuyerdata().notLoaned() == false) {
                    x.getBuyerdata().setReturn();
                    System.out.println("Item returned");
                } else {
                    System.out.println("Not Loaned");
                    break;
                }
            }

        }
        if (found == false) {
            System.out.println("Item doesn't exist");

        }

    }

    public static void listItems(List<Media> list) {
        Collections.sort(list);
        for (Media x : list) {
            if (x.getBuyerdata().notLoaned()) {
                System.out.printf("Media Title:%s\tMedia Format:%s\n", x.getTitle(), x.getFormat());

            } else {
               System.out.printf("Media Title:%s\tMedia Format:%s\t (Renter:%s\tLoan Date:%s)\n", x.getTitle(), x.getFormat(), x.getBuyerdata().getName(), x.getBuyerdata().getDate());
            }
           

        }
         System.out.println();
    }

    public static boolean remove(String title, List<Media> list) {
        if (!mediaFinder(title, list)) {
            return false;
        }
        for (Media x : list) {
            if (x.getTitle().equals(title)) {
                list.remove(x);
                return true;
            }
        }
        return false;

    }

    public static void removeItem(List<Media> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter title to remove");
        String title;
        title = sc.nextLine();

       if (remove(title, list)) {                    
            System.out.println("\nTitle removed\n");
        }else{
           System.out.println("Doesn't Exist");
       }

    }

    public static void quitAndSave(List<Media> list) {
        try (FileOutputStream fos = new FileOutputStream("file.bin")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);

        } catch (Exception e) {
          

        }
        System.exit(0);

    }

    static boolean mediaFinder(String title, List<Media> list) {
        if (list.isEmpty()) {
            return false;
        }
        for (Media x : list) {
            if (x.getTitle().equals(title)) {
                return true;
            }

        }
        return false;
    }

    static String getOption() {
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        while (true) {
            if (option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4") || option.equals("5") || option.equals("6")) {
                return option;
            } else {
                System.out.println("Try 1-6, Invalid Input\n");
                option = sc.nextLine();
                System.out.println();
            }

        }

    }
}
