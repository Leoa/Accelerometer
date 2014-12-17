package thedatabase;

/**
 * Created by Leondria on 12/17/2014.
 */
public class Position {



        //private variables
        int _id;
        String _name;
        String _timestamp;

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

}
