package com.main.lunch.entity;

import com.main.lunch.entity.types.OutcomeType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "record")
@Data
@NoArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Player player;

    @Column(name = "outcome")
    private OutcomeType outcome;

    @Column(name = "date")
    private LocalDateTime date;

    public Record(Player player, OutcomeType outcome, LocalDateTime date) {
        this.player = player;
        this.outcome = outcome;
        this.date = date;
    }
}
