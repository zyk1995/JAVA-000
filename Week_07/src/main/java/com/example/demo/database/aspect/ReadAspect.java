package com.example.demo.database.aspect;

import com.example.demo.database.datasource.ManagementCenter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;


/**
 * @author lw
 */
@Aspect
@Component
public class ReadAspect {

    @Autowired
    ManagementCenter managementCenter;

    @Pointcut("@annotation(com.example.demo.database.annotation.ReadAnnotation)")
    public void read(){};

    /**
     * 获取参数，改变DataSource为slave节点
     */
    @Around("read()")
    public List<Map<String, Object>> setDatabaseSource(ProceedingJoinPoint point) throws Throwable {
        System.out.println("data source change......");
        Object[] argv = point.getArgs();
        argv[0] = managementCenter.getSlaveDataSource();
        return (List<Map<String, Object>>) point.proceed(argv);
    }
}
