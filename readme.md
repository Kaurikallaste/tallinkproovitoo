# Proovitöö REST API
Tallink proovitöö REST api for managing Conferences and Participants

GET /conferences 

returns all conferences as json

example response
```json
[
	{
		"id": 1,
		"name": "conference",
		"maxParticipants": 3,
		"participants": [
			{
				"id": 4,
				"name": "username"
			},
			{
				"id": 5,
				"name": "username"
			}
		]
	},
	{
		"id": 2,
		"name": "conference two",
		"maxParticipants": 3,
		"participants": [
			{
				"id": 6,
				"name": "username"
			}
		]
	},
	{
		"id": 3,
		"name": "conference three",
		"maxParticipants": 5,
		"participants": []
	}
]
```

GET /conferences/{id} - returns conference with given ID as json
example response
``` json
{
	"id": 2,
	"name": "conference two",
	"maxParticipants": 3,
	"participants": [
		{
			"id": 6,
			"name": "username"
		}
	]
}
```
GET /conferences/{id}/max_participants 
returns FALSE if conference max participants is reached otherwise returns TRUE
example response
``` json
true
```

POST /conferences
adds a participant to the database
example request
```json
{"name":"conference three", "maxParticipants" : 5}
```
example response
```json
{
	"id": 3,
	"name": "conference three",
	"maxParticipants": 5,
	"participants": null
}
```
DELETE /conferences/{id}
deletes the conference with the given ID, gives no response body on success
example response on ERROR
```json
{
	"timestamp": "2022-06-03T13:58:25.966+00:00",
	"status": 404,
	"error": "Not Found",
	"trace": "org.springframework.web.server.ResponseStatusException: 404 NOT_FOUND \"conference with id: 1555 not found\..."
	"message": "conference with id: 1555 not found",
	"path": "/conferences/1555"
}
```

POST /conferences/{id}/add_participant
adds a participant to a conference
example request
```json
{"name": "user"}
```

example response
```json
{"name": "user"}
```
