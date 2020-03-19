package hu.bme.vik.tbs.onlab.CsotthonApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CleaningItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "cleaningItem",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<RoomCleaning> room_cleanings=new ArrayList<>();

}
