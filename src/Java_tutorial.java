/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package src;
/**
 *
 * @author quanglv
 */
public class Java_tutorial {

    public static void main(String[] args) {
        String userDataFile = "/home/quanglv/NetBeansProjects/java_tutorial/src/main/java/com/quanglv/java_tutorial/users.csv";
        String recordsDirectory = "/home/quanglv/NetBeansProjects/java_tutorial/src/main/java/com/quanglv/java_tutorial/records";
        UserManager userManager = new UserManager(userDataFile, recordsDirectory);
        
        Login  login_frame = new Login(userManager);
        login_frame.setVisible(true);
    }
}
