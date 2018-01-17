# MathREST
Test REST service for simple math operations and current time retrieval

### Version
0.1.0-SNAPSHOT

## Build
MathREST project relies on <b>gradle</b> as the build system. The project can be built by running the following command from the project root directory:
```
gradle build
```

## Package
MathREST can be packaged as a web archive or a docker image.
* The output web archive is assembled during project build execution. See [`Build`](#build) for details.
* The docker image can be assembled via running the following command from the project root directory:
```
gradle docker
```

## Run
To run MathREST service locally as a web container application, execute the following command from the project root directory:
```
java -jar build/libs/mathrest-<version>.war
```

To run MathREST service locally as a docker image, execute the following command from the project root directory:
```
docker run -d -p 8080:8080 --name <container_name> org.alexander1leonov/mathrest:<version>
```
The service can be accessed at port <b>8080</b>.

See [`Version`](#version) for version details.

## API Reference
This section describes operations supported by <b>MathREST</b>.

### GET /math/add
This operations evaluates a sum of 2 numbers provided in the request.

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
This operations evaluates a sum of numbers provided in the request.

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

### GET /time/now
This operations returns the current time in the format "HH:mm:ss zZ".

<b>TODO</b>: integrate the given operation with <i>developer.aero/WaitTime API</i>.
See also https://www.developer.aero/WaitTime-API/Try-it-Now.

* *Communication approach*: `Synchronous`
* *Request*
    * *Method*: **GET**
    * *URI*: `[BASE_URI]/time/now`
    * *Content-Type*: `application/json`
    * *Parameters*: <none>

* *Response*
    * *Response-Code*:
        * **200 OK** successfull response
        * **500 Internal Server Error** a server error occurred during execution
    * *Content-Type*: `application/json`
    * *Body*: [`/time/now`](#tn-response)

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

### /time/now <a name="tn-response">response body</a>

| &nbsp;    | Type | Cardinality | Sample data | Description | Notes |
| --------- | ----------- | ----------- | ----------- | ----------- | ----- |
| &nbsp;time | String | 1..1 | 10:07:31 MST-0700 | Time string in MST timezone containing local time and timezone information | |

```json
{
    "time":"10:07:31 MST-0700"
}
```

## Security
<b>TODO</b>: Implement security mechanism for the MathREST service.  
The following considerations can be taken into account during security design and implementation:
* A combination of SSL over HTTP (1-way) and an API key is enough suitable and convenient to serve the service security purpose;
* For MathREST as a private web service, SSL over HTTP (2-way) can be considered as an alternative in case of a predefined and limited set of service clients;
* Using credentials as an alternative to an API key has certain drawbacks, such as unnecessary exposure of credential data, additional requirements to credential complexity, extra efforts to handle password reset.

## Performance optimization
There is no performance optimization mechanisms introduced for the MathREST service yet, such as operation results caching.  
The following considerations may be taken into account in future implementation:
* Caching of <b>/math/add</b> operation results does not seem valueable as processing time benefits are doubtful:
    * for small numbers cached value retrieval is no better than the sum evaluation,
    * for big numbers cache hit rate expectation is quite low;
* Caching of <b>/time/now</b> operation does not seem possible due to an absence of any parameters to serve as a cache key.
