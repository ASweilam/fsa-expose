# fsa-expose
An API using a database populated with data from Food Standard Agency (FSA) as datasource.

This is the second component of the FSA microservices application. It utilises the database
created from [fsa-persist](https://github.com/ASweilam/fsa-persist) microservice.

## What is the purpose?
This API uses a database as a datasource. This can provide high availability and less dependency on third-party API
reducing the risk of the service becoming unavailable or can serve as a back-up strategy.
The data stored in the database are from Food Standards Agency (FSA).

The API allows CRUD operations (Create, Read, Update and Delete) on both establishment details and local authorities records.

## Endpoints

- To view all the Establishment Details records and create (post) a new establishment detail:
```bash
http://localhost:8080/api/v1/details

Create ==> POST request to the URL with the new record in the body of the request
```

- To view/create all the Local Authorities records and create (post) a new Local Authority:
```bash
http://localhost:8080/api/v1/authority

Create ==> POST request to the URL with the new record in the body of the request
```

- To view/delete/update a specific establishment detail record using the FHRSID:
```bash
http://localhost:8080/api/v1/details/{fhrs_id}

Update ==> PUT request with updated record in the body of the request
Delete ==> DELETE request

```

- To view/delete/update a specific local authority record using the local authority code:
```bash
http://localhost:8080/api/v1/authority/{code}
```

## The data

The data is in JSON format. There's one embedded JSON object, Local Authority.

```json
{
  "fhrs_id": 55555,
  "local_authority_business_id": "PI/123456",
  "business_name": "The good council",
  "business_type": "Restaurant/Cafe/Canteen",
  "business_type_id": 1,
  "address_line_1": null,
  "address_line_2": "the second address line",
  "address_line_3": "the third address line",
  "address_line_4": "Good Ville",
  "postcode": "SA2 9UM",
  "rating_value": "Pass",
  "rating_key": "fhis_pass_en-GB",
  "rating_date": "2020-02-12",
  "scores": "",
  "scheme_type": "FHIS",
  "new_rating_pending": false,
  "longitude": -24.782873,
  "latitude": 68.016292,
  "localAuthority": {
    "code": 777,
    "name": "Good Ville",
    "website": "http://www.gv.gov.uk/",
    "email": "gv@good.gov.uk"
  }
}
```

## Installation

- Clone the project.

```bash
$ git clone https://github.com/ASweilam/fsa-expose.git
```

- The project uses MySql as a datasource. Download and install [MySql](https://dev.mysql.com/downloads/installer/)
and [MySQL Community Server](https://dev.mysql.com/downloads/mysql/)

- Go to application.properties file inside the project to change the database username and password to correspond to your setup of the database.

- Make sure to install the Maven dependencies before running the application
```bash
mvn clean install
```

## Contributing
Pull requests are welcome.


## License
[MIT](https://choosealicense.com/licenses/mit/)
