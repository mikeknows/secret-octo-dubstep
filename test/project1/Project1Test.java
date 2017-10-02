/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author plymi
 */
public class Project1Test {

    private List<Media> list;

    public Project1Test() {
    }

    @Before
    public void setUp() {
        list = new ArrayList<>();
        list.add(new Media("hello", "no"));
    }

    @After
    public void tearDown() {
        list = null;
    }

    /**
     * Test of insertItem method, of class Project1.
     */
    @Test
    public void testAdd() {
        System.out.println("insertItem");
        String title = "hello", format = "no";
        boolean var = project1.Project1.add(title, format, list);
        assertEquals(false, var);
        
        var = project1.Project1.add("coolkids", "dvd", list);
        assertEquals(true, var);
        
    }

    /**
     * Test of removeItem method, of class Project1.
     */
    @Test
    public void testRemove() {
        System.out.println("removeItem");
        Media item = new Media("title", "format");
        String title = "hello";
        boolean var = project1.Project1.remove(title, list);
        assertEquals(true, var);
        
        var = project1.Project1.remove("dne", list);
        assertEquals(false, var);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

}
