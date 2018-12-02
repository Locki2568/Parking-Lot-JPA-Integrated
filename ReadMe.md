## Story1
### AC1
Given: A parking boy with Json body
```json
{
employeeID : String
}
```
When : POST to /parkingboys

Then: Should return status code 201
### AC2
When: GET from /parkingboys

Then: Should return a Json with list of parking boys
```json
{
 [
  {
    employeeID : String
  }
  ...
 ]
}
```

## Story 2
### AC1
Given: A parking lot with Json body
```json
{
parkingLotID : String,
capacity : int
}
```
When: POST to `/parkinglots`

Then: Should return status code 201

### AC2
When: GET from `/parkinglots`

Then: Should return a list containing all parking lots
```json
[
 {
  parkingLotID : String,
  capacity : int
 }...
]
```
## Story 3
### AC1
Given: A parking boy and a parking lot in DB. A employee ID and A parking lot in body
```json
{
  parkingLotID : String,
  capacity : int
}
```
When: PUT to ``/parkingboys/{employeeID}``

Then: Should return status code 200

### AC2
Given: A employee ID
When: GET from ``/parkingboy/{employeeID}``
Then: Should return status code 200.
