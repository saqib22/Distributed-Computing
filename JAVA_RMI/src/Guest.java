/*
 * Author : Saqib Ali Khan
 * BSCS6C
 * SEECS-NUST
 * */

import java.io.Serializable;

public class Guest implements Serializable {
    public String name;
    public String room_type;

    Guest(String name, int room_type){
        this.name = name;
        this.room_type = "type_" + room_type;
    }
}
