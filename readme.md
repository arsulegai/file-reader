# File Reader Service

The scope of the application is to read the files
recursively using the configured connection parameters
for the specified storage.

The stored file content is then added as metadata in the
RDBMS and sent for indexing on an external storage solution
such as elasticsearch.

## Build

Run following commands

1. Format the code
```shell
mvn fmt:format
```
2. Clean, compile the code
```shell
mvn clean package
```
