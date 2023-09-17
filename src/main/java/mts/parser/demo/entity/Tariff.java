package mts.parser.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tariffs")
@Data
@NoArgsConstructor
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tariff_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "benefits")
    private String benefits;

    @Column(name = "internet")
    private String internet;

    @Column(name = "minutes")
    private String minutes;

    @Column(name = "tv")
    private String tv;

    @Column(name = "wifi")
    private String wifi;

    @Column(name = "price")
    private long price;
}
