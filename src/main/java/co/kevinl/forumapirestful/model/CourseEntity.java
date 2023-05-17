package co.kevinl.forumapirestful.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "course", schema = "foro_restful")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    @OneToMany(mappedBy = "idCourse")
    private Collection<TopicEntity> topicsById;

}
