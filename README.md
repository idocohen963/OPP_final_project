# Manhattan Real Estate Broker System

## Project Overview

This project implements a comprehensive real estate broker system for Manhattan, New York, designed as part of an Object-Oriented Programming (OOP) course final assignment. The system demonstrates advanced OOP principles, SOLID design principles, and multiple design patterns working together in a cohesive application.


## System Description

The Manhattan Real Estate System manages properties on a grid-based address system where:
- Properties are apartments characterized by their grid coordinates
- Address format: `[street, avenue, subdivision1, subdivision2, ...]`
- Examples: `[4,5]` (street 4, avenue 5) or `[4,5,1,1]` (subdivided apartment)
- Properties have area (m²), price per m², and sale status
- Users have different permission levels (Buyer, Seller, Broker)

## Key Features

### 1. Property Management
- **Grid-based addressing system** for Manhattan properties
- **Property attributes**: address, area, price per m², sale status
- **File-based property loading** with validation
- **CRUD operations** with proper permission controls

### 2. User Management
- **Three user types**: Buyer, Seller, Broker
- **Permission system**: View, Edit, Delete permissions
- **Factory pattern** for user creation
- **Observer pattern** for property deletion notifications

### 3. Search Functionality
- **Strategy pattern** for different search algorithms
- **Radius-based searches** using Manhattan distance
- **Search by status** (sold/available)
- **Search by price** (higher/lower/equal)
- **Average price calculation** for market analysis

### 4. Deal Management
- **Decorator pattern** for adding services to deals
- **Basic deal execution** with property status updates
- **Additional services**: Evening, Cleaning, Moving, Design
- **Dynamic pricing** based on selected services

## Design Patterns Implemented

### 1. Singleton Pattern
- **SystemManager**: Ensures single instance managing the entire system
- **Benefits**: Centralized property management, consistent state

### 2. Factory Method Pattern
- **UserFactory**: Creates different user types (Buyer, Seller, Broker)
- **Benefits**: Centralized user creation, validation, duplicate prevention

### 3. Observer Pattern
- **PropertyDeletionObserver**: Brokers receive notifications when sellers delete properties
- **Benefits**: Loose coupling, automatic notifications

### 4. Strategy Pattern
- **PropertySearchStrategy**: Different search algorithms (status, price, average)
- **Benefits**: Interchangeable algorithms, extensible search functionality

### 5. Decorator Pattern
- **Deal decorators**: Add services to property deals dynamically
- **Benefits**: Flexible service composition, extensible service options

## SOLID Principles Implementation

### Single Responsibility Principle (SRP)
- **SystemManager**: Only manages system-wide operations
- **Property**: Only represents property data
- **UserFactory**: Only creates users
- **RadiusCalculator**: Only calculates distances

### Open/Closed Principle (OCP)
- **Strategy pattern**: New search strategies can be added without modifying existing code
- **Decorator pattern**: New services can be added without changing deal logic
- **Permission interfaces**: New permission types can be added easily

### Liskov Substitution Principle (LSP)
- **User hierarchy**: All user types can be used interchangeably where User is expected
- **Strategy implementations**: All search strategies can be substituted
- **Deal decorators**: All decorators can be used interchangeably

### Interface Segregation Principle (ISP)
- **Permission interfaces**: Separated ViewPermission, EditPermission, DeletePermission
- **Strategy interfaces**: Specific interfaces for different search types
- **Observer interfaces**: Focused on single responsibility

### Dependency Inversion Principle (DIP)
- **Strategy pattern**: High-level modules depend on abstractions, not concrete implementations
- **Factory pattern**: Depends on abstract User class, not concrete implementations
- **Observer pattern**: Depends on observer interface, not concrete observers

## OOP Principles Demonstrated

### Encapsulation
- **Property**: Private fields with controlled access through getters/setters
- **User**: Protected fields accessible only to subclasses
- **SystemManager**: Private constructor for singleton pattern

