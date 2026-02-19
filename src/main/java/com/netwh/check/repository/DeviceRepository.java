package com.netwh.check.repository;

import com.netwh.check.entity.NetworkDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<NetworkDevice,Long> {
}
