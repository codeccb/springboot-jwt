package cn.ccb;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.ccb.dao")
public class JwtSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtSpringbootApplication.class, args);
    }

}
