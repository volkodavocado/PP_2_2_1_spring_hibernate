package hiber.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "car_id")
    @MapsId
    private User user;

    @Column(name = "series")
    private int series;

    @Column(name = "model")
    private String model;

    public Car() {

    }

    public Car(int series, String model) {
        this.series = series;
        this.model = model;
    }

    public User getUser() {
        return user;
    }

    public int getSeries() {
        return series;
    }

    public String getModel() {
        return model;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "user=" + user +
                ", series=" + series +
                ", model='" + model + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return series == car.series && Objects.equals(user, car.user) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, series, model);
    }
}
