# My Diary - GCP CI/CD Pipeline & Backend Architecture

## 📌 Project Overview
This project features a cloud-based CI/CD deployment pipeline for a Spring Boot-based diary web application, My Diary. It was implemented as part of the Introduction to Cloud Computing course at OTH Regensburg.

### Result: Version Upgrade
| My Diary v1.0 | My Diary v2.0 |
| :---: | :---: |
| <img width="155" height="136" alt="image" src="https://github.com/user-attachments/assets/670ae8e7-221f-4507-8140-09fffb9742f1" /> | <img width="145" height="136" alt="image" src="https://github.com/user-attachments/assets/32a77be4-a377-4541-8e34-c8ae5dc655fa" /> |

## 🏗️ Solution Architecture
<img width="613" height="382" alt="image" src="https://github.com/user-attachments/assets/f6ad6deb-b3cf-4f39-b2b4-d943552f9df3" />

* **Code Push**: Pushing code to GitHub triggers the automated pipeline.
* **Build & Push**: Cloud Build executes the build process to create a Docker image and pushes it to Artifact Registry.
* **Deploy**: The container image is then deployed to a fully managed Cloud Run instance.
* **Data Management**: Application text data is managed via Cloud SQL, while image data is handled by Cloud Storage.
* **Security & Monitoring**: Secret Manager is utilized to securely inject secrets, and Cloud Monitoring provides integrated system monitoring and alerts.

## 🛠️ Tech Stack & GCP Services
* **Backend**: Java, Spring Boot
* **Cloud Infrastructure**:
  * **Cloud Build**: Executes builds and generates artifacts from source code.
  * **Artifact Registry**: Centrally stores and manages container images.
  * **Cloud Run**: Fully managed application platform to run code and containers.
  * **Cloud SQL**: Fully managed relational database (RDB).
  * **Cloud Storage**: Object storage service for any type of data.
  * **Secret Manager**: Secure storage system to protect sensitive data.
  * **Cloud Monitoring**: Integrated monitoring service.

## ⚙️ Implementation Details

### CI/CD Pipeline (Cloud Build Triggers)
* **`pr-on-develop`**: Triggers upon pull requests to perform build verification before the PR merge, without actually deploying the application.
* **`push-to-develop`**: Triggers auto-deployment in three stages when code is pushed to the develop branch:
  * Stage 1: Builds the Docker image.
  * Stage 2: Pushes the image to Artifact Registry.
  * Stage 3: Updates the Cloud Run service with the new image.

### Docker & Storage Strategy
* **Containerization**: The application is packaged using a lightweight `eclipse-temurin:17-jdk-alpine` base image.
* **GCS Upload**: File uploads to Cloud Storage are managed by generating UUID-based unique keys to prevent file name collisions.

### Monitoring & Alerts
* **Log-Based Alerts**: Configured a `build-fail-alert` with a critical severity level to monitor logs for any Cloud Run revision errors.
