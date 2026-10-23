# 🚀 AI-Powered Interview Prep

### Your Smart Companion for Technical Interview Preparation

An AI-powered interview preparation platform built with **Java, Spring Boot, MySQL, HTML, CSS, and JavaScript**, integrated with Google Gemini AI. The platform helps students prepare for technical interviews through an interactive question bank, coding practice, AI-generated explanations, and progress tracking.

[Features](#-features) • [Tech Stack](#-technology-stack) • [Getting Started](#-getting-started) • [Project Structure](#-project-structure) • [Future Enhancements](#-future-enhancements)

---

## 📌 Overview

Preparing for technical interviews requires consistent coding practice, strong computer science fundamentals, and a structured learning approach.

**AI-Powered Interview Prep** brings these resources together in one web application. Users can explore technical questions, practise coding problems, ask AI for explanations, and monitor their preparation progress.

The project combines a Java-based backend with a web interface and an AI integration to create a practical learning platform for students preparing for internships and software engineering placements.

## ✨ Features

### 📚 Interactive Question Bank
- Browse technical interview questions across multiple subjects.
- Search questions and explore available topics.
- View answers to strengthen conceptual understanding.
- Track practice progress.

### 💻 Coding Practice
- Practise programming problems through a dedicated interface.
- Explore coding solutions and explanations.
- Copy code for convenient practice.
- Track completed problems using browser-based progress storage.

### 🤖 AI Interview Assistant
- Ask questions about technical and programming topics.
- Generate AI-powered explanations using Google Gemini.
- Request coding solutions in supported programming languages.
- Choose different response styles, including interview explanations, coding solutions, MCQs, and beginner-friendly explanations.

### 📊 Progress Tracking
- View question practice progress.
- Monitor completed and remaining questions.
- Use progress information to guide further preparation.

### 🎨 Responsive User Interface
- Clean, organised layout for learning and practice.
- Navigation between the question bank, coding practice, and AI assistant.
- Responsive styling for different screen sizes.

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java 17 | Core programming language |
| Spring Boot 3 | Backend application and REST APIs |
| Maven | Dependency management and build automation |
| MySQL | Relational database |
| HTML5 | Web page structure |
| CSS3 | Styling and responsive layout |
| JavaScript | Client-side interactions |
| Google Gemini API | AI-generated interview assistance |

## 🏗️ Application Architecture

The application follows a layered web architecture:

1. **Frontend:** HTML, CSS, and JavaScript provide the user interface.
2. **Backend:** Spring Boot controllers handle requests and application logic.
3. **Database:** MySQL stores application data such as interview questions and practice progress, according to the configured schema.
4. **AI integration:** The backend communicates with the Google Gemini API to generate answers.

### Request Flow

```text
User
 |
 v
Web Interface
(HTML, CSS, JavaScript)
 |
 v
Spring Boot REST API
 |
 +--------------------+
 |                    |
 v                    v
MySQL Database    Gemini AI API
 |                    |
 +---------+----------+
           |
           v
     Display Results
```

## 📂 Project Structure

```text
interview-prep/
├── .mvn/
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/pallavi/interviewprep/
│   │   │       ├── AIController.java
│   │   │       ├── PracticeProgress.java
│   │   │       ├── PracticeProgressController.java
│   │   │       ├── PracticeProgressRepository.java
│   │   │       └── ...
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   │           ├── index.html
│   │           ├── coding.html
│   │           └── ai.html
│   └── test/
└── README.md
```

*Note: The structure above highlights the main files. Additional controllers, repositories, entities, and configuration files may be present in the complete project.*

## ⚙️ Getting Started

Follow these steps to run the project locally.

### Prerequisites

Install the following:

- Java Development Kit (JDK) 17
- MySQL Server
- Git
- An internet connection for Gemini API access and external frontend libraries

### 1. Clone the Repository

```bash
git clone https://github.com/pallavikompalli-max/Interview-Prep-AI.git
cd Interview-Prep-AI
```

### 2. Configure the Database

Create a MySQL database for the application:

```sql
CREATE DATABASE interview_prep;
```

Update the database connection settings in `src/main/resources/application.properties` to match your local MySQL configuration.

Use your own database username and password. The database name above is an example; use the database name expected by your application's configuration.

### 3. Configure the Gemini API Key

Create a Gemini API key through Google AI Studio and configure it as an environment variable.

**Windows PowerShell:**

```powershell
$env:GEMINI_API_KEY="YOUR_GEMINI_API_KEY"
$env:GEMINI_MODEL="gemini-3.5-flash-lite"
```

Replace the placeholder with your own API key. Never commit API keys, database passwords, or other secrets to GitHub.

If your application uses a different Gemini model, use the model configured for your account and controller.

### 4. Run the Application

From the project root, execute:

**Windows:**

```powershell
.\mvnw.cmd spring-boot:run
```

**Linux/macOS:**

```bash
./mvnw spring-boot:run
```

Ensure the configured MySQL database is running and accessible before starting the application.

### 5. Open the Website

Once the Spring Boot application starts, open:

- **Homepage / Question Bank:** http://localhost:8081/
- **Coding Practice:** http://localhost:8081/coding.html
- **AI Interview Assistant:** http://localhost:8081/ai.html

The application must be running for these local links to work.

## 🧪 Build and Test

Run the Maven test suite before submitting changes:

**Windows:**

```powershell
.\mvnw.cmd clean test
```

**Linux/macOS:**

```bash
./mvnw clean test
```

To package the application:

```bash
./mvnw package
```

Use the Windows Maven wrapper command when running these commands in PowerShell.

## 🔐 Security and Configuration

- Keep Gemini API keys outside version control.
- Do not commit database passwords or private credentials.
- Use environment variables or a local, untracked configuration file for secrets.
- Validate user input on the backend.
- Restrict and sanitise rendered AI-generated content before displaying it in the browser.
- Review dependencies and update them when appropriate.

## 🎯 Target Users

This platform is intended for:

- Students preparing for campus placements.
- Candidates preparing for technical interviews.
- Beginners learning programming and computer science fundamentals.
- Developers who want to practise coding problems and revise interview concepts.

## 🚀 Future Enhancements

Potential improvements include:

- User registration and secure authentication.
- Personalised learning plans and topic-wise analytics.
- Timed coding assessments and mock interviews.
- Difficulty-based question filtering.
- Bookmarks and saved questions.
- More programming languages and problem categories.
- Enhanced AI feedback for submitted coding solutions.
- Deployment to a cloud hosting platform.
- Automated build and testing using GitHub Actions.

## 👩‍💻 Author

**Pallavi Kompalli**

Computer Science Engineering student interested in software development, AI applications, and technical interview preparation.

### GitHub

[View the project repository](https://github.com/pallavikompalli-max/Interview-Prep-AI)

⭐ If you find this project useful, consider starring the repository!