/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package MiniApp;

import java.sql.SQLException;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws SQLException{
        MiniApp.views.Users.Index index = new MiniApp.views.Users.Index();
        index.setVisible(true);
    }
}
