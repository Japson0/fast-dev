package com.nlecloud.spring.scaffold.service;

import net.github.fastdev.boot.handle.DicCacheService;
import net.github.fastdev.boot.handle.ResourceClean;
import org.springframework.boot.ApplicationArguments;

import java.util.Collections;
import java.util.Map;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class DictServiceProxy extends DicCacheService implements ResourceClean {

    private ThreadLocal<Map<String,String>> dicInfo = new ThreadLocal<>();

    public Map<String,String> getDicMap(String type){

        return Collections.EMPTY_MAP;
    }

    @Override
    protected Map<String, String> getDicDetail(String typeCode) {

        return Collections.emptyMap();
    }

    @Override
    public void clean() {
        dicInfo.remove();
    }
}
