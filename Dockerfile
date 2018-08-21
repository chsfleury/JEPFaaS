FROM openjdk:8

RUN apt-get update
RUN apt-get install -y python3-pip
RUN pip3 install jep
RUN mkdir -p /lib
RUN pip3 show jep | grep Location
ENV LD_PRELOAD /usr/lib/x86_64-linux-gnu/libpython3.5m.so.1.0

RUN pip3 install  numpy
RUN pip3 install  pymc3
RUN pip3 install six
RUN pip3 install urllib3
RUN pip3 install pandas
RUN pip3 install requests
RUN pip3 install urllib3
RUN pip3 install six
RUN pip3 install lxml
RUN pip3 install JPype1==0.6.2
RUN pip3 install jep
RUN pip3 install baostock
RUN pip3 install bs4
RUN pip3 install tushare
RUN pip3 install numpy
ENV JAVA_OPTS  "-Djava.library.path=/usr/local/lib/python3.5/dist-packages/jep"

COPY ./target/jepfaas.jar /data/jepfaas.jar
COPY ./python/script.py /data/script.py
WORKDIR /data

CMD /usr/bin/java -Djava.library.path=/usr/local/lib/python3.5/dist-packages/jep -jar jepfaas.jar

