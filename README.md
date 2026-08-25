
# MediCare Patient & Bed Management System
## Overview

The MediCare Patient & Bed Management System is a Java console application designed to handle healthcare data logic, including patient registration, categorization, dynamic sorting, and ward bed allocations. It separates data models, business logic services, and user interfaces using Object-Oriented Programming (OOP) principles.

## Usage Guide
### Running the Application
> Compile and run Main.java

> Use the interactive menu to register, update, delete, view patient details

> Use the interactive menu to assign beds to INPATIENTS, view ward layout, release beds

> Use the interactive menu to view summary statistics, view bed report, view patient report

## Running Unit Tests

The unit tests validate core functionality such as duplicate ID prevention, ward capacity limits, and bed assignments.

### Important Note for Unit Tests:
>
## Folder Structure
```
src/
└── com/
    └── medicare/
        ├── model/
        │   ├── Patient.java             # Base model for patient attributes
        |   ├── Bed.java                 # Base model for Bed attributes
        │   ├── Inpatient.java           # Extended model with ward and bed details
        │   └── PatientCategory.java     # Enum (INPATIENT, OUTPATIENT, EMERGENCY)
        ├── service/
        │   ├── PatientManager.java      # Handles patient CRUD operations & sorting
        │   └── BedManager.java          # Handles ward capacity & bed allocations
        └── Main.java                    # Console interface & entry point

test/
└── com/
    └── medicare/
        └── service/
            ├── PatientManagerTest.java  # Unit tests for patient logic
            └── BedManagerTest.java      # Unit tests for ward bed logic
```

## Dependency Management
The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
