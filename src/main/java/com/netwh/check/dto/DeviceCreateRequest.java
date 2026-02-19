package com.netwh.check.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceCreateRequest {

    @NotBlank(message = "Device name cannot be blank")
    private String name;

    @NotBlank(message = "IP address cannot be blank")
    @Pattern(
            regexp = "^(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}$",
            message = "IP address must be a valid IPv4 address (e.g., 192.168.1.10)"
    )
    private String ipAddress;
}
