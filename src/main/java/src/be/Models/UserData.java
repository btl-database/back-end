package src.be.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "user_data", schema = "auth")
@NoArgsConstructor
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public UserData(String username, String password, String email) throws Exception {
        if(username == null || password == null || email == null) {
            throw new Exception("Điền không đủ thông tin!");
        }

        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
    }

    public void setPassword(String password) throws Exception {
        String Regex = "^[a-zA-Z0-9_]{8,15}$";
        if (password == null || !password.matches(Regex)) {
            throw new Exception("Định dạng mật khẩu không hợp lệ, 8-15 ký tự");
        }

        this.password = password;
    }

    public void setUsername(String username) throws Exception {
        String Regex = "^[a-zA-Z0-9_]{3,15}$";
        if (username == null || !username.matches(Regex)) {
            throw new Exception("Định dạng username không hợp lệ, 3-15 ký tự");
        }

        this.username = username;
    }

    public void setEmail(String email) throws Exception {
        String Regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if(email == null || !email.matches(Regex)) {
            throw new Exception("Định dạng email không hợp lệ");
        }

        this.email = email;
    }
}