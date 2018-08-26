#!/bin/bash

mvn clean package && cp ./target/jepfaas.jar ./template/jep/ && chmod +x ./template/jep/jepfaas.jar