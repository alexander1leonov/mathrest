# mathrest
Test REST service for simple math operations

## Operations
This section describes operations supported by <b>mathrest</b>

### getSum
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
        * **400 Bad Request** some of the provided parameters are not valid numbers
        * **500 Internal Server Error** an server error occurred during execution
    * *Content-Type*: `application/json`
    * *Body*: [`TBD`](TBD)

### postSum
This operations evaluates a sum of numbers provided in the request

* *Communication approach*: `Synchronous`
* *Request*
    * *Method*: **POST**
    * *URI*: `[BASE_URI]/math/add`
    * *Content-Type*: `application/json`
    * *Body*: [`TBD`](TBD)

* *Response*
    * *Response-Code*:
        * **200 OK** successfull response
        * **400 Bad Request** some of the provided numbers are not valid
        * **500 Internal Server Error** an server error occurred during execution
    * *Content-Type*: `application/json`
    * *Body*: [`TBD`](TBD)

## Request / Response
This section describes <b>mathrest</b> request/response structure and  samples

### postSum request

TBD

### getSum/postSum response

TBD

