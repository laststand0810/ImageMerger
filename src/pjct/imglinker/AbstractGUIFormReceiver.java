/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjct.imglinker;

import java.util.HashMap;

/**
 *
 * @author nguyenpham
 */
public abstract class AbstractGUIFormReceiver {
    private HashMap<String, Object> data;
    
    /**
     * @return the data
     */
    public HashMap<String, Object> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
    
    public abstract void operate();
}
