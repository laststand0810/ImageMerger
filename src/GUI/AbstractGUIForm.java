/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author nguyenpham
 */
import pjct.imglinker.AbstractGUIFormReceiver;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstract class for GUI input form, receive and store data in HashMap, check
 * data with the required constraints function and send data to added receivers
 * which are classes extended from AbstractGUIFormReceiver.
 *
 * This abstract is used to promote data constraint setting and checking
 * practice on the front layer before sending to be processed in the back
 * receivers.
 *
 * Must implement checkDataConstraints() and throw RuntimeException on each
 * constraint accordingly.
 *
 * Implementation: 1. Instantiate an input Hashtable< String, Object> [says,
 * hmap], put data into hmap and use super.setData(hmap) to submit the data in
 * hmap to the GUI Form.
 *
 * 2. Set constraints in checkDataConstraints(HashMap<String, Object> data),
 * throw RuntimeException on each constraint, return true when all the
 * constraint is satisfied.
 *
 * 3. add in the receivers. Receivers only receive data through sendData() after 
 * being added. 
 * sendData(AbstractGUIFormReceiver... res) is used only for when developer want 
 * only some specific receivers in the list to run at that time.
 */
public abstract class AbstractGUIForm {

    /**
     *
     */
    private HashMap<String, Object> data;

    private final ArrayList<AbstractGUIFormReceiver> receivers = 
            new ArrayList<>();

    /**
     * @return the data
     */
    public HashMap<String, Object> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    protected void setData(HashMap<String, Object> data) {
        try {
            if (checkDataConstraints(data)) {
                this.data = data;
            }
        } catch (RuntimeException ex){
            java.util.logging.Logger.getLogger(IndexFrame.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    }

    public ArrayList<AbstractGUIFormReceiver> getReceivers() {
        return receivers;
    }

    public void addReceiver(AbstractGUIFormReceiver receiver) 
            throws RuntimeException {
        if (!receivers.contains(receiver)) {
            receivers.add(receiver);
            System.gc();
        } else {
            System.gc();
            throw new RuntimeException("Receiver is already added.");
        }
    }

    public void removeReceiver(AbstractGUIFormReceiver receiver) {
        receivers.remove(receivers.get(receivers.indexOf(receiver)));
    }

    public void sendData() throws NullPointerException {
        if (receivers.isEmpty()) {
            throw new NullPointerException("There are no receiver in this form.");
        } else {
            receivers.forEach((receiver) -> {
                receiver.setData(data);
                receiver.operate();
            });
        }
    }

    public void sendData(AbstractGUIFormReceiver... res) throws RuntimeException {
        ArrayList<AbstractGUIFormReceiver> duplicate = new ArrayList<>();
        for (AbstractGUIFormReceiver receiver : res) {
            if (receivers.contains(receiver) && !duplicate.contains(receiver)) {
                duplicate.add(receiver);
            } else {
                throw new 
        RuntimeException("Called receivers must first be added to receiver list. "
                        + "Cannot send to the same receiver at one time");
            }
        }
        for (AbstractGUIFormReceiver receiver : res) {
                receiver.setData(data);
                receiver.operate();
        }
       

    }

    // set up Data Constraints before input
    // please implement RuntimeExceptions for every constraints
    abstract boolean checkDataConstraints(HashMap<String, Object> data) 
            throws RuntimeException;

}
