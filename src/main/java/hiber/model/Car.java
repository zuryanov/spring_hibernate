package hiber.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "series")
    private int series;

    @Column(name = "number")
    private String number;

    public Car(String name, int series) {
        this.name = name;
        this.series = series;
    }

    public Car(String name, int series, String number) {
        this.name = name;
        this.series = series;
        this.number = number;
    }
}
