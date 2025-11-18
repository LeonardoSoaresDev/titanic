# Titanic Passenger API

This project features a simple **RESTful API**, developed using **Kotlin** and **Spring Boot**, designed to serve data about the passengers of the Titanic. It includes capabilities for data filtering and pagination.

---

## Requirements

The API was built to meet the following core criteria:

* **RESTful API:** Provide endpoints to serve information about the Titanic passengers.
* **Data Storage:** Utilize an **embedded database** (such as H2 or similar) for data persistence.
* **Filtering:** The main listing endpoint must support filtering on specific columns:
    * **`survived`**: Filter by survival status (0 = Did Not Survive, 1 = Survived).
    * **`Pclass`**: Filter by passenger class (1, 2, or 3).

---

## 🔗 API Endpoints

The following endpoints are exposed by the API:

### 1. Get All Passengers

Retrieves a paginated list of passengers with optional filtering capabilities.

| Detail | Description |
| :--- | :--- |
| **URL** | `{baseUrl}/passengers` |
| **Method** | `GET` |
| **Description** | Returns a paginated list of passengers filtered by optional criteria. |

#### Query Parameters (Optional)

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `page` | `int` | Page number to retrieve (Default: 0) |
| `size` | `int` | Page size (number of items per page)  |
| `survived` | `int` | Filter by survival status (0 or 1) |
| `pclass` | `int` | Filter by passenger class (1, 2, or 3) |

#### Example Request

```bash
curl -X GET "{baseUrl}/passengers?survived=1&pclass=1&page=0&size=10"
```

### 2. Get Passenger by ID

Retrieves the details for a specific passenger using their ID.

| Detail | Description |
| :--- | :--- |
| **URL** | `{baseUrl}/passengers/{id}` |
| **Method** | `GET` |
| **Description** | Returns a single passenger based on the provided ID. |

#### Path Parameter

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `id` | `long` | Unique identifier of the passenger. |

#### Example Request

```bash
curl -X GET "{baseUrl}/passengers/2"
```
