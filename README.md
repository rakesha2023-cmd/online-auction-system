# Online Auction System

This is a beginner-friendly Agile project for an **Online Auction System**.
It includes:
- Java bidding logic
- JUnit test cases for bid validation
- Maven build automation
- GitHub Actions CI pipeline
- Jenkins pipeline sample
- Docker containerization
- Kubernetes deployment files

## 1) Project Features
- Records bids correctly
- Validates bidder name and bid amount
- Checks auction start and end time
- Selects highest bidder correctly
- Runs automated tests in CI/CD pipeline
- Supports Docker and Kubernetes deployment

## 2) Project Structure
```text
online-auction-system/
├── .github/workflows/maven-ci.yml
├── k8s/
│   ├── deployment.yaml
│   └── service.yaml
├── src/main/java/com/auction/
│   ├── AuctionApplication.java
│   ├── model/
│   │   ├── AuctionItem.java
│   │   └── Bid.java
│   └── service/
│       ├── AuctionService.java
│       └── BidValidationException.java
├── src/test/java/com/auction/service/
│   └── AuctionServiceTest.java
├── Dockerfile
├── Jenkinsfile
├── pom.xml
└── README.md
```

## 3) Software Needed
Install these first on Windows:
1. **JDK 17**
2. **Apache Maven**
3. **Git**
4. **Docker Desktop**
5. **kubectl**
6. **Minikube** (optional for local Kubernetes)
7. **VS Code** or IntelliJ IDEA

## 4) How to Run the Project Locally
Open Command Prompt or PowerShell in the project folder.

### Step 1: Build the project
```bash
mvn clean package
```

### Step 2: Run tests
```bash
mvn test
```

### Step 3: Run the application
```bash
java -jar target/online-auction-system-1.0.0.jar
```

You should see the highest bidder printed in the console.

## 5) How to Push Project to GitHub

### Step 1: Create repository on GitHub
- Open GitHub
- Click **New repository**
- Repository name: `online-auction-system`
- Choose **Public** or **Private**
- Click **Create repository**

### Step 2: Open terminal in project folder and run
```bash
git init
git add .
git commit -m "Initial commit - Online Auction System"
git branch -M main
git remote add origin https://github.com/YOUR_GITHUB_USERNAME/online-auction-system.git
git push -u origin main
```

Replace `YOUR_GITHUB_USERNAME` with your GitHub username.

## 6) GitHub Actions CI Pipeline
This project already contains `.github/workflows/maven-ci.yml`.

When you push code to GitHub:
- GitHub Actions automatically starts
- Maven build runs
- JUnit tests run automatically

To check it:
- Open your GitHub repository
- Click **Actions** tab
- You will see the workflow status

## 7) Jenkins CI/CD Pipeline
This project also contains a `Jenkinsfile`.

### Jenkins flow
- Pull source code from GitHub
- Run Maven build
- Run JUnit tests
- Package JAR file
- Build Docker image
- Push image to Docker Hub
- Deploy to Kubernetes

### Before running Jenkins pipeline
You must:
- Install Jenkins
- Configure JDK and Maven in Jenkins Global Tools
- Install Docker on the Jenkins machine
- Install kubectl on the Jenkins machine
- Add Docker Hub credentials in Jenkins using ID: `dockerhub-creds`
- Replace `YOUR_DOCKERHUB_USERNAME` in `Jenkinsfile`

## 8) Docker Steps

### Step 1: Build JAR first
```bash
mvn clean package
```

### Step 2: Build Docker image
```bash
docker build -t YOUR_DOCKERHUB_USERNAME/online-auction-system:latest .
```

### Step 3: Login to Docker Hub
```bash
docker login
```

### Step 4: Push image
```bash
docker push YOUR_DOCKERHUB_USERNAME/online-auction-system:latest
```

### Step 5: Run container locally
```bash
docker run --name online-auction-app YOUR_DOCKERHUB_USERNAME/online-auction-system:latest
```

## 9) Kubernetes Steps

### Step 1: Start Minikube
```bash
minikube start
```

### Step 2: Apply deployment
```bash
kubectl apply -f k8s/deployment.yaml
```

### Step 3: Apply service
```bash
kubectl apply -f k8s/service.yaml
```

### Step 4: Check resources
```bash
kubectl get pods
kubectl get services
```

### Step 5: Open service
```bash
minikube service online-auction-system-service
```

## 10) Important Changes You Must Make
Before running Docker/Kubernetes/Jenkins:
- Replace `YOUR_DOCKERHUB_USERNAME` in:
  - `Jenkinsfile`
  - `k8s/deployment.yaml`
  - Docker commands

## 11) Suggested Agile Modules / User Stories
- As a bidder, I want to place a valid bid.
- As an admin, I want invalid bids rejected.
- As a system, I want the highest bidder selected automatically.
- As a developer, I want tests to run on every code commit.
- As a DevOps engineer, I want automatic deployment after successful build.

## 12) Viva / Explanation Points
- **Bidding logic:** validates bids and stores highest bid.
- **JUnit testing:** checks valid and invalid bidding scenarios.
- **CI:** automatically builds and tests code on every push.
- **CD:** automatically deploys Docker image to Kubernetes.
- **Docker:** packages app and dependencies into one container.
- **Kubernetes:** manages deployment and service exposure.

## 13) Common Errors
### Port already allocated
Change `nodePort` in `k8s/service.yaml` to a free port like `30082`.

### Minikube container not found
Run:
```bash
minikube start --driver=docker
```

### Docker image not found in Kubernetes
Make sure image is pushed correctly and deployment file has correct Docker Hub username.

## 14) Commands Summary
```bash
mvn clean package
mvn test
java -jar target/online-auction-system-1.0.0.jar
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/YOUR_GITHUB_USERNAME/online-auction-system.git
git push -u origin main
docker build -t YOUR_DOCKERHUB_USERNAME/online-auction-system:latest .
docker login
docker push YOUR_DOCKERHUB_USERNAME/online-auction-system:latest
minikube start
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
minikube service online-auction-system-service
```
