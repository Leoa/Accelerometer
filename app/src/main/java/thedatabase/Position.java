package thedatabase;

/**
 * Created by Leondria on 12/17/2014.
 */
public class Position {



        //private variables
        int _id;
        String _name;
        String _timestamp;
        int _leftAmount;
        int _rightAmount;
        int _upAmount;
        int _downAmount;
        int _backAmount;
        int _frontAmount;


    // Empty constructor
        public Position(){

        }
        // constructor
        public Position (int id, String name, String _timestamp){
            this._id = id;
            this._name = name;
            this._timestamp = _timestamp;
        }

        // constructor
        public Position (String name, String _timestamp){
            this._name = name;
            this._timestamp = _timestamp;
        }
        // getting ID
        public int getID(){
            return this._id;
        }

        // setting id
        public void setID(int id){
            this._id = id;
        }

        // getting name
        public String getPosition(){
            return this._name;
        }

        // setting name
        public void setPosition(String name){
            this._name = name;
        }

         // getting phone number
        public String getTimestamp(){
            return this._timestamp;
        }

        // setting phone number
        public void setTimestamp(String timestamp){
            this._timestamp = timestamp;
        }

        // get sum positions left
        public int getSumLeft(){
        return this._leftAmount;
    }

        // set sum positions left
        public void setSumLeft(int leftAmount){
        this._leftAmount = leftAmount;
    }

        // get sum positions right
        public int getSumRight(){
            return this._rightAmount;
        }

        // set sum positions right
        public void setSumRight(int rightAmount){
        this._rightAmount = rightAmount;
    }

        // get sum positions up
        public int getSumUp(){
            return this._upAmount;
        }

        // set sum positions up
        public void setSumUp(int upAmount){
            this._upAmount = upAmount;
        }

        // get sum positions down
        public int getSumDown(){
            return this._downAmount;
        }

        // set sum positions down
        public void setSumDown(int downAmount){
        this._downAmount = downAmount;
    }

        // get sum positions front
        public int getSumFront(){
            return this._frontAmount;
        }

        // set sum positions front
        public void setSumFront(int frontAmount){
            this._frontAmount = frontAmount;
        }

        // get sum positions back
        public int getSumBack(){
            return this._backAmount;
        }

        // set sum positions back
        public void setSumBack(int backAmount){
        this._backAmount = backAmount;
    }

}
