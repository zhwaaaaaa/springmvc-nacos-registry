# springmvc-nacos-registry
spring mvc backend registry to nacos

## 项目说明
测试 spring mvc 把自己的ip和端口注册到 nacos。 然后使用nginx 订阅nacos 执行反向代理

## 项目启动

### 本机安装启动 nacos。
本项目是使用的nacos地址是127.0.0.1
### 下载并启动 本项目
可以通过 IDE 下载启动。也可以通过maven build 启动:

`mvn install -DskipTests && cd registry-demo/target && java -jar registry-demo-1.0-SNAPSHOT.jar`
### 下载并安装 nginx-nacos-upstream
 ```git clone https://github.com/zhwaaaaaa/nginx-nacos-upstream.git ```

 ```cd ginx-nacos-upstream```

 ```./configure --add-module=modules/nacos && make```

 ```sudo make install```


修改 nginx 配置文件 ```sudo vim /usr/local/nginx/conf/nginx.conf```，在http 之前加入 nacos 配置块
```
nacos {
    server_list localhost:8848; # nacos 服务器列表，空格隔开
    udp_port 19999; #udp 端口号
    udp_ip 127.0.0.1; #udp ip 地址。
    udp_bind 0.0.0.0:19999; # 绑定udp 地址
    error_log logs/nacos.log info;
    default_group DEFAULT_GROUP; # 默认的nacos group name
}
http {
    upstream s {
        use_nacos_address data_id=springmvc-nacos-demo; # data_id 要和 spring.application.name一致
    }
    server {
        # ... other config
        location ^~ / {
            proxy_pass http://s;
        }
    }
}
```
启动 nginx. 然后访问 localhost 80 端口
```bash
curl localhost/hello  # 得到 Current server is running on 8080
```
