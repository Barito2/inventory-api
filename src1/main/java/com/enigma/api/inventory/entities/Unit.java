package com.enigma.api.inventory.entities;

import jdk.jfr.Name;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Table
@Getter
@Setter
@ToString
@Entity
@SQLDelete(sql = "UPDATE unit SET is_deleted=1 WHERE id=?")
@Where(clause = "is_deleted=0")
public class Unit extends AbstractEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 5)
    private String code;

    @Column(nullable = false, length = 50)
    @Name("desc")
    private String description;
}
