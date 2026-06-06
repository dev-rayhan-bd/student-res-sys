import java.sql.*;
import java.util.*;

public class StudentResultSystem {

    static final String URL = "jdbc:mysql://localhost:3306/student_db"; 
static final String USER = "root"; 
static final String PASS = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
    
        String[] studentNames = new String[3]; 
        
        HashMap<Integer, Integer[]> marksMap = new HashMap<>();

        System.out.println("--- Enter Data for 3 Students ---");
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter ID for student " + (i+1) + ": ");
            int id = sc.nextInt();
            sc.nextLine(); 

            System.out.print("Enter Name: ");
            studentNames[i] = sc.nextLine();

            System.out.print("Enter Marks for 3 subjects (space separated): ");
            Integer[] marks = new Integer[3];
            marks[0] = sc.nextInt();
            marks[1] = sc.nextInt();
            marks[2] = sc.nextInt();
            
            marksMap.put(id, marks);
        }
//calculate total and grade, then insert into database using JDBC
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO student_results VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            int index = 0;
            for (Integer id : marksMap.keySet()) {
                Integer[] m = marksMap.get(id);
                String name = studentNames[index++];
                
             //logic to calculate total and grade
                int total = m[0] + m[1] + m[2];
                double avg = total / 3.0;
                String grade = (avg >= 80) ? "A+" : (avg >= 70) ? "A" : (avg >= 60) ? "B" : "F";
//insert data into database
                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.setInt(3, m[0]);
                pstmt.setInt(4, m[1]);
                pstmt.setInt(5, m[2]);
                pstmt.setInt(6, total);
                pstmt.setString(7, grade);
                pstmt.executeUpdate();
            }
            System.out.println("\nSuccessfully inserted data into Database!");

           //display all records from database
            displayAllRecords(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //display all records from database
    public static void displayAllRecords(Connection conn) throws SQLException {
        System.out.println("\n--- Student Records from Database ---");
        String query = "SELECT * FROM student_results";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("ID | Name | Total | Grade");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " | " + 
                               rs.getString("name") + " | " + 
                               rs.getInt("total") + " | " + 
                               rs.getString("grade"));
        }
    }
}