# Manhattan Real Estate Broker System

## 🚀 Project Overview

This project implements a comprehensive real estate broker system for Manhattan, New York, designed as part of an Object-Oriented Programming (OOP) course final assignment. The system demonstrates advanced OOP principles, design patterns, and robust software engineering practices.

---

## 📑 Table of Contents

- [🚀 Project Overview](#-project-overview)
- [🗺️ System Description](#-system-description)
- [✨ Key Features](#-key-features)
- [🏗️ Design Patterns Implemented](#-design-patterns-implemented)
- [🧑‍💻 SOLID Principles in Action](#-solid-principles-in-action)
- [💡 OOP Principles Demonstrated](#-oop-principles-demonstrated)
- [🗂️ Project Structure](#-project-structure)
- [⚙️ System Requirements](#-system-requirements)
- [🛠️ Installation & Setup](#-installation--setup)
- [🧪 Testing](#-testing)
- [🌟 Implementation Highlights](#-implementation-highlights)
- [🧩 Design Pattern Benefits](#-design-pattern-benefits)
- [🎓 Learning Outcomes](#-learning-outcomes)
- [👤 Author](#-author)
- [📄 License](#-license)

---

## 🗺️ System Description

The Manhattan Real Estate System manages properties on a grid-based address system:

- Properties: Apartments identified by grid coordinates  
  - **Address format:** `[street, avenue, subdivision1, subdivision2, ...]`  
  - **Examples:** `[4,5]` (street 4, avenue 5), `[4,5,1,1]` (subdivided apartment)
- Attributes: Area (m²), price per m², sale status
- Users: Buyer, Seller, Broker (each with unique permissions)
- File-based property loading with validation

---

## ✨ Key Features

### 1. Property Management
- Grid-based addressing system
- Property attributes: address, area, price per m², sale status
- File-based property load & validation
- Full CRUD operations with permission controls

### 2. User Management
- Three user types: Buyer, Seller, Broker
- Permission system: View, Edit, Delete
- Factory pattern for user creation
- Observer pattern for property deletion notifications

### 3. Search Functionality
- Strategy pattern for flexible search algorithms
- Radius-based searches (Manhattan distance)
- Search by status (sold/available)
- Search by price (higher/lower/equal)
- Average price calculation

### 4. Deal Management
- Decorator pattern for adding deal services
- Basic deal execution with status updates
- Additional services: Evening, Cleaning, Moving, Design
- Dynamic pricing based on selected services

---

## 🏗️ Design Patterns Implemented

| Pattern      | Where Used              | Benefit                              |
| ------------ | ---------------------- | ------------------------------------- |
| Singleton    | SystemManager           | Centralized property management       |
| Factory      | UserFactory             | Centralized, validated user creation  |
| Observer     | PropertyDeletionObserver| Loose coupling, automatic notifications |
| Strategy     | PropertySearchStrategy  | Interchangeable/extensible search     |
| Decorator    | Deal Services           | Flexible, dynamic service composition |

---

## 🧑‍💻 SOLID Principles in Action

- **SRP:** Each class has a single responsibility
- **OCP:** Easily extend search/services via patterns
- **LSP:** Subtypes usable wherever parent types expected
- **ISP:** Small, focused interfaces
- **DIP:** High-level modules depend on abstractions

---

## 💡 OOP Principles Demonstrated

- **Encapsulation:** Private fields, getters/setters
- **Inheritance:** User hierarchy, DealDecorator, abstract classes
- **Polymorphism:** Strategy & Decorator patterns, user permissions
- **Abstraction:** Abstract User class, interfaces

---

## 🗂️ Project Structure

```text
src/
├── decorator/           # Deal service decorators
│   ├── BasicDeal.java
│   ├── CleaningDecorator.java
│   ├── Deal.java
│   ├── DealDecorator.java
│   ├── DesignDecorator.java
│   ├── EveningServicesDecorator.java
│   └── MovingDecorator.java
├── factory/            # User factory and user types
│   ├── Broker.java
│   ├── Buyer.java
│   ├── Seller.java
│   ├── User.java
│   └── UserFactory.java
├── main/               # Main system components
│   ├── SystemManager.java
│   ├── runningExample.java
│   └── prop1.txt
├── observer/           # Property deletion notifications
│   └── PropertyDeletionObserver.java
├── permission/         # Permission interfaces
│   ├── DeletePermission.java
│   ├── EditPermission.java
│   └── ViewPermission.java
├── strategy/           # Search algorithms
│   ├── PropertySearchContext.java
│   ├── PropertySearchStrategy.java
│   ├── SearchByAveragePriceStrategy.java
│   ├── SearchByPriceStrategy.java
│   └── SearchByStatusStrategy.java
├── test/               # Unit tests
│   └── java/
│       ├── TestDealDecorators.java
│       ├── TestFileReaderProperty.java
│       ├── TestProperty.java
│       ├── TestRadiusCalculator.java
│       ├── TestSearchStrategies.java
│       └── TestUser.java
└── util/               # Utilities
    ├── FileReaderProperty.java
    ├── Property.java
    └── RadiusCalculator.java
```

---

## ⚙️ System Requirements

- Java 8+ (uses modern Java features)
- JUnit 4 for testing
- File system access for property data

---

## 🛠️ Installation & Setup

1. Clone or download the project files
2. Ensure Java 8+ is installed
3. Compile the project:
   ```bash
   javac -cp . src/**/*.java
   ```
4. Run the example:
   ```bash
   java -cp . src.main.runningExample
   ```

---

## 🧪 Testing

This project includes comprehensive unit tests for:

- Property creation & validation
- User creation & permissions
- Search strategy functionality
- Deal decorator behavior
- File reading and parsing
- Radius calculation accuracy

*Run tests using your preferred Java testing framework or IDE.*

---

## 🌟 Implementation Highlights

### Error Handling
- Comprehensive input validation
- Custom exceptions for business logic
- Graceful file error handling
- Search boundary condition checks

### Performance
- Efficient Manhattan distance calculations
- Safe collection modifications (iterator pattern)
- Lazy loading where appropriate
- Memory-efficient data structures

### Code Quality
- Full JavaDoc documentation
- Consistent coding style
- Proper exception handling
- Thread-safe singleton implementation

---

## 🧩 Design Pattern Benefits

- **Maintainability:** Separation of concerns, loose coupling, easy extensibility
- **Flexibility:** Runtime strategy switching, dynamic deal services, configurable permissions
- **Testability:** Isolated components, mock-friendly interfaces, predictable behavior

---

## 🎓 Learning Outcomes

- Advanced OOP concepts in a real-world scenario
- Integration of design patterns in a cohesive system
- SOLID principles applied to complex requirements
- Enterprise-level code organization
- Comprehensive testing strategies

---

## 👤 Author

**Ido Cohen**  
idocohen963@gmail.com  
[GitHub](https://github.com/idocohen963) | [LinkedIn](https://www.linkedin.com/in/ido-cohen-14b8772b9/)

---

## 📄 License

This project is developed for educational purposes as part of an Object-Oriented Programming course.
