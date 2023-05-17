# Interval Calculator

Interval CRUD API

This project is generated with [Spring Initializr](https://start.spring.io) using Maven project.

The scope of this project is to provide a secured REST API for performing CRUD operations on intervals using JSON data.

## Input Data

The input data for the API calls should be in the following format:

```json
[
    {
        "id": 0,
        "start": "16/05/2023 08:22:07",
        "end": "16/05/2023 12:10:14"
    },
    {
        "id": 2,
        "start": "16/05/2023 12:44:00",
        "end": "16/05/2023 13:55:00"
    }
]
```

## Output Data

The output data from the API calls will be in the following format:

```json
[
  {
    "id": 0,
    "start": "16/05/2023 05:22:07",
    "end": "16/05/2023 09:10:14",
    "duration": "3h48m"
  },
  {
    "id": 2,
    "start": "16/05/2023 09:44:00",
    "end": "16/05/2023 10:55:00",
    "duration": "1h11m",
    "break": "33m46s"
  }
]
```
using the provided API.

### API Endpoints

The API provides the following endpoints for CRUD operations on intervals:

- `GET /intervals`: Retrieve all intervals.
- `GET /intervals/{id}`: Retrieve a specific interval by ID.
- `POST /intervals`: Create a new interval.
- `PUT /intervals/{id}`: Update an existing interval.
- `DELETE /intervals/{id}`: Delete an interval.

### Request Examples

#### Retrieve all intervals

- Endpoint: `GET /intervals`
- Description: Retrieves all intervals.
- Example Request:

### API Authentication
To make API calls, include the following authentication information in the request headers:

#### Header: api_key
#### Value: test_around
Make sure to set the api_key header with the provided value in your API requests.

**Example API Call**

To retrieve all intervals from the API, you can use the following HTTP request:


Replace `localhost:8080` with the appropriate hostname and port number for your API endpoint. Make sure to set the `api_key` header with the provided value in your API requests.

Alternatively, you can use the following cURL command:

```bash
curl -X GET -H "api_key: test_around" http://localhost:8080/intervals

```
