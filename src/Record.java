package src;

import java.util.ArrayList;
import java.util.List;

class Record {
    private String date;
    private float speed;
    private float time;
    private float distance;

    public Record(String date, float speed, float time, float distance) {
        this.date = date;
        this.speed = speed;
        this.time = time;
        this.distance = distance;
    }

    public String getDate() {
        return date;
    }

    public float getSpeed() {
        return speed;
    }

    public float getTime() {
        return time;
    }

    public float getDistance() {
        return distance;
    }
    
    public String[] getData(){
        String[] array = {String.valueOf(date),
                        String.valueOf(speed), 
                        String.valueOf(time),
                        String.valueOf(distance)};
        return array;
    }

    @Override
    public String toString() {
        return "Record{" +
                "date='" + date + '\'' +
                ", speed=" + speed +
                ", time=" + time +
                ", distance=" + distance +
                '}';
    }
}