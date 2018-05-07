## Factom sdk 

该项目是[Factom Web Service API](https://docs.factom.com/api)的一个Java实现， 同时添加了一些便捷方法。

 - 创建新链
 - 添加条目
 - 账户间转账

___

### 项目构建

该项目是使用Apache Maven作为构建工具，  **最小JDK版本为1.8。** 

构建命令:

`$ mvn clean install`


### 基本使用


创建客户端  `CloseableFactomClient` 实例：
```java
String factomdURI = "fatomd server uri";
String walletdURI = "walletd server uri";
CloseableFactomClient client = FactomClients.create(FactomURI.create(factomdURI, walletdURI));
```


创建新链：

```java
String ecAddress = "EC2xFxCzppZD1wjetxCPdLsTd3xbs8BTQKny63GnuEGDLAgXLu8z";
ComposeChainParam composeChainParam = new ComposeChainParam(ecAddress,"sha256hex-content", Arrays.asList("wancloud","factom","api","testing","101")
EntryInfo entryInfo = client.addChain(composeChainParam);
``` 


创建新条目：

```java
String chainId = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
ComposeEntryParam entryParam = new ComposeEntryParam(chainId,
        "EC2wMFzvcAbjJeVWRUZJEcKp8S37AhZu8ZMjyrkheyTS8WZnt9Hh", "haha", Arrays.asList("add-entry-test","1001"));
EntryInfo entryInfo = client.addEntry(entryParam);
```


购买条目信用：
```java
String factoidAddress = "FA2jK2HcLnRdS94dEcU27rF3meoJfpUcZPSinpb7AwQvPRY6RL1Q";
String ecAddress = "EC2xFxCzppZD1wjetxCPdLsTd3xbs8BTQKny63GnuEGDLAgXLu8z";
client.buyEntryCredit(factoidAddress, ecAddress, 8000L);
```


账户之间转 factoshis (一个输入账户一个输出账户)：

```java
String inputAddress1 = "FA2jK2HcLnRdS94dEcU27rF3meoJfpUcZPSinpb7AwQvPRY6RL1Q";
String outputAddress1 = "FA2hjXMAkwJjNFn9512AMExEtUU55QB7KdcFbm3MrMNrS5SxBw81";

client.transferFactoshis(inputAddress1, outputAddress1, 80000000L,  true);
```


账户之间转 factoshis (多个输入账户多个输出账户)：

```java
String inputAddress1 = "FA2jK2HcLnRdS94dEcU27rF3meoJfpUcZPSinpb7AwQvPRY6RL1Q";
String inputAddress2 = "FA2hjXMAkwJjNFn9512AMExEtUU55QB7KdcFbm3MrMNrS5SxBw81";
String outputAddress1 = "FA2hjXMAkwJjNFn9512AMExEtUU55QB7KdcFbm3MrMNrS5SxBw81";

AddressAmount input1 = new AddressAmount(inputAddress1, 4000L);
AddressAmount input2 = new AddressAmount(inputAddress2, 4000L);
AddressAmount output1 = new AddressAmount(outputAddress1, 8000L);

String txId = client.transferFactoshis(Arrays.asList(input1, input2), Arrays.asList(output1), inputAddress1);
```


使用 `FluentWait<T>`工具类轮询条件等待：

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


