
# Technical Assessment — System Implementation & Support Engineer (Docker Edition)

**Time window:** 7–10 days from receipt  
**Suggested effort:** ~6–8 focused hours (not a hard cap)  
**Delivery:** A Git repository URL + a runnable Docker setup (compose) and a short demo video or live demo

---

## 1) Overview
You will **package and run** a pre‑built Spring Boot service called **OrderDesk** using **Docker only** (no Kubernetes). Your goal is to get the app running locally with a database, expose it through **NGINX**, and show that you can scale the app to multiple replicas using Docker. **TLS is optional** (a plus if you add it). **CI/CD is optional**.

We evaluate clarity, correctness, reproducibility, and basic operability.

---

## 2) Provided Materials
You will receive a ZIP archive with the following:

```
orderdesk-spring/
 ├─ app/                       # Spring Boot (ready to run)
 │   ├─ pom.xml                # Maven build
 │   └─ src/...                # Source & tests
 ├─ sql/
 │   ├─ migrations/            # Flyway migrations + seed data
 │   └─ sample_queries.sql     # A few queries
 └─ postman/OrderDesk.postman_collection.json (optional)
```

**What you may change:** configuration (YAML/ENV), build files for packaging, and anything infra‑related (Docker, NGINX, compose, CI).  
**What you should not change:** business logic or API endpoints (no new features required).

**Service assumptions (for testing):**
- HTTP port: `8080`
- Health: `/actuator/health`
- Example APIs: `/orders/stats/daily`, `/customers`, `/orders`

---

## 3) Tasks (in sequence)
For each task, we list **what to do** and the **expected outcome** you must include in your README and demo.

### A. Push the code to Git
**What to do:**  
1. Create a new Git repository (GitHub, GitLab, or Bitbucket).  
2. Commit the provided code and infra files.  
3. Create one annotated tag (e.g., `v0.1.0`) and a `CHANGELOG.md` noting what you added.

**Expected outcome:**  
- A public or shareable repo URL.  
- `git tag -n` shows your release tag and message.  
- `CHANGELOG.md` present.

---

### B. Package the application using Maven
**What to do:**  
- Use Java 17 and run a Maven build that produces a runnable JAR (tests may run or be skipped; your choice, but say which).

**Expected outcome:**  
- A generated artifact under `app/target/orderdesk-*.jar`.  
- A README snippet showing the exact Maven command you used.

---

### C. Containerize the application with Docker and push it to a repository
**What to do:**  
1. Write a **multi‑stage Dockerfile** that builds the JAR and produces a slim runtime image running as a non‑root user.  
2. Include a `.dockerignore`.  
3. Build the image, tag it (e.g., `your-registry/orderdesk:0.1.0`) and push to a registry of your choice (Docker Hub acceptable).

**Expected outcome:**  
- `docker images` shows your image.  
- The image can start successfully with environment variables for DB connection.  
- An image URL we can pull (or instructions to build locally).

---

### D. Deploy the database using a Docker image, then initialize it with the provided migrations
**What to do:**  
- Use **PostgreSQL** (Docker Hub `postgres` image).  
- Provide environment variables, volume, and port mapping in **docker‑compose**.  
- Ensure the database schema and seed data are applied (you may rely on Flyway inside the app or use a migration container—either approach is fine; document what you chose).

**Expected outcome:**  
- `docker compose up -d` starts the DB.  
- Tables exist and seed data is present (show a simple `psql` or app‑level verification).

---

### E. Deploy the application and expose it via NGINX
**What to do:**  
- Use **docker‑compose** to run the app and an **NGINX** reverse proxy.  
- NGINX should route `http://localhost` (or `http://orderdesk.local`) to the app on port 8080.  
- **TLS optional:** if you add TLS, include self‑signed cert steps and use `https://` in your demo.

**Expected outcome:**  
- `curl http://localhost/actuator/health` returns `"UP"`.  
- `GET /orders/stats/daily` returns JSON.  
- Example `POST /customers` and `POST /orders` work (document request bodies & responses).

---

### F. Scale the application by adding another replica
**What to do:**  
- Scale to at least **2 app replicas** with Docker Compose (e.g., `docker compose up --scale app=2 -d`).  
- Ensure NGINX load‑balances requests across replicas (document your approach).

**Expected outcome:**  
- Requests are served by both replicas (e.g., add a `/actuator/health` instance id in the response headers or log the container hostname and show it changes between calls).  
- Include a short note on your chosen load‑balancing method.

---

### (Optional) G. CI/CD
**What to do (optional):**  
- Provide either a minimal GitHub Actions or GitLab CI pipeline to build and (optionally) push the image. No need to deploy.

**Expected outcome:**  
- A pipeline file (`.github/workflows/build.yml` or `.gitlab-ci.yml`) and a screenshot or badge showing success.

---

## 4) Acceptance Checklist (what we will run)
- `README` “Quickstart” can bring the stack up with `docker compose up -d` and verify endpoints via `curl`.  
- We can create a customer and an order and query them back.  
- Scaling to 2 replicas works and NGINX still routes correctly.  
- If you implemented TLS: we can hit the site over `https://` with your instructions for trusting a local cert.

---

## 5) What to Submit
- Git repo URL with: `Dockerfile`, `.dockerignore`, `docker-compose.yml`, `nginx.conf`, `README.md`, `CHANGELOG.md`, and (optional) CI file.  
- Demo notes: exact commands used and expected outputs (snippets are fine).

---

## 6) Evaluation Rubric (80 pts)
- **Reproducible deployment** (docs + compose) – 30 pts  
- **Docker image quality & security basics** (multi‑stage, non‑root, slim) – 15 pts  
- **NGINX exposure & routing** – 15 pts  
- **Database setup & migrations** – 10 pts  
- **Scaling with Docker** – 10 pts  
- **Documentation clarity** – 10 pts  
(+) **TLS** and **CI/CD** add up to 5 bonus points each.
