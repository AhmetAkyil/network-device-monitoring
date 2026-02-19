package com.netwh.check.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class DeviceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "device_id", nullable = false)
    private NetworkDevice device;

    @Column(nullable = false)
    private String status;

    private long responseTime;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public DeviceLog(NetworkDevice device, String status, long responseTime, LocalDateTime timestamp) {
        this.device = device;
        this.status = status;
        this.responseTime = responseTime;
        this.timestamp = timestamp;
    }
}