### Inheritance
- **User hierarchy**: Buyer, Seller, Broker extend User
- **DealDecorator**: All service decorators extend DealDecorator
- **Abstract classes**: DealDecorator provides common functionality

### Polymorphism
- **Strategy pattern**: Different implementations of PropertySearchStrategy
- **Decorator pattern**: Different deal decorators implementing Deal interface
- **User types**: Different user implementations with varying permissions

### Abstraction
- **Abstract User class**: Common user functionality without implementation
- **Interfaces**: Define contracts without implementation details
- **Strategy interfaces**: Abstract search algorithms

## Project Structure

```
src/
├── decorator/           # Decorator pattern for deal services
│   ├── BasicDeal.java
│   ├── CleaningDecorator.java
│   ├── Deal.java
│   ├── DealDecorator.java
│   ├── DesignDecorator.java
│   ├── EveningServicesDecorator.java
│   └── MovingDecorator.java
├── factory/            # Factory pattern for user creation
│   ├── Broker.java
│   ├── Buyer.java
│   ├── Seller.java
│   ├── User.java
│   └── UserFactory.java
├── main/               # Main system components
│   ├── SystemManager.java
│   ├── runningExample.java
│   └── prop1.txt
├── observer/           # Observer pattern for notifications
│   └── PropertyDeletionObserver.java
├── permission/         # Permission system interfaces
│   ├── DeletePermission.java
│   ├── EditPermission.java
│   └── ViewPermission.java
├── strategy/           # Strategy pattern for search algorithms
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
└── util/               # Utility classes
    ├── FileReaderProperty.java
    ├── Property.java
    └── RadiusCalculator.java
```

## System Requirements

- **Java 8+** (uses modern Java features like switch expressions)
- **JUnit 4** for testing
- **File system access** for reading property data

## Installation and Setup

1. **Clone or download** the project files
2. **Ensure Java 8+ is installed** on your system
3. **Compile the project**:
   ```bash
   javac -cp . src/**/*.java
   ```
4. **Run the example**:
   ```bash
   java -cp . src.main.runningExample
   ```



## Testing

The project includes comprehensive unit tests covering:

- **Property creation and validation**
- **User creation and permissions**
- **Search strategy functionality**
- **Deal decorator behavior**
- **File reading and parsing**
- **Radius calculation accuracy**

Run tests using your preferred Java testing framework or IDE.

## Implementation Highlights

### Error Handling
- **Comprehensive validation** for all inputs
- **Custom exceptions** for business logic violations
- **Graceful error handling** in file operations
- **Boundary condition checking** for searches

### Performance Considerations
- **Efficient Manhattan distance calculations**
- **Iterator pattern** for safe collection modification
- **Lazy loading** where appropriate
- **Memory-efficient copying** of collections

### Code Quality
- **Comprehensive JavaDoc documentation**
- **Consistent coding style**
- **Proper exception handling**
- **Thread-safe singleton implementation**

## Design Pattern Benefits

### Maintainability
- **Separation of concerns** through different patterns
- **Loose coupling** between components
- **Easy to extend** with new functionality

### Flexibility
- **Runtime strategy switching** for searches
- **Dynamic service composition** for deals
- **Configurable user permissions**

### Testability
- **Isolated components** for unit testing
- **Mock-friendly interfaces**
- **Predictable behavior** patterns

## Learning Outcomes

This project demonstrates:
- **Advanced OOP concepts** in a real-world scenario
- **Design pattern integration** in a cohesive system
- **SOLID principles** applied to complex requirements
- **Enterprise-level code organization**
- **Comprehensive testing strategies**

## Author

**Ido cohen**  
*idocohen963@gmail.com*  
[GitHub](https://github.com/idocohen963)  
[LinkedIn](https://www.linkedin.com/in/ido-cohen-14b8772b9/)

## License

This project is developed for educational purposes as part of an Object-Oriented Programming course.
