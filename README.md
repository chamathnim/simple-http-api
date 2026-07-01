# Hello World API

A simple Spring Boot REST API with one endpoint: `GET /hello-world`.

## Endpoint

`GET /hello-world?name={name}`

- If `name` starts with **A–M** (case-insensitive): `200 OK` → `{ "message": "Hello Alice" }`
- If `name` starts with **N–Z**: `400 Bad Request` → `{ "error": "Invalid Input" }`
- If `name` is missing or empty: `400 Bad Request` → `{ "error": "Invalid Input" }`

## How to Run

```bash
mvn spring-boot:run
```

App runs on `http://localhost:8080`.

```bash
curl "http://localhost:8080/hello-world?name=alice"
# {"message":"Hello Alice"}
```

## How to Run Tests

```bash
mvn test
```

## Assumptions

- Alphabet check is case-insensitive (`Alice` and `alice` behave the same).
- The name in the response is normalized (first letter capitalized, rest lowercase).
- Whitespace-only names are treated as missing.
- A name starting with a non-letter character is treated as invalid input.
