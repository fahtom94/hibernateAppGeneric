package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Aleksandr Makarov on 05.02.2018.
 */

@Entity
@Table(name="user", schema = "public")
public class User {

    @Id
    @Column(name = "id", columnDefinition = "NUMERIC")
    private Long id;
    private String name;
}
