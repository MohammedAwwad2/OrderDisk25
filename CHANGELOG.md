## v0.1.0 - Initial Assessment Submission

**First push to GitHub:**
- Project received for technical assessment.
- No code changes made prior to assessment.
- Set up project in new Git repository.
- Added annotated tag `v0.1.0`.

**Second push to GitHub:**
- Prepared Docker multi-stage build and .dockerignore.
- Built, tagged, and pushed Docker image to registry.
- Deployed PostgreSQL database with Docker Compose, applied migrations and seed data using Flyway.
- Verified DB tables and seed data present.
- Deployed app and NGINX reverse proxy with Docker Compose; NGINX routes `http://localhost` to app on port 8080.
- Scaled app to 2 replicas with Docker Compose; NGINX load-balances requests across replicas.
