package com.netwh.check.controller;

import com.netwh.check.dto.DeviceCreateRequest;
import com.netwh.check.entity.DeviceLog;
import com.netwh.check.entity.NetworkDevice;
import com.netwh.check.repository.DeviceLogRepository;
import com.netwh.check.repository.DeviceRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceRepository deviceRepository;
    private final DeviceLogRepository deviceLogRepository;

    @GetMapping
    public List<NetworkDevice> getAllDevices() {
        return deviceRepository.findAll();
    }

    @GetMapping("/{id}/logs")
    public List<DeviceLog> getDeviceLogs(@PathVariable Long id) {
        return deviceLogRepository.findByDeviceIdOrderByTimestampDesc(id);
    }

    @PostMapping
    public NetworkDevice addDevice(@Valid @RequestBody DeviceCreateRequest request) {
        NetworkDevice device = new NetworkDevice();
        device.setName(request.getName());
        device.setIpAddress(request.getIpAddress());

        device.setStatus("UNKNOWN");
        device.setLastChecked(LocalDateTime.now());

        return deviceRepository.save(device);
    }
}
