package ru.iaulitin.delayed.queue.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@EqualsAndHashCode
@ToString
public class User {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "id_generator", sequenceName = "user_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DEACTIVATION_TIME")
    private ZonedDateTime deactivationTime;
}
