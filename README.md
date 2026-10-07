# Portfolio Project with Custom CMS

This project implements the supplied portfolio/CMS specification as two deployable applications: a Spring Boot backend containing the custom CMS/API and a React/Vite frontend containing the public portfolio plus the CMS admin panel. The specification calls for a custom-coded CMS rather than an existing headless CMS, REST CRUD APIs, JWT authentication, media upload and contact-message handling. fileciteturn0file0L5-L12 fileciteturn0file0L40-L71

## Features implemented

- Custom CMS built from scratch; no third-party headless CMS.
- PostgreSQL persistence for users, about, skills, projects, blogs, experience, testimonials, services, messages and media.
- JWT login + refresh token flow for the admin panel.
- Protected CMS write APIs and admin dashboard.
- CRUD for About, Skills, Projects, Blogs, Experience, Testimonials and Services.
- Image upload endpoint and public media serving.
- Public portfolio pages for Home, About, Projects, Skills, Experience, Blog and Contact, plus Testimonials.
- Contact form saves messages and can send notification email through configurable SMTP.
- Responsive, intentionally simple/moderate UI.
- Dockerfiles for separate backend and frontend deployment.
- Environment-based configuration for database, JWT, CORS, API URL and SMTP.

The content models and required CRUD/auth/upload/contact responsibilities follow the source specification. fileciteturn0file0L42-L84

## Folder structure

```text
project-root/
├── backend/
│   ├── src/
│   ├── pom.xml
│   ├── Dockerfile
│   ├── .dockerignore
│   └── README.md
├── frontend/
│   ├── src/
│   ├── package.json
│   ├── vite.config.js
│   ├── Dockerfile
│   ├── .dockerignore
│   └── README.md
└── README.md
```

## Local setup

### 1. PostgreSQL

Create a PostgreSQL database:

```sql
CREATE DATABASE portfolio_cms;
```

### 2. Backend

Set the variables in `backend/.env.example` in your shell/IDE. At minimum:

```text
DATABASE_URL=jdbc:postgresql://localhost:5432/portfolio_cms
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=your-password
JWT_SECRET=a-random-secret-at-least-32-bytes
CORS_ALLOWED_ORIGINS=http://localhost:5173
PUBLIC_BASE_URL=http://localhost:8080
ADMIN_EMAIL=admin@example.com
ADMIN_PASSWORD=your-admin-password
```

Then:

```bash
cd backend
mvn clean package -DskipTests
mvn spring-boot:run
```

### 3. Frontend

Create `frontend/.env`:

```text
VITE_API_BASE_URL=http://localhost:8080
```

Then:

```bash
cd frontend
npm install
npm run dev
```

Open the Vite URL shown in the terminal. The admin panel is at `/admin/login`.

## Contact email

The contact API always saves the message in PostgreSQL. To send email notifications, configure `MAIL_HOST`, `MAIL_PORT`, `MAIL_USERNAME`, `MAIL_PASSWORD`, `MAIL_SMTP_AUTH`, `MAIL_SMTP_STARTTLS` and `CONTACT_EMAIL_TO`.

## Docker

The two services are independent. Docker Compose is not required.

Backend:

```bash
cd backend
docker build -t portfolio-cms-backend .
docker run --env-file .env -p 8080:8080 portfolio-cms-backend
```

Frontend:

```bash
cd frontend
docker build --build-arg VITE_API_BASE_URL=https://YOUR-BACKEND.onrender.com .
docker run -p 8080:80 portfolio-cms-frontend
```

For the frontend Docker build, Vite reads environment variables at build time. The simplest production approach is to create a temporary `.env.production` containing `VITE_API_BASE_URL=https://YOUR-BACKEND.onrender.com` before building, or pass it through the Docker build configuration. Do not put secrets in frontend variables.

## Render deployment

### Backend Web Service

1. Create a PostgreSQL database on Render.
2. Create a Web Service from the `backend` folder/repository.
3. Select Docker deployment.
4. Render uses `backend/Dockerfile`.
5. Set these environment variables:
   - `DATABASE_URL` = JDBC PostgreSQL connection URL for the Render database.
   - `DATABASE_USERNAME`
   - `DATABASE_PASSWORD`
   - `JWT_SECRET` = long random secret, at least 32 bytes.
   - `CORS_ALLOWED_ORIGINS` = your deployed frontend URL, for example `https://your-portfolio.onrender.com`.
   - `PUBLIC_BASE_URL` = deployed backend URL, for example `https://your-backend.onrender.com`.
   - `ADMIN_EMAIL`
   - `ADMIN_PASSWORD`
   - `CONTACT_EMAIL_TO`
   - SMTP variables if contact email delivery is required.
6. Render supplies `PORT`; the backend uses `server.port=${PORT:8080}`.

### Frontend Web Service

1. Create another Render Web Service from the `frontend` folder/repository.
2. Select Docker deployment.
3. Set `VITE_API_BASE_URL=https://YOUR-BACKEND.onrender.com` during the frontend image build. Because Vite embeds public configuration into the build, this variable is not a secret.
4. Deploy the service. Nginx serves the Vite production build and falls back to `index.html` for React routes.
5. Update backend `CORS_ALLOWED_ORIGINS` to the final frontend URL if it changes.

The source plan calls for backend/admin deployment, frontend deployment, environment variables, API URL configuration and final API/security/image testing. fileciteturn0file0L136-L163

## Important production note about media

The implementation uses configurable file storage (`MEDIA_DIR`) as required by the specification. Render service filesystems can be ephemeral, so for durable production media you should point the upload layer at persistent/object storage before treating uploaded portfolio images as permanent. The application code keeps the storage boundary isolated in `UploadController` so this can be replaced without changing the content model.

## Verification status

The source has been statically reviewed for project structure, API wiring, route protection, environment configuration and frontend/backend endpoint alignment. This execution environment does not have Maven, Docker, PostgreSQL or a usable npm dependency cache/network available, so full Maven, PostgreSQL integration and Docker image builds could not be executed here. The project therefore includes the exact commands above for the final environment verification requested in the specification.
