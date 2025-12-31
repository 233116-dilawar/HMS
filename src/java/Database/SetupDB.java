package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class SetupDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();

            // 1. Create Database if not exists
            System.out.println("Creating database 'hospital'...");
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS hospital");
            stmt.close();
            con.close();

            // 2. Connect to the new database
            con = DriverManager.getConnection(url + "hospital", user, password);
            stmt = con.createStatement();

            // 3. Read hospital.sql and execute
            File sqlFile = new File("hospital.sql");
            if (!sqlFile.exists()) {
                // Try looking in root if running from elsewhere
                sqlFile = new File("C:\\Users\\Dell\\Downloads\\Hospital-Management-System-main\\hospital.sql");
            }

            System.out.println("Reading SQL file: " + sqlFile.getAbsolutePath());
            BufferedReader br = new BufferedReader(new FileReader(sqlFile));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                // Skip comments and empty lines
                if (line.startsWith("--") || line.startsWith("/*") || line.trim().isEmpty())
                    continue;

                sb.append(line);

                // If statement ends with semicolon, execute it
                if (line.trim().endsWith(";")) {
                    try {
                        stmt.execute(sb.toString());
                        System.out
                                .println("Executed: " + sb.toString().substring(0, Math.min(sb.length(), 50)) + "...");
                    } catch (Exception e) {
                        // Ignore "Table exists" errors
                        System.out.println("Warning: " + e.getMessage());
                    }
                    sb = new StringBuilder();
                }
            }

            System.out.println("✅ DATABASE SETUP COMPLETE!");
            System.out.println("User 'admin' / 'admin' should now exist.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Setup Failed: " + e.getMessage());
        }
    }
}
