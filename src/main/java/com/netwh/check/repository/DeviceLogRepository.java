package com.netwh.check.repository;

import com.netwh.check.entity.DeviceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceLogRepository  extends JpaRepository<DeviceLog,Long> {

    List<DeviceLog> findByDeviceIdOrderByTimestampDesc(Long deviceId);

    List<DeviceLog> findTop3ByDeviceIdOrderByTimestampDesc(Long id);
}
