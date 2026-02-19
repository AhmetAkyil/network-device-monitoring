package com.netwh.check.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class NetworkDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Device name cannot be blank")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "IP address cannot be blank")
    @Pattern(
            regexp = "^(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}$",
            message = "IP address must be a valid IPv4 address (e.g., 192.168.1.10)"
    )
    @Column(nullable = false)
    private String ipAddress;

    private String status;

    private LocalDateTime lastChecked;
}
