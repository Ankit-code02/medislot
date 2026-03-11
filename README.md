# 🏥 MediSlot - Smart Healthcare Scheduling Platform

A production-ready multi-role Healthcare Scheduling REST API built with Spring Boot 3.

## 🚀 Tech Stack
- **Java 17** + **Spring Boot 3.5**
- **Spring Security** + **JWT Authentication**
- **PostgreSQL** + **Spring Data JPA**
- **Spring Scheduler** (Email Reminders)
- **JavaMail** (Email Notifications)
- **Docker** + **Docker Compose**
- **Maven**

## 👥 Roles
| Role | Permissions |
|------|------------|
| PATIENT | Register, book/cancel appointments, view profile |
| DOCTOR | Manage profile, view/confirm appointments |
| ADMIN | View all appointments, manage users |

## 📋 API Endpoints

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/auth/register | Register new user |
| POST | /api/auth/login | Login & get JWT token |

### Doctor
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/doctor/profile | Create doctor profile |
| GET | /api/doctor/profile | Get my profile |
| GET | /api/doctor/all | Get all doctors |
| GET | /api/doctor/search?specialization= | Search by specialization |

### Patient
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/patient/profile | Create patient profile |
| GET | /api/patient/profile | Get my profile |

### Appointments
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/appointments/book | Book appointment |
| GET | /api/appointments/my | Get my appointments |
| PUT | /api/appointments/cancel/{id} | Cancel appointment |
| PUT | /api/appointments/confirm/{id} | Confirm appointment |
| GET | /api/appointments/all | Get all appointments |

## 🐳 Run with Docker
```bash
