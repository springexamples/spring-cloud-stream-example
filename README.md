## spring-cloud-stream example

### To trigger the stream:

```
curl localhost:8080/<some value>
```
e.g.:
```
curl -XGET http://localhost:8080/this%20this%20sparta
``` 


### what is happening:

```
http > stringSupplier > upperCase > log

```  

* http endpoint accept string and put it in a queue.
* an upperCase function, read the string from the queue and convert it into person name with upper case and put it in another queue.
* a log consumer read the person object from the second queue and log it.
