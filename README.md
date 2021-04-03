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

* http endpoint accept string and publish it to stringSupplier function, which put it in `upperCase.topic` destination.
* an upperCase function, read the string from the `upperCase.topic` destination and convert it into person name with upper case and put it in `log.topic` destination.
* a log consumer read the person object from `log.topic` destination and log it to stdout.
