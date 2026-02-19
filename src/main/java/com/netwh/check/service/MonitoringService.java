package com.netwh.check.service;

import com.netwh.check.entity.DeviceLog;
import com.netwh.check.entity.NetworkDevice;
import com.netwh.check.repository.DeviceLogRepository;
import com.netwh.check.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonitoringService {

    private final DeviceRepository deviceRepository;
    private final DeviceLogRepository deviceLogRepository;

    @Scheduled(fixedRate = 30000)
    public void checkDevices() {
        List<NetworkDevice> devices = deviceRepository.findAll();

        for (NetworkDevice device : devices) {
            long startTime = System.currentTimeMillis();
            boolean isAlive = pingDevice(device.getIpAddress());
            long endTime = System.currentTimeMillis();

            long responseTime = isAlive ? (endTime - startTime) : 0;
            String currentStatus = isAlive ? "UP" : "DOWN";

            device.setStatus(currentStatus);
            device.setLastChecked(LocalDateTime.now());
            deviceRepository.save(device);

            DeviceLog logEntry = new DeviceLog(device, currentStatus, responseTime, LocalDateTime.now());
            deviceLogRepository.save(logEntry);

            log.info("Log saved: {} - {} ({}ms)", device.getName(), currentStatus, responseTime);

            if ("DOWN".equals(currentStatus)) {
                List<DeviceLog> lastLogs = deviceLogRepository.findTop3ByDeviceIdOrderByTimestampDesc(device.getId());
                long downCount = lastLogs.stream()
                        .filter(l -> "DOWN".equals(l.getStatus()))
                        .count();

                if (downCount >= 3) {
                    triggerAlert(device);
                }
            }
        }
    }

    private void triggerAlert(NetworkDevice device) {
        log.warn("🚨 [ALARM] {} ({}) IS NOT REACHABLE!", device.getName(), device.getIpAddress());

    }

    private boolean pingDevice(String ipAddress) {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            return address.isReachable(3000);
        } catch (IOException e) {
            log.debug("Ping failed for ipAddress={} reason={}", ipAddress, e.toString());
            return false;
        }
    }
}
