# Sample App using `jasypt-spring-boot-starter` dependency

 ## Asymmetric Encryption
 
 This app showcases the usage of asymmetric encryption/decryption using pair of PEM certificates
 
## Encryptable Property Sources enabled by:

In `pom.xml`:

```xml
    <dependency>
        <groupId>com.github.ulisesbocchio</groupId>
        <artifactId>jasypt-spring-boot-starter</artifactId>
        <version>1.10</version>
    </dependency>

```
The starter jar has a `spring.factories` definition that Spring Boot uses to bootstrap Autoconfiguration. The autoconfiguration
 simply loads the `@EnableEncryptableProperties` annotation that decorates all property sources in the Spring `Environment` to be
 encryptable.
 
 ## Custom Environment
 
 This app also showcases early initialization property encryption with a custom `Environment`:
 
 ```java
         new SpringApplicationBuilder()
                 .environment(new StandardEncryptableEnvironment())
                 .sources(SimpleStarterDemoApplication.class).run(args);
 ```
 
 **This is not required in most scenarios**
 
 ## Test jasypt-maven-plugin encryption
 ```bash
mvn jasypt:encrypt-value -Dspring.config.location="file:src/main/resources/application.yml" -Djasypt.encryptor.public-key-format="PEM" -Djasypt.encryptor.public-key-location="file:src/main/resources/publickey.pem" -Djasypt.plugin.value="theValueYouWantToEncrypt"
 ```
 
