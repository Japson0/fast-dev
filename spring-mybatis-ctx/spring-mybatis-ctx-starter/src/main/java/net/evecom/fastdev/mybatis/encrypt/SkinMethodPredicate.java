package net.evecom.fastdev.mybatis.encrypt;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2024年08月16日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class SkinMethodPredicate implements Predicate<String> {


    private Set<String> skinMethSet;


    @EventListener
    protected void init(ContextRefreshedEvent event){
        SqlSessionFactory sqlSessionFactory = event.getApplicationContext().getBean(SqlSessionFactory.class);
        Collection<Class<?>> mapperInterfaces = sqlSessionFactory.getConfiguration().getMapperRegistry().getMappers();
        Set<String> temp=new HashSet<>();
        for (Class<?> mapperInterface : mapperInterfaces) {
            ReflectionUtils.doWithMethods(mapperInterface, method -> {
                if (method.isAnnotationPresent(SkinEncrypt.class)) {
                    String mappedStatementId = mapperInterface.getName() + "." + method.getName();
                    MappedStatement mappedStatement = sqlSessionFactory.getConfiguration().getMappedStatement(mappedStatementId);
                    temp.add(mappedStatement.getId());
                }
            });
        }
        skinMethSet = new HashSet<>(temp); //这是忽略加密的方法
    }

    @Override
    public boolean test(String s) {
        return skinMethSet.contains(s);
    }
}
