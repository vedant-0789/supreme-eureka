# \ud83d\udef0 Supreme-Eureka - SatOps-DSL

[![GitHub stars](https://img.shields.io/github/stars/sam1302-sks/supreme-eureka?style=flat-square&color=blue)](https://github.com/sam1302-sks/supreme-eureka)
[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg?style=flat-square)](LICENSE)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.8%2B-purple.svg?style=flat-square)](https://kotlinlang.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0%2B-orange.svg?style=flat-square)](https://spring.io/projects/spring-boot)

A lightweight, extensible **Domain-Specific Language (DSL)** simulator for satellite operations, mission planning, and resource management. Built with **Kotlin**, **Spring Boot**, **ANTLR4**, and **Orekit**, this project enables mission control teams to define, parse, and execute complex satellite operations using intuitive DSL commands.

## \ud83c\udf1f Overview

Supreme-Eureka provides a powerful framework for:
- Defining satellite missions through custom DSL syntax
- Simulating satellite orbits and ground station communications
- Managing mission control workflows
- Integrating with real satellite data (Celestrak TLEs)
- Extending with custom operations and optimization algorithms

Perfect for aerospace engineers, mission planners, and satellite operators who need a flexible, hackable simulation platform.

## \ud83d\ude80 Features

### \ud83d\udcdc Custom DSL for Satellite Operations
- **Intuitive syntax** for defining satellite missions
- Deploy satellites and ground stations
- Establish communication links between satellites and stations
- Send and receive messages with validation
- Execute complex mission scripts sequentially
- Real-time status tracking and logging

### \ud83d\udef0 Real Satellite Integration
- **Celestrak TLE Data**: Fetch real Two-Line Element sets for actual satellites
- **Orekit Integration**: Accurate orbital mechanics and propagation
- **Visibility Computation**: Calculate communication windows between satellites and ground stations
- **Position Simulation**: Track satellite positions in real-time

### \ud83d\uddd1\ufe0f Mission Control Engine
- Parse and validate DSL scripts
- Execute mission scenarios with error handling
- Validate visibility before sending messages
- Built-in scheduling support
- Extensible command framework

### \ud83d\udd27 Extensible Backend Architecture
- Built on **Spring Boot** for enterprise-grade reliability
- **ANTLR4** grammar for robust parsing
- RESTful HTTP API for remote mission control
- Ready for OptaPlanner integration for optimization
- Prepared for database persistence (JPA/Hibernate)
- Kafka support for real-time event streaming

## \ud83d\udcda Tech Stack

| Technology | Version | Purpose |
|-----------|---------|----------|
| Kotlin | 1.8+ | Primary language |
| Spring Boot | 3.0+ | Application framework |
| Spring Web | Latest | REST API framework |
| ANTLR4 | 4.13+ | DSL parsing |
| Orekit | 12.0+ | Orbital mechanics |
| JUnit 5 | Latest | Testing framework |
| Maven | 3.8+ | Build automation |

## \ud83d\ude80 Quick Start

### Prerequisites
- **Java 17+** or **Kotlin 1.8+**
- **Maven 3.8+**
- **Git**

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/sam1302-sks/supreme-eureka.git
   cd supreme-eureka
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**
   ```
   API Endpoint: http://localhost:8080
   ```

## \ud83d\udcdd Usage Examples

### Basic DSL Syntax

```
deploy satellite Sat1 at Celestrak:STARLINK-1130
deploy groundstation GS1 at "Pune, India"

link Sat1 to GS1
send "Hello from Ground Control!" from GS1 to Sat1
```

### HTTP API Request

**POST** `/api/missions/execute`

```json
{
  "dslScript": "deploy satellite Sat1 at Celestrak:ISS\ndeploy groundstation GS1 at \"Kennedy Space Center\"\nlink Sat1 to GS1\nsend \"Mission Start\" from GS1 to Sat1",
  "missionName": "ISS_Communication",
  "simulationTime": "2025-01-01T00:00:00Z"
}
```

**Response:**

```json
{
  "missionId": "mission_12345",
  "status": "SUCCESS",
  "executedCommands": 4,
  "results": [
    {
      "command": "deploy",
      "entity": "Sat1",
      "status": "DEPLOYED",
      "timestamp": "2025-01-01T00:00:01Z"
    },
    {
      "command": "link",
      "fromEntity": "Sat1",
      "toEntity": "GS1",
      "status": "LINKED",
      "visibility": "AVAILABLE"
    }
  ]
}
```

## \ud83d\udcbc Project Structure

```
supreme-eureka/
\u251c\u2500\u2500 src/
\u251c\u2502\u2502\u2502  \u251c\u2500\u2500 main/
\u251c\u2502\u2502\u2502  \u2502  \u251c\u2500\u2500 kotlin/
\u251c\u2502\u2502\u2502  \u2502  \u2502  \u251c\u2500\u2500 com/satops/
\u251c\u2502\u2502\u2502  \u2502  \u2502  \u2502  \u251c\u2500\u2500 dsl/          (DSL parser & interpreter)
\u251c\u2502\u2502\u2502  \u2502  \u2502  \u2502  \u251c\u2500\u2500 satellite/     (Satellite domain models)
\u251c\u2502\u2502\u2502  \u2502  \u2502  \u2502  \u251c\u2500\u2500 groundstation/ (Ground station models)
\u251c\u2502\u2502\u2502  \u2502  \u2502  \u2502  \u251c\u2500\u2500 mission/       (Mission management)
\u251c\u2502\u2502\u2502  \u2502  \u2502  \u2502  \u251c\u2500\u2500 api/           (REST controllers)
\u251c\u2502\u2502\u2502  \u2502  \u2502  \u2502  \u2514\u2500\u2500 service/       (Business logic)
\u251c\u2502\u2502\u2502  \u2502  \u251c\u2500\u2500 resources/
\u251c\u2502\u2502\u2502  \u2502  \u2502  \u2514\u2500\u2500 application.properties
\u251c\u2502\u2502\u2502  \u2514\u2500\u2500 test/          (JUnit 5 tests)
\u251c\u2500\u2500 pom.xml                   (Maven configuration)
\u251c\u2500\u2500 .github/workflows/        (GitHub Actions CI/CD)
\u251c\u2500\u2500 README.md                 (This file)
\u251c\u2500\u2500 LICENSE                   (Apache 2.0)
\u251c\u2500\u2500 .gitignore
\u2514\u2500\u2500 Dockerfile               (Docker support)
```

## \ud83e\uddd11\ufe0f DSL Grammar Overview

The Supreme-Eureka DSL supports the following operations:

### Deploy Command
```
DEPLOY SATELLITE <name> AT <source>
DEPLOY GROUNDSTATION <name> AT <location>
```

### Link Command
```
LINK <satellite> TO <groundstation>
```

### Send Command
```
SEND "<message>" FROM <source> TO <destination>
```

### Query Command
```
QUERY VISIBILITY <satellite> TO <groundstation>
```

## \ud83d\udd27\ufe0f Configuration

### Application Properties

**application.properties:**
```properties
spring.application.name=supreme-eureka
server.port=8080

# Orekit Configuration
orekit.tle.source=CELESTRAK
orekit.propagator.type=SSPROP

# Logging
logging.level.root=INFO
logging.level.com.satops=DEBUG
```

## \ud83e\uddea API Endpoints

### Mission Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/missions/execute` | Execute a DSL script |
| GET | `/api/missions/{id}` | Get mission details |
| GET | `/api/missions/{id}/results` | Get mission execution results |
| DELETE | `/api/missions/{id}` | Cancel/delete a mission |

### Satellite Operations

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/satellites/deploy` | Deploy a satellite |
| GET | `/api/satellites` | List all deployed satellites |
| GET | `/api/satellites/{id}` | Get satellite details |
| GET | `/api/satellites/{id}/position` | Get satellite current position |

### Ground Station Operations

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/groundstations/deploy` | Deploy a ground station |
| GET | `/api/groundstations` | List all ground stations |
| GET | `/api/groundstations/{id}` | Get ground station details |

## \ud83e\uddea Testing

Run all tests with:
```bash
mvn test
```

Run specific test class:
```bash
mvn test -Dtest=DSLParserTest
```

Generate coverage report:
```bash
mvn jacoco:report
```

## \ud83d\udcc4 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork the repository**
   ```bash
   git clone https://github.com/YOUR-USERNAME/supreme-eureka.git
   cd supreme-eureka
   ```

2. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```

3. **Commit your changes**
   ```bash
   git add .
   git commit -m "Add amazing feature"
   ```

4. **Push to the branch**
   ```bash
   git push origin feature/amazing-feature
   ```

5. **Open a Pull Request**
   - Describe your changes clearly
   - Link any related issues
   - Ensure all tests pass

## \ud83d\udcab Support & Community

- **GitHub Issues**: [Report bugs or request features](https://github.com/sam1302-sks/supreme-eureka/issues)
- **Discussions**: [Ask questions and share ideas](https://github.com/sam1302-sks/supreme-eureka/discussions)
- **Documentation**: [View detailed docs](https://github.com/sam1302-sks/supreme-eureka/wiki)

## \ud83d\udcc3 License

This project is licensed under the **Apache License 2.0** - see the [LICENSE](LICENSE) file for details.

```
Copyright 2024 Supreme-Eureka Contributors

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## \ud83c\udf1f Acknowledgments

- **Orekit**: For accurate orbital mechanics calculations
- **Celestrak**: For providing real satellite TLE data
- **Spring Boot**: For the robust application framework
- **ANTLR4**: For powerful parsing capabilities
- **Kotlin**: For expressive and safe language features
- All contributors and supporters of this project

## \ud83d\ude80 Roadmap

### Version 1.1 (In Progress)
- [ ] Enhanced DSL grammar with variables and functions
- [ ] Kafka integration for event streaming
- [ ] Database persistence layer (PostgreSQL)
- [ ] Web UI dashboard for mission visualization

### Version 1.2 (Planned)
- [ ] OptaPlanner integration for mission optimization
- [ ] Machine learning models for anomaly detection
- [ ] Advanced scheduling algorithms
- [ ] GraphQL API support

### Version 2.0 (Future)
- [ ] Mobile app for field operations
- [ ] Real-time collaboration features
- [ ] Multi-tenant architecture
- [ ] Advanced analytics and reporting

---

**Built with \u2764\ufe0f by the Supreme-Eureka Team**

GitHub: [@sam1302-sks](https://github.com/sam1302-sks)
