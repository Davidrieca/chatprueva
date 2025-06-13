# TecnoMeet Social Platform

## Architecture Overview

TecnoMeet is a RESTful API-based application for Tecnocampus students to connect with each other. The application follows a layered architecture with clear separation of concerns:

1. **Presentation Layer**: REST Controllers handling HTTP requests/responses
2. **Business Layer**: Service components implementing business logic and rules
3. **Persistence Layer**: Repository components for data storage and retrieval
4. **Domain Layer**: Core domain entities and value objects

### System Architecture Diagram

![image](https://github.com/user-attachments/assets/dd319bc5-67d2-47a3-90f3-ae7a988d5d31)


## Key Features Implemented

- University email validation (@tecnocampus.cat)
- Complete user profile management
- Match status tracking (Pending, Matched, Unmatched)
- Chat functionality with message history management
- Profile completeness verification before matching
- Robust error handling and validation

## Domain Model

The core domain model consists of:

- **User**: A Tecnocampus student with profile information
- **Match**: A connection between two users with status tracking
- **Chat**: A conversation linked to a match, containing messages
- **Message**: Individual text communications between matched users

## API Endpoints

### User API

- `POST /api/users` - Create a new user
- `GET /api/users/{id}` - Get user details
- `PUT /api/users/{id}` - Update user details
- `PATCH /api/users/{id}/complete-profile` - Mark profile as complete

### Match API

- `POST /api/matches/swipe` - Register a swipe action
- `GET /api/matches/user/{userId}` - Get all matches for a user
- `PATCH /api/matches/{id}/unmatch` - Unmatch two users

### Chat API

- `POST /api/chats?matchId={matchId}` - Create a new chat for a match
- `GET /api/chats/{id}` - Get chat details
- `POST /api/chats/{id}/messages` - Send a message in a chat
- `GET /api/chats/{id}/messages` - Get all messages in a chat
- `DELETE /api/chats/{id}` - Delete a chat

## Technology Stack

- Java 17
- Spring Boot 2.7
- Spring MVC for RESTful APIs
- JUnit 5 and Cucumber for testing
- GitHub Actions for CI/CD
- Maven for build automation

## Build & Run


Clone repository
git clone https://github.com/LabSoftware-25/group-project-idpg.git
cd tecnomeet
Build the application
mvn clean install
Run the application
mvn spring-boot:run

## Testing


Run unit tests
mvn test
Run integration tests
mvn verify -DskipUnitTests
Run BDD tests with Cucumber
mvn test -Dcucumber.filter.tags="@regression"

## Security Considerations

- User authentication via Spring Security
- Input validation for all API endpoints
- Rate limiting for API calls
- Data sanitization to prevent XSS attacks

## Future Enhancements

- Push notifications for new matches and messages
- Integration with university events calendar
- Recommendation system for potential matches
- Group chat functionality for study groups
