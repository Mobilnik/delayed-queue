package ru.iaulitin.delayed.queue.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.iaulitin.delayed.queue.processor.core.DelayedTask;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "ORDERS")
@Data
@EqualsAndHashCode
@ToString
public class Order extends DelayedTask {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "id_generator", sequenceName = "order_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    private long id;

    @Column(name = "DEACTIVATION_TIME")
    private ZonedDateTime deactivationTime;

    @Column(name = "IS_ACTIVE")
    private boolean active;


    @Override
    public long getDelay(TimeUnit unit) {
        long diff = deactivationTime.toInstant().toEpochMilli() - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed user) {
        if (!(user instanceof Order)) {
            throw new RuntimeException("Unexpected delayed object:" + user);
        }
        return this.deactivationTime.compareTo(((Order) user).deactivationTime);
    }
}
