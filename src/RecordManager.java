/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author quanglv
 */
public class RecordManager {
    private String username;
    private String csvFilePath;

    public RecordManager(String username) {
        this.username = username;
        this.csvFilePath = "/home/quanglv/NetBeansProjects/java_tutorial/src/main/java/com/quanglv/java_tutorial/records/" + username + ".csv";
        createCSVFileIfNotExists();
    }

    private void createCSVFileIfNotExists() {
        File file = new File(csvFilePath);
        if (!file.exists()) {
            try {
                FileWriter writer = new FileWriter(csvFilePath);
                writer.append("Date,Speed,Time,Distance\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addRecord(String date, float speed, float time, float distance) {
        try {
            FileWriter writer = new FileWriter(csvFilePath, true);
            writer.append(date + "," + speed + "," + time + "," + distance + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Record> getAllRecords() {
        List<Record> records = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            String line;
            reader.readLine(); // Bỏ qua dòng tiêu đề
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String date = parts[0];
                    float speed = Float.parseFloat(parts[1]);
                    float time = Float.parseFloat(parts[2]);
                    float distance = Float.parseFloat(parts[3]);
                    records.add(new Record(date, speed, time, distance));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Sắp xếp danh sách theo ngày
        Collections.sort(records, (r1, r2) -> r1.getDate().compareTo(r2.getDate()));
        return records;
    }

    public void updateRecord(int index, String date, float speed, float time, float distance) {
        List<Record> records = getAllRecords();
        if (index >= 0 && index < records.size()) {
            records.set(index, new Record(date, speed, time, distance));
            saveRecords(records);
        }
    }

    public void deleteRecord(int index) {
        List<Record> records = getAllRecords();
        if (index >= 0 && index < records.size()) {
            records.remove(index);
            saveRecords(records);
        }
    }

    private void saveRecords(List<Record> records) {
        try {
            FileWriter writer = new FileWriter(csvFilePath);
            writer.append("Date,Speed,Time,Distance\n");
            for (Record record : records) {
                writer.append(record.getDate() + "," + record.getSpeed() + "," + record.getTime() + "," + record.getDistance() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Sử dụng RecordManager để quản lý record của người dùng "username"
        RecordManager recordManager = new RecordManager("admin2");

        // Thêm một bản ghi mới
        recordManager.addRecord("2024-03-15", 10, 60, 600);

        // Lấy tất cả các bản ghi
        List<Record> records = recordManager.getAllRecords();
        for (Record record : records) {
            System.out.println(record);
        }

        // Cập nhật một bản ghi
        recordManager.updateRecord(0, "2024-03-15", 15, 50, 750);

        // Xóa một bản ghi
//        recordManager.deleteRecord(0);
    }
}

