package com.nledu.cloud.server.service.impl;

import net.github.fastdev.boot.handle.DictionaryService;
import org.bouncycastle.jcajce.provider.symmetric.CAST5;
import org.springframework.stereotype.Component;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月02日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Component
public class DictionServiceImpl implements DictionaryService {
    @Override
    public String getDicStr(String typeCode, String code) {
        switch (code){
            case "1":return "字典1";
            case "2":return "字典2";
            case "3": return "字典3";
        }
        return "测试字典";
    }
}
