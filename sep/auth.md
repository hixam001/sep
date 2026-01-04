# Authentication (Auth)

## Login

- Endpoint: `POST /api/auth/login`
- Payload (application/json):

  {
    "cnic": "string",
    "password": "string",
    "role": "REPRESENTATIVE|PRODUCTION_MANAGER|STOCK_MANAGER"
  }

- Success (200): returns the user object (a subset of `BaseUser`).
- Failure (401): returns a plain-text message `Invalid Credentials`.

Notes:
- The backend currently performs a simple login by matching `cnic`, `password` and `role` against role-specific repositories. There is no JWT or session token implemented in this codebase.
- For production, replace with secure password hashing and token-based authentication (e.g., JWT) and add `Authorization: Bearer <token>` header requirements in the OpenAPI spec.
