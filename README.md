
### Prerequisites:

Run MariaDB with:
```
docker-compose up
```

## Backend REST API

#### Running instructions:


- Run SpringBoot application **ExerciseApplication**

Endpoint can be reached at: 

```
http://localhost:8080/api/cities?name={name}&page={pageNumber}&size={pageSize}
```

Please keep in mind `name`, `page` and `size` are optional parameters and that they may be discarded to retrieve all cities

### Combination exercise

- Run **CityIdCombinationTest** to retrieve the longest sorted id sequence based on the city id ordered by name

## Frontend Angular

#### Running instructions:

- Build and run Angular application with:

```
ng serve
```

Available pages within the site: 

```
http://localhost:4200
http://localhost:4200/cities
```