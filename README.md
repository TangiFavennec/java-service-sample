# java-service-sample
Java implementation of microservice

## Set up

### Prerequisite

Make sure the following are installed on your computer (For MacOS or Linus users, one handy way to install the following is to use brew (https://brew.sh/))

1) Java (version up to 8) : https://www.java.com/ja/
2) Ant : http://ant.apache.org/ 
3) Maven : https://maven.apache.org/

### Build the application

1) ```git clone``` this repository
2) run ```ant idea``` then run ```build```

Some additional targets for ant

- ```clean``` : clean binaries
- ```clean-lib``` : clean imported libraries
- ```clean-dist``` : clean project artifact
- ```clean-ivy``` : clean ivy cache
- ```clean-all``` : clean binaries, artifacts, imported libraries, ivy cache
- ```resolve``` : import and resolve dependencies (import from ivy)
- ```build``` : build binaries
- ```build-all``` :  import and resolve dependencies, build binaries, generate artifact


### Run the application

```./run```
