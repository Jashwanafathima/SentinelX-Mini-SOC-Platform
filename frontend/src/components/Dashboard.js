import React, { useEffect, useState } from "react";
import axios from "axios";
import AlertsTable from "./AlertsTable";
import "./Dashboard.css";

function Dashboard({ onLogout }) {
  const [logs, setLogs] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/logs")
      .then(res => setLogs(res.data))
      .catch(err => console.error(err));
  }, []);

  const successCount = logs.filter(l => l.status === "SUCCESS").length;
  const failedCount = logs.filter(l => l.status === "FAILED").length;

  return (
    <div className="dashboard">
      
      <div className="top-bar">
        <h1 className="title">🛡 SOC Security Dashboard</h1>
        <button className="logout-btn" onClick={onLogout}>
          Logout
        </button>
      </div>

      <div className="cards">
        <div className="card">
          <h3>Total Logs</h3>
          <p>{logs.length}</p>
        </div>

        <div className="card success">
          <h3>Successful Logins</h3>
          <p>{successCount}</p>
        </div>

        <div className="card failed">
          <h3>Failed Logins</h3>
          <p>{failedCount}</p>
        </div>
      </div>

      <AlertsTable alerts={logs} />
    </div>
  );
}

export default Dashboard;
