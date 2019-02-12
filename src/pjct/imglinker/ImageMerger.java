/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjct.imglinker;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
/**
 *
 * @author nguyenpham
 */
public class ImageMerger extends pjct.imglinker.AbstractGUIFormReceiver {
    
    //read all files from folder
    public static ArrayList<BufferedImage> listImgFilesFromFolder(final File folder) throws IOException {
        String mimetype;
        ArrayList<BufferedImage> output = new ArrayList<>();
        File[] listfiles = folder.listFiles();
        Arrays.sort(listfiles);
        for (File entry : listfiles) {
            mimetype = new MimetypesFileTypeMap().getContentType(entry);

            if (mimetype.split("/")[0].equals("image")) {
                output.add(ImageIO.read(entry));
            }
        }
        return output;
    }

    /**
     *
     * @param image
     * @param height
     * @param width
     * @return
     */
    public static BufferedImage resizeImage(BufferedImage image, int height, int width) {
        Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public static BufferedImage concatImage(int col, int row, int grid, String readDirectory, String corner, String direction) throws IOException {
        ArrayList<BufferedImage> images = listImgFilesFromFolder(new File(readDirectory));
        int height = images.get(0).getHeight(), width = images.get(0).getWidth();
        BufferedImage dimg = new BufferedImage((width+grid)*col + grid, (height+grid) * row + grid, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = dimg.createGraphics();
        //g2d.setColor(Color.BLUE);
        //g2d.fillRect(0, 0, width, height);
        
        int count = 0, i = 0, j = 0, increment_i = 1, increment_j = 1, temp;
        switch (corner){
            case "UPPERLEFT":
                break;
            case "UPPERRIGHT":
                j = col -1;
                col = -1;
                increment_j = -1;
                break;
            case "LOWERLEFT":
                i = row -1 ;
                row = -1;
                increment_i = -1;
                break;
            case "LOWERRIGHT":
                i = row-1;
                j = col-1;
                row = -1;
                col = -1;
                increment_i = -1;
                increment_j = -1;
                break;
        }
        if (direction.equals("HORIZONTAL")){
            for (; i != row; i+= increment_i) {

                for (temp = j; j != col && count < images.size(); j+=increment_j) {
                    g2d.drawImage(resizeImage(images.get(count), height, width) , j * (width + grid) + grid*increment_j, i * (height + grid) + grid*increment_i, null);
                    count++;
                }
                j = temp;
            }
        }else{
            for (; j != col; j+= increment_j) {

                for (temp = i; i != row && count < images.size(); i+=increment_i) {
                    g2d.drawImage(resizeImage(images.get(count), height, width) , j * (width + grid) + grid*increment_j, i * (height + grid) + grid*increment_i, null);
                    count++;
                }
                i = temp;
            }
        }
        g2d.dispose();
        System.gc();
        return dimg;

    }

    public static void saveImageJPG(BufferedImage image, String location) throws IOException {
        int counter = 1;
        while (Arrays.asList(new File(location).list((File dir, String name1) -> {
            return name1.startsWith("result_") && name1.endsWith(".jpg");
        })).contains("result_" + counter + ".jpg")) {
            counter++;
        }
        File output = new File(location + "/result_" + counter + ".jpg");
        ImageIO.write(image, "jpg", output);
    }
    
    public static void concatAll(int col, int row, int grid, String readDirectory, String corner, String direction){
        
    }

    @Override
    public void operate() {
        try {
            
            BufferedImage img = concatImage((int) super.getData().get("Column"), 
                    (int) super.getData().get("Row"), (int) super.getData().get("Grid"),
                    (String) super.getData().get("ReadDirectory"), super.getData().get("Corner").toString(), 
                            super.getData().get("Direction").toString());
            saveImageJPG(img, (String) super.getData().get("WriteDirectory"));
        } catch (IOException ex) {
            Logger.getLogger(ImageMerger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
