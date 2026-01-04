# Error Handling

This document describes common error responses produced by the API.

Common HTTP status codes used:

- 200 OK: Successful operation (GET/POST responses when returning resource)
- 201 Created: Resource created (where applicable)
- 400 Bad Request: Input validation or malformed payloads
- 401 Unauthorized: Authentication failed (e.g., login failed)
- 404 Not Found: Requested resource does not exist (repositories use Optional.orElseThrow())
- 500 Internal Server Error: Unhandled exceptions or server errors

Error response body conventions
- The API may return plain strings in some controllers (e.g., `Invalid Credentials`).
- It's recommended to standardize on a JSON error envelope for clients, for example:

```
{
  "timestamp": "2026-01-02T12:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed: 'name' is required",
  "path": "/api/inventory"
}
```

Recommendations
- Add a `@ControllerAdvice` to convert exceptions (e.g., EntityNotFoundException, IllegalArgumentException) to structured JSON error responses.
- Validate request bodies with `@Valid` and DTOs to return `400` with detailed validation messages.
