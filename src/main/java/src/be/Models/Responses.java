package src.be.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Responses {
    private Date timestamp;
    private String status;
    private String message;
    private String path;
}
