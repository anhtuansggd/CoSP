# CoSP
A Java-based web application that utilizes the Spring Boot framework for backend development. It
features a RESTful API for managing code snippets, which are stored as entities in a database.<br>
The application supports operations such as retrieving a specific code snippet by its UUID, and
fetching the latest code snippets. Each code snippet entity has properties such as time of creation,
time restriction, view restriction, and last accessed time.<br>
The application also includes exception handling for scenarios such as invalid UUID or exceeding
view restrictions.

#### API Documentation
##### Endpoints

<details>
 <summary><code>POST</code> <code><b>/api/code/new</b></code></summary>

##### Parameters

> | name      |  type     | data type  | description       |
> |-----------|-----------|------------|-------------------|
> | code      |  required | string     | Secret code       |
> | time      |  required | int        | Time in seconds   |
> | views     |  required | int        | Number of views   |

##### Request Body

```json
{
    "code": "Secret code",
    "time": 60,
    "views": 5
}
```

##### URL

http://localhost:8889/api/code/new

##### Responses

> | Code      |  Description     |
> |-----------|------------------|
> | 200       |  OK              |
```json
{
    "id": uuid
}
```

</details>

<details>
 <summary><code>GET</code> <code><b>/code/{id}</b></code></summary>

##### Parameters

> | name      |  type     | data type  | description       |
> |-----------|-----------|------------|-------------------|
> | id        |  required | string     | id                |


##### URL

http://localhost:8889/code/{id}

##### Responses

> | Code      |  Description     |
> |-----------|------------------|
> | 200       |  OK              |

Note: HTML will be returned.
</details>

<details>
 <summary><code>GET</code> <code><b>/api/code/{id}</b></code></summary>

##### URL

http://localhost:8889/api/code/{id}

##### Parameters

> | name      |  type     | data type  | description       |
> |-----------|-----------|------------|-------------------|
> | id        |  required | string     | id                |

##### Responses

> | Code      |  Description     |
> |-----------|------------------|
> | 200       |  OK              |
```json
{
    "code": string,
    "date": date type,
    "time": int,
    "views": int
}
```

</details>

<details>
 <summary><code>GET</code> <code><b>/api/code/latest</b></code></summary>
<br>No parameters

##### URL

http://localhost:8889/api/code/latest

##### Responses
> | Code      |  Description     |
> |-----------|------------------|
> | 200       |  OK              |
Notes: this endpoint return json object of all codes.
```json
{
    "code": string,
    "date": date type,
    "time": int,
    "views": int
}
```

</details>

<details>
 <summary><code>GET</code> <code><b>/code/latest</b></code></summary>
<br>No parameters

##### URL

http://localhost:8889/code/latest

##### Responses
> | Code      |  Description     |
> |-----------|------------------|
> | 200       |  OK              |
Notes: this endpoint return HTML of all codes.

</details>

<details>
 <summary><code>GET</code> <code><b>/code/new</b></code></summary>
<br>No parameters
  
##### URL

http://localhost:8889/code/new
##### Responses
> | Code      |  Description     |
> |-----------|------------------|
> | 200       |  OK              |
Notes: this endpoint return a form to input code.

</details>
