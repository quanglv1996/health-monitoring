package src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class UserManager {
    private List<User> users;
    private String userDataFile;
    private String recordsDirectory;

    public UserManager(String userDataFile, String recordsDirectory) {
        this.userDataFile = userDataFile;
        this.recordsDirectory = recordsDirectory;
        this.users = new ArrayList<>();
        loadUsers();
    }

    private void loadUsers() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userDataFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    users.add(new User(username, password));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(String username, String password, String re_password) {
        if (!userExists(username) && password.equals(re_password)) {
            try {
                FileWriter writer = new FileWriter(userDataFile, true);
                writer.append(username + "," + password + "\n");
                writer.close();
                createUserRecordFile(username);
                loadUsers();
                return (true && password.equals(re_password));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Đăng nhập thành công cho người dùng: " + username);
                return true;
            }
        }
        System.out.println("Đăng nhập không thành công cho người dùng: " + username);
        return false;
    }

    public boolean changePassword(String username, String newPassword) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(userDataFile));
                    StringBuilder data = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith(username)) {
                            line = username + "," + newPassword;
                        }
                        data.append(line).append("\n");
                    }
                    reader.close();

                    FileWriter writer = new FileWriter(userDataFile);
                    writer.write(data.toString());
                    writer.close();
                    user.setPassword(newPassword);
                    loadUsers();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean deleteUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(userDataFile));
                    StringBuilder data = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.startsWith(username)) {
                            data.append(line).append("\n");
                        }
                    }
                    reader.close();

                    FileWriter writer = new FileWriter(userDataFile);
                    writer.write(data.toString());
                    writer.close();

                    File recordFile = new File(recordsDirectory + File.separator + username + ".csv");
                    if (recordFile.exists()) {
                        recordFile.delete();
                    }

                    users.remove(user);
                    loadUsers();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private void createUserRecordFile(String username) {
        try {
            String recordFilePath = recordsDirectory + File.separator + username + ".csv";
            FileWriter writer = new FileWriter(recordFilePath);
            writer.append("Date,Speed,Time,Distance\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean userExists(String username) {
        loadUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager("/home/quanglv/NetBeansProjects/java_tutorial/src/main/java/com/quanglv/java_tutorial/users.csv", 
                "/home/quanglv/NetBeansProjects/java_tutorial/src/main/java/com/quanglv/java_tutorial/records");

        // Đăng ký người dùng mới
        userManager.registerUser("quanglv", "password123", "password123");

        // Đăng nhập
        userManager.login("user1", "password123");
        userManager.login("user1", "wrongpassword");

        // Thay đổi mật khẩu
        userManager.changePassword("user1", "newpassword123");

        // Xóa người dùng
        userManager.deleteUser("user1");
    }
}
