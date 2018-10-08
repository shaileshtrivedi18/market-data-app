You’re given a git repository with simple marketdata application which operates on qoutes and quote collections.

To run application, execute
  
```
> gradlew bootRun
```

Using this app, you can:

1. ask for latest quote for symbol, e.g. 'GOOG': ``` curl localhost:8080/quote/GOOG ```

output:
```
{
  "symbol" : "GOOG",
  "price" : 801.49
}
```

2. ask for latest quotes for collection of symbols: ```curl localhost:8080/collection/test-collection```
 
output: 
```
[ {
  "symbol" : "GOOG",
  "price" : 801.49
}, {
  "symbol" : "AAPL",
  "price" : 129.08
}, {
  "symbol" : "MSFT",
  "price" : 63.68
}, {
  "symbol" : "FB",
  "price" : 130.98
} ] 
```
3. create new or update existing collection with different symbols: ```curl --data "symbols=AAPL,GOOG" localhost:8080/collection/my-collection```
 
### The task.

Read the code through and fix and refactor anything you can find about logging and exception handlig functionality. 
Write a short note about what has been done and *why*.