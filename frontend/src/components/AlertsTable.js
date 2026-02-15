import React from "react";

function AlertsTable({ alerts }) {
  return (
    <div className="table-wrapper">
      <h2>System Logs</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>User</th>
            <th>Event</th>
            <th>Status</th>
            <th>IP</th>
            <th>Time</th>
          </tr>
        </thead>

        <tbody>
          {alerts.map(alert => (
            <tr key={alert.id}>
              <td>{alert.id}</td>
              <td>{alert.username}</td>
              <td>{alert.eventType}</td>
              <td className={alert.status === "SUCCESS" ? "green" : "red"}>
                {alert.status}
              </td>
              <td>{alert.ipAddress}</td>
              <td>{alert.timestamp}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default AlertsTable;
