# AREP - LAB 6


## Getting Started

### Pre-requisites

You need to have installed on your PC:

- JDK 
- Maven 
- Git
- Docker

Also, be aware of having an account in AWS

### Installing

Open a command prompt on the folder that you are going to save this project and copy:

```
git clone https://github.com/CarlosGomez380/arep-lab7.git
```

And copy:

```
mvn clean install
```

Stablish conection with ypur EC2 instance

Get the containers, get the same ports as you'll see below:

To see your containers:

```
docker ps
```

Enable ports that are going to use for each service, go to the EC2's security group an open them


## Documentation JavaDoc

To see the javadoc generated copy:

```
mvn javadoc:javadoc
```

This document will be located in /target/site

## Execution


If we try to login with a user and passwrod different from "root" this is going to be the error:

![](https://github.com/CarlosGomez380/arep-lab7/blob/main/imag/accesofallido.PNG)

![](https://github.com/CarlosGomez380/arep-lab7/blob/main/imag/resultadoDenegado.PNG)

Otherwise, the login will be successful and can do the operation that you want:

![](https://github.com/CarlosGomez380/arep-lab7/blob/main/imag/acceso.PNG)

![](https://github.com/CarlosGomez380/arep-lab7/blob/main/imag/calcular.PNG)

![](https://github.com/CarlosGomez380/arep-lab7/blob/main/imag/resultado.PNG)

## Built With

- [Maven](https://maven.apache.org/) - Dependency Management
- [Docker](https://www.docker.com/) - Container creator
- [Spark](http://sparkjava.com/) - framework

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
