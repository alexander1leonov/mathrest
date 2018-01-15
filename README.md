# MathREST
Test REST service for simple math operations

## Operations
This section describes operations supported by <b>MathREST</b>

### GET /math/add
This operations evaluates a sum of 2 numbers provided in the request

* *Communication approach*: `Synchronous`
* *Request*
    * *Method*: **GET**
    * *URI*: `[BASE_URI]/math/add`
    * *Content-Type*: `application/json`
    * *Parameters*:
        * **n1**: number parameter #1
        * **n2**: number parameter #2

* *Response*
    * *Response-Code*:
        * **200 OK** successfull response
        * **400 Bad Request** some of the provided numbers are not valid
        * **500 Internal Server Error** a server error occurred during execution
    * *Content-Type*: `application/json`
    * *Body*: [`/math/add`](#ma-response)

### POST /math/add
This operations evaluates a sum of numbers provided in the request

* *Communication approach*: `Synchronous`
* *Request*
    * *Method*: **POST**
    * *URI*: `[BASE_URI]/math/add`
    * *Content-Type*: `application/json`
    * *Parameters*:
        * **n1**: number parameter #1
        * **n2**: number parameter #2

* *Response*
    * *Response-Code*:
        * **200 OK** successfull response
        * **400 Bad Request** some of the provided numbers are not valid
        * **500 Internal Server Error** a server error occurred during execution
    * *Content-Type*: `application/json`
    * *Body*: [`/math/add`](#ma-response)

## Operations Response
This section describes response body structure and samples for <b>MathREST</b> operations

### /math/add <a name="ma-response">response body</a>

| &nbsp;    | Type | Cardinality | Sample data | Description | Notes |
| --------- | ----------- | ----------- | ----------- | ----------- | ----- |
| &nbsp;numbers | Array | 1..1 | [2.0, 3.0] | Array of numbers [n1, n2] provided in the request | |
| &nbsp;sum | Number | 1..1 | 5.0 | Result sum value  | |

```json
{
    "numbers": [2.0, 3.0],
    "sum": 5.0
}
```
