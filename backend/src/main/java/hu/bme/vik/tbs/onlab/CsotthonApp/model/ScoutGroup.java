package hu.bme.vik.tbs.onlab.CsotthonApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hu.bme.vik.tbs.onlab.CsotthonApp.util.Sex;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoutGroup {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    private Sex sex;

    @OneToMany(mappedBy = "scoutGroup", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<User> members=new ArrayList<>();

    @OneToMany(mappedBy = "scoutGroup", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Cleaning> cleanings=new ArrayList<>();

}
