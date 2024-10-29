# https://grafana.com/docs/k6/latest/results-output/real-time/prometheus-remote-write/
docker run --hostname k6 --network docker-compose_app_net --rm -i \
  -e K6_PROMETHEUS_RW_SERVER_URL=http://prometheus:9090/api/v1/write/ \
  grafana/k6 run --out experimental-prometheus-rw - <script.js
