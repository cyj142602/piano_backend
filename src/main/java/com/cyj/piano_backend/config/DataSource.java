package com.cyj.piano_backend.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @project  cloudplatform
 * @package com.css.dj.yw.config
 * @file OracleDataSource.java 创建时间:2019年6月19日下午5:19:11
 * @title 标题（要求能简洁地表达出类的功能和职责）
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @copyright Copyright (c) 2019 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 * @module 模块: 模块名称
 * @author   史雪涛
 * @reviewer 审核人
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.cyj.piano_backend.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSource {

    /**
     * @name 向SessionFactory注入数据源
     * @description 向SessionFactory注入数据源
     * @time 创建时间:2018年12月16日上午9:51:19
     * @param dataSource dataSource
     * @return SqlSessionFactory
     * @throws Exception
     * @history 修订历史（历次修订内容、修订人、修订时间等）
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("primaryDataSource") DruidDataSource dataSource) throws Exception {
        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        dataSource.setUsername(dataSource.getUsername());
        dataSource.setPassword(dataSource.getPassword());
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**/*Mapper.xml"));
        final org.apache.ibatis.session.Configuration ibatisConfiguration = new org.apache.ibatis.session.Configuration();
        ibatisConfiguration.setMapUnderscoreToCamelCase(true);
        ibatisConfiguration.setCallSettersOnNulls(true);
        bean.setConfiguration(ibatisConfiguration);
        return bean.getObject();
    }

    /**
     * @name 配置声明事务管理器
     * @description 配置声明事务管理器
     * @time 创建时间:2018年12月16日上午9:53:17
     * @param dataSource dataSource
     * @return PlatformTransactionManager
     * @history 修订历史（历次修订内容、修订人、修订时间等）
     */
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("primaryDataSource") javax.sql.DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * @name 创建SqlSessionTemplate(MyBatis-Spring的核心)
     * @description 创建SqlSessionTemplate(MyBatis-Spring的核心)
     * @time 创建时间:2018年12月16日上午9:54:13
     * @param sqlSessionFactory sqlSessionFactory
     * @return SqlSessionTemplate
     * @throws Exception
     * @history 修订历史（历次修订内容、修订人、修订时间等）
     */
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
