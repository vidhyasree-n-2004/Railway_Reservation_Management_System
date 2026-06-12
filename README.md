## 💻 Sample Code

### Ticket Booking Example

```java
import java.sql.*;

public class BookTicket {

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/railway_db",
                "root",
                "password"
            );

            String query = "INSERT INTO passenger(name, age, train_no, seat_no) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "Vidhyasree");
            ps.setInt(2, 22);
            ps.setInt(3, 12635);
            ps.setString(4, "S1-25");

            int result = ps.executeUpdate();

            if(result > 0){
                System.out.println("Ticket Booked Successfully!");
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
```
