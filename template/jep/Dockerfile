FROM openjdk:8

RUN apt-get update \
    && apt-get install -y python3-pip curl \
    && pip3 install jep \
    && mkdir -p /lib \
    && pip3 show jep | grep Location \
    && curl -sSL https://github.com/openfaas-incubator/of-watchdog/releases/download/0.2.5/of-watchdog > /usr/bin/fwatchdog \
    && chmod +x /usr/bin/fwatchdog \
    && apt-get remove -y curl

ENV LD_PRELOAD /usr/lib/x86_64-linux-gnu/libpython3.5m.so.1.0

RUN pip3 install numpy pymc3 six urllib3 pandas requests urllib3 six lxml JPype1==0.6.2 jep baostock bs4 tushare numpy
WORKDIR /data

COPY jepfaas.jar /data/jepfaas.jar
COPY function/handler.py /data/handler.py

ENV upstream_url="http://127.0.0.1:8081"
ENV mode="http"
ENV fprocess="java -Djava.library.path='/usr/local/lib/python3.5/dist-packages/jep' -jar jepfaas.jar"

HEALTHCHECK --interval=2s CMD [ -e /tmp/.lock ] || exit 1

CMD ["fwatchdog"]
