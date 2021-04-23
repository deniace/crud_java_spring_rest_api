# CRUD Rest api (java Spring boot)
aplikasi rest api dibuat menggunakan java spring boot, PostgreSQL

```
get all data
method = GET
url = localhost:8080/api/tutorials

get data data by id
method = GET
url = localhost:8080/api/tutorials/{id}

insert new data
method = POST
url = localhost:8080/api/tutorials
request body = {"title":"title","description":"description"}

update data
method = PUT
url = localhost:8080/api/tutorials/{id}
request body = {"title":"title","description":"description", "published":true}

delete data by id
method = DELETE
url = localhost:8080/api/tutorials/{id}

delete all data
method = DELETE
url = localhost:8080/api/tutorials

get published data
method = GET
url = localhost:8080/api/tutorials/published
```

![Screenshoot](https://github.com/deniace/crud_java_spring_rest_api/blob/master/screenshot/get.PNG)
![Screenshoot2](https://github.com/deniace/crud_java_spring_rest_api/blob/master/screenshot/post.PNG)
