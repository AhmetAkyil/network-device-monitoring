# Network Device Monitoring Dashboard

A lightweight **network monitoring system** built with **Spring Boot + MySQL** that periodically checks device availability, logs performance history, and provides a clean web dashboard for monitoring.

This project focuses on core backend concepts such as:

- Scheduled background jobs
- REST API design
- Structured logging (SLF4J)
- Input validation
- Global exception handling
- Database logging & historical tracking
- Dashboard UI integration

---

# Features

## Device Monitoring
- Add devices via REST API
- Automatic health checks every 30 seconds
- Tracks:
  - Status (UP / DOWN)
  - Response time (latency)
  - Last checked timestamp

## Performance History
- Stores every check in `device_log`
- Latency visualization using Chart.js
- Detailed timestamped history table

## Alert Mechanism
- Triggers alert after **3 consecutive DOWN states**
- Uses structured SLF4J logging

## Validation & Error Handling
- Jakarta Validation (`@NotBlank`, `@Pattern`)
- IPv4 format validation
- Clean JSON validation responses
- Global exception handler

---

# Architecture Overview

1. Devices are stored in `network_device`.
2. A Spring `@Scheduled` task runs every 30 seconds.
3. Each device is pinged using `InetAddress.isReachable()`.
4. Status & latency are stored in `device_log`.
5. After 3 consecutive failures → alert is triggered.


### Layered Architecture

```
Client Request
     ↓
Controller
     ↓
Service
     ↓
Repository
     ↓
Database
```


---

# Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- Spring Scheduler
- SLF4J Logging
- Jakarta Validation
- Bootstrap (UI)
- Chart.js (Graphs)

---

# Screenshots

## 🏠 Dashboard

Displays all monitored devices with current status and last check time.

<img width="1903" height="812" alt="mainpage" src="https://github.com/user-attachments/assets/aed76ced-5827-4c8d-b551-e2283603c0e3" />

---

## 📊 Performance History

Latency visualization and detailed logs.

<img width="788" height="879" alt="loghist" src="https://github.com/user-attachments/assets/6f80525c-7f8d-4cb5-a9fe-675844fa7abb" />

---

## ➕ Add New Device

Modal for registering new monitored devices.

<img width="496" height="333" alt="newdevice" src="https://github.com/user-attachments/assets/e9ce45c7-8631-4ba7-930e-70a7874aff87" />

