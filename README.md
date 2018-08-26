# OpenFaaS JEP template

## Pre-requisites

* Docker
* Kubernetes / Minikube & OpenFaaS (https://medium.com/devopslinks/getting-started-with-openfaas-on-minikube-634502c7acdf)
* faas-cli
* python3
* java8 / openjdk8 + maven (optional, to edit the template)

## Usage

```bash
faas-cli template pull https://github.com/chsfleury/JEPFaaS
faas-cli new --lang jep hello-jep
```

edit your ``hello-jep.yml``:
 * set the good gateway url 
 * add your dockerhub username as a prefix of the image name ``$USERNAME/hello-jep``

build the template to an docker image
```bash
faas-cli build -f hello-jep.yml
```

push the image to dockerhub (OpenFaaS pull from there)
```bash
docker login
faas-cli push -f hello-jep.yml
```

deploy to your OpenFaaS server
```bash
faas-cli deploy -f hello-jep.yml
```

when the function is ready, type
```bash
curl --request POST \
  --url http://$MINIKUBE_IP:$OPENFAAS_GATEWAY_PORT/function/hello-jep \
  --data 'Groot'
```
and get (with default handler.py)
```bash
python says: Hello Groot
```