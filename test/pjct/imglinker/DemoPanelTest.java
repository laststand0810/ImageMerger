/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjct.imglinker;

import DummyPackage.ImageMergerPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nguyenpham
 */
public class DemoPanelTest {
    
    public DemoPanelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getImg method, of class ImageMergerPanel.
     */
    @Test
    public void testGetImg() throws IOException {
        System.out.println("getImg");
        ImageMergerPanel instance = new ImageMergerPanel();
        BufferedImage expResult = null;
        BufferedImage result = instance.getImg();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setImg method, of class ImageMergerPanel.
     */
    @Test
    public void testSetImg() throws IOException {
        System.out.println("setImg");
        BufferedImage img = null;
        ImageMergerPanel instance = new ImageMergerPanel();
        instance.setImg(img);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paintComponent method, of class ImageMergerPanel.
     */
    @Test
    public void testPaintComponent() throws IOException {
        System.out.println("paintComponent");
        Graphics g = null;
        ImageMergerPanel instance = new ImageMergerPanel();
        instance.paintComponent(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listImgFilesFromFolder method, of class ImageMergerPanel.
     */
    @Test
    public void testListImgFilesFromFolder() throws Exception {
        System.out.println("listImgFilesFromFolder");
        File folder = null;
        ArrayList<BufferedImage> expResult = null;
        ArrayList<BufferedImage> result = ImageMergerPanel.listImgFilesFromFolder(folder);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resizeImage method, of class ImageMergerPanel.
     */
    @Test
    public void testResizeImage() {
        System.out.println("resizeImage");
        BufferedImage image = null;
        int height = 0;
        int width = 0;
        BufferedImage expResult = null;
        BufferedImage result = ImageMergerPanel.resizeImage(image, height, width);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of concatImage method, of class ImageMergerPanel.
     */
    @Test
    public void testConcatImage() {
        System.out.println("concatImage");
        int col = 0;
        int row = 0;
        ArrayList<BufferedImage> images = null;
        BufferedImage expResult = null;
        BufferedImage result = ImageMergerPanel.concatImage(col, row, images);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveImageJPG method, of class ImageMergerPanel.
     */
    @Test
    public void testSaveImageJPG() throws Exception {
        System.out.println("saveImageJPG");
        BufferedImage image = null;
        String location = "";
        ImageMergerPanel.saveImageJPG(image, location);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class ImageMergerPanel.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ImageMergerPanel.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
