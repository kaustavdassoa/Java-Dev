package java.demo.streams.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Employees {
    private String name;
    private Department department;
    private String emailID;
    private List<String> phoneNumber;
}
