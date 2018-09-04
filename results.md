# v1; 2c8g; 8req/s
```
echo "POST http://192.168.99.100:31112/function/hello-jep" | vegeta attack -rate=8 -duration=240s | vegeta report
Requests      [total, rate]            1920, 8.00
Duration      [total, attack, wait]    3m59.927115548s, 3m59.875235509s, 51.880039ms
Latencies     [mean, 50, 95, 99, max]  681.097878ms, 51.344496ms, 4.355105357s, 10.298005948s, 12.478211004s
Bytes In      [total, mean]            34637, 18.04
Bytes Out     [total, mean]            0, 0.00
Success       [ratio]                  94.95%
Status Codes  [code:count]             200:1823  500:40  502:57
Error Set:
500 Internal Server Error
502 Bad Gateway
```
# v1; 3c8g; 8req/s
```
➜  ~ echo "POST http://192.168.99.100:31112/function/hello-jep" | vegeta attack -rate=8 -duration=240s | vegeta report
Requests      [total, rate]            1920, 8.00
Duration      [total, attack, wait]    3m59.922842024s, 3m59.875236642s, 47.605382ms
Latencies     [mean, 50, 95, 99, max]  461.545026ms, 47.625438ms, 2.593910055s, 10.022488965s, 11.218074617s
Bytes In      [total, mean]            35682, 18.58
Bytes Out     [total, mean]            0, 0.00
Success       [ratio]                  97.81%
Status Codes  [code:count]             200:1878  500:5  502:37
Error Set:
500 Internal Server Error
502 Bad Gateway
```
# v1; 3c8g; 15req/s
```
echo "POST http://192.168.99.100:31112/function/hello-jep" | vegeta attack -rate=15 -duration=240s | vegeta report
Requests      [total, rate]            3600, 15.00
Duration      [total, attack, wait]    4m9.938396689s, 3m59.933386906s, 10.005009783s
Latencies     [mean, 50, 95, 99, max]  5.324642764s, 6.356928787s, 10.127715027s, 11.051593498s, 15.524362946s
Bytes In      [total, mean]            33516, 9.31
Bytes Out     [total, mean]            0, 0.00
Success       [ratio]                  49.00%
Status Codes  [code:count]             502:1795  200:1764  500:41  
Error Set:
500 Internal Server Error
502 Bad Gateway
```
# v2; 3c8g; 8req/s
```
echo "POST http://192.168.99.100:31112/function/hello-jep" | vegeta attack -rate=8 -duration=240s | vegeta report
Requests      [total, rate]            1920, 8.00
Duration      [total, attack, wait]    3m59.918991419s, 3m59.874930257s, 44.061162ms
Latencies     [mean, 50, 95, 99, max]  270.437686ms, 50.530437ms, 1.522591056s, 4.790359399s, 8.313771789s
Bytes In      [total, mean]            36233, 18.87
Bytes Out     [total, mean]            0, 0.00
Success       [ratio]                  99.32%
Status Codes  [code:count]             200:1907  502:13  
Error Set:
502 Bad Gateway
```