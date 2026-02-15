package com.sentinelx.app.repository;

import com.sentinelx.app.model.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventLogRepository extends JpaRepository<EventLog, Long> {

    List<EventLog> findByStatus(String status);

    List<EventLog> findByUsername(String username);

    List<EventLog> findByIpAddress(String ipAddress);
}
