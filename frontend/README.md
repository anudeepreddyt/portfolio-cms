# Portfolio + CMS Admin Frontend

React/Vite frontend containing the public portfolio and the custom CMS admin panel at `/admin/login`.

## Run locally

Copy `.env.example` to `.env` and set `VITE_API_BASE_URL=http://localhost:8080` for local development.

```bash
npm install
npm run dev
```

Production build:

```bash
npm run build
```

The frontend includes Home, About, Projects, Skills, Experience, Blog, Testimonials and Contact pages. The admin panel provides login, dashboard, CRUD screens for every CMS content type, media upload and contact message management.
