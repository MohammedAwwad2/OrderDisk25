# OrderDesk25

## Quickstart

1. **Build and run the stack:**
   ```
   docker compose up -d
   ```

2. **Verify health:**
   ```
   curl localhost/actuator/health
   ```


3. **Create customer:**
   ```
   curl -X POST -H "Content-Type: application/json" -d '{"name":"Alice Example","email":"alice@example.com"}' localhost/customers
   ```

4. **Get customers:**
   ```
   curl localhost/customers
   ```

5. **Create order:**
   ```
   curl -X POST -H "Content-Type: application/json" -d '{"customerId":1,"amount":25.5,"currency":"USD","status":"PAID"}' localhost/orders
   ```

6. **Get orders by customer:**
   ```
   curl "localhost/orders?customerId=1"
   ```



5. **Scale app to 2 replicas:**
   ```
   docker compose up --scale app=2 -d
   ```



### Endpoint summary
- `POST /customers` — Create customer
- `GET /customers` — List customers
- `POST /orders` — Create order
- `GET /orders?customerId=1` — List orders by customer



## Database

- Uses PostgreSQL (Docker Compose)
- Migrations and seed data applied via Flyway

## NGINX

- Reverse proxy on port 80 routes to app on port 8080
- Load-balances requests when scaling app replicas

## Docker

- Multi-stage Dockerfile for slim runtime image
- `.dockerignore` included for efficient builds

## CI/CD

- GitHub Actions workflow builds and pushes Docker image to Docker Hub

## Notes

- All commands and endpoints are tested with curl and Postman.

