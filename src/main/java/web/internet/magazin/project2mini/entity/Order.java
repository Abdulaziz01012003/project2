package web.internet.magazin.project2mini.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.tuple.GenerationTiming;
import web.internet.magazin.project2mini.entity.enums.Status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CurrentTimestamp(timing = GenerationTiming.INSERT)
    private LocalDateTime orderDate;
    @ManyToOne
    private User user;

    public String getSimpleDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        return orderDate.format(formatter);
    }
}
