#docker run --rm -i grafana/k6 run - <script.js

#K6_WEB_DASHBOARD_EXPORT=html-report.html k6 run script.js

#Write on prometheus
#K6_PROMETHEUS_RW_SERVER_URL=http://localhost:9090/api/v1/write K6_WEB_DASHBOARD_EXPORT=html-report.html k6 run -o experimental-prometheus-rw script.js

# Run with web_dashboard and export dashboard
K6_WEB_DASHBOARD=true K6_WEB_DASHBOARD_EXPORT=html-report.html k6 run script.js