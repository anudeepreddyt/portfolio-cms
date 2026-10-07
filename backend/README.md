# Portfolio CMS Backend

Spring Boot REST API and custom CMS backend for the portfolio. It uses PostgreSQL, JPA, Spring Security and JWT access/refresh tokens.

## Run locally

1. Create a PostgreSQL database named `portfolio_cms`.
2. Copy `.env.example` values into your shell/environment (or configure the same variables in your IDE).
3. Use Maven 3.9+ and Java 21.
4. Run `mvn clean package -DskipTests` and then `mvn spring-boot:run`.

The API listens on `PORT` (default 8080). Uploaded images are stored under `MEDIA_DIR` and exposed from `/media/{filename}`.

## Authentication

The first startup creates an admin account from `ADMIN_EMAIL` and `ADMIN_PASSWORD` when that email does not already exist. Passwords are BCrypt hashed. Login returns an access token and refresh token.

## API

### Auth
| Method | Endpoint | Auth | Request | Response |
|---|---|---|---|---|
| POST | `/auth/login` | No | `{email,password}` | access/refresh tokens |
| POST | `/auth/refresh` | No | `{refreshToken}` | rotated access/refresh tokens |

### Public content / authenticated writes
| Method | Endpoint | Auth | Request / Response |
|---|---|---|---|
| GET | `/about` | No | About object |
| PUT | `/about` | Admin | About object |
| GET | `/skills` | No | Skill list |
| POST/PUT/DELETE | `/skills`, `/skills/{id}` | Admin | Skill JSON |
| GET | `/projects` | No | Project list |
| POST/PUT/DELETE | `/projects`, `/projects/{id}` | Admin | Project JSON |
| GET | `/blogs` | No | Published blogs |
| GET | `/blogs/{slug}` | No | Blog detail |
| GET | `/blogs/admin` | Admin | All blogs |
| POST/PUT/DELETE | `/blogs`, `/blogs/{id}` | Admin | Blog JSON |
| GET | `/experience` | No | Timeline list |
| POST/PUT/DELETE | `/experience`, `/experience/{id}` | Admin | Experience JSON |
| GET | `/testimonials` | No | Testimonials |
| POST/PUT/DELETE | `/testimonials`, `/testimonials/{id}` | Admin | Testimonial JSON |
| GET | `/services` | No | Services |
| POST/PUT/DELETE | `/services`, `/services/{id}` | Admin | Service JSON |
| POST | `/upload/image` | Admin | multipart `file` | Media object |
| POST | `/contact` | No | `{name,email,subject,message}` | confirmation |
| GET | `/media/{filename}` | No | image request | file |

### Admin
| Method | Endpoint | Auth | Purpose |
|---|---|---|---|
| GET | `/admin/stats` | Admin | CMS dashboard counts |
| GET | `/admin/messages` | Admin | Contact messages |
| PATCH | `/admin/messages/{id}/read` | Admin | Mark message read |

## Environment variables

`DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD`, `JWT_SECRET`, `CORS_ALLOWED_ORIGINS`, `PUBLIC_BASE_URL`, `ADMIN_EMAIL`, `ADMIN_PASSWORD` are required. SMTP variables are required for actual contact email delivery; the message is always saved to PostgreSQL first.
