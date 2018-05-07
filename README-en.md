## Factom sdk 

Java client implementation of the [Factom web service api](https://docs.factom.com/api), and we add some external convenient functions.

 - create new chain
 - add entry
 - transfer factoshis 

___

### Building

This project is built with Apache Maven.  *Mininum compatible version of Java is 1.8.* 

To build:

`$ mvn clean install`


### Basic Usage


Create `CloseableFactomClient` instance:
```java
String factomdURI = "fatomd server uri";
String walletdURI = "walletd server uri";
CloseableFactomClient client = FactomClients.create(FactomURI.create(factomdURI, walletdURI));
```


Create new chain:

```java
String ecAddress = "EC2xFxCzppZD1wjetxCPdLsTd3xbs8BTQKny63GnuEGDLAgXLu8z";
ComposeChainParam composeChainParam = new ComposeChainParam(ecAddress,"sha256hex-content", Arrays.asList("wancloud","factom","api","testing","101")
EntryInfo entryInfo = client.addChain(composeChainParam);
``` 


Save entry with:

```java
String chainId = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
ComposeEntryParam entryParam = new ComposeEntryParam(chainId,
        "EC2wMFzvcAbjJeVWRUZJEcKp8S37AhZu8ZMjyrkheyTS8WZnt9Hh", "haha", Arrays.asList("add-entry-test","1001"));
EntryInfo entryInfo = client.addEntry(entryParam);
```


Buy Entry Credit:
```java
String factoidAddress = "FA2jK2HcLnRdS94dEcU27rF3meoJfpUcZPSinpb7AwQvPRY6RL1Q";
String ecAddress = "EC2xFxCzppZD1wjetxCPdLsTd3xbs8BTQKny63GnuEGDLAgXLu8z";
client.buyEntryCredit(factoidAddress, ecAddress, 8000L);
```


Transfer factoshis (one input to one output):

```java
String inputAddress1 = "FA2jK2HcLnRdS94dEcU27rF3meoJfpUcZPSinpb7AwQvPRY6RL1Q";
String outputAddress1 = "FA2hjXMAkwJjNFn9512AMExEtUU55QB7KdcFbm3MrMNrS5SxBw81";
client.transferFactoshis(inputAddress1, outputAddress1, 80000000L,  true);
```


Transfer factoshis (muiltple inputs to muiltple ouputs):

```java
String inputAddress1 = "FA2jK2HcLnRdS94dEcU27rF3meoJfpUcZPSinpb7AwQvPRY6RL1Q";
String inputAddress2 = "FA2hjXMAkwJjNFn9512AMExEtUU55QB7KdcFbm3MrMNrS5SxBw81";
String outputAddress1 = "FA2hjXMAkwJjNFn9512AMExEtUU55QB7KdcFbm3MrMNrS5SxBw81";

AddressAmount input1 = new AddressAmount(inputAddress1, 4000L);
AddressAmount input2 = new AddressAmount(inputAddress2, 4000L);
AddressAmount output1 = new AddressAmount(outputAddress1, 8000L);

String txId = client.transferFactoshis(Arrays.asList(input1, input2), Arrays.asList(output1), inputAddress1);
```


Use `FluentWait<T>`:

```java
//loop retrive entry by entryhash, throw TimeOutException if 30 seconds timed out.
EntryResult result =new FluentWait<FactomClient>(client, 1500)
    .withTimeOut(30, TimeUnit.SECONDS)
    .until((Function<ClientCommand,EntryResult>) c -> { 
        try{
            return c.getEntry(entryHash);
        }catch(FactomException ignored){
            // can't find entry
        }
        return null;
    });
```


