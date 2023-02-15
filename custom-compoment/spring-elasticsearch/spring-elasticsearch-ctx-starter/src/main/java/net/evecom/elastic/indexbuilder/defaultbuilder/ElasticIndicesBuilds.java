/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.elastic.indexbuilder.defaultbuilder;

import net.evecom.elastic.indexbuilder.ElasticQueryIndicesBuild;
import net.evecom.elastic.pojo.EsQueryWrapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年06月03日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ElasticIndicesBuilds {

    public static ElasticQueryIndicesBuild timeBuilder(Date begin, Date end) {
        return (EsQueryWrapper obj, String alias, String[] indices) -> {
            return timeBuilder(alias, indices, begin, end);
        };
    }


    public static String[] timeBuilder(String alias, String[] indices, Date begin, Date end) {
        DateFormat dateFormat = new DateFormat(begin, end).invoke();
        String beginTimeStr = dateFormat.getBeginTimeasString();
        String endTimeStr = dateFormat.getEndTimeasString();

        String stringFormat = alias + "_" + "%s";
        if (indices.length == 0) {
            return new String[]{alias};
        }
        String _bt = String.format(stringFormat, beginTimeStr);
        String _et = String.format(stringFormat, endTimeStr);
        List<String> ans = new ArrayList<>();
        for (String index : indices) {
            if (index.compareTo(_bt) >= 0 && index.compareTo(_et) <= 0) {
                ans.add(index);
            }
        }
        return ans.toArray(new String[ans.size()]);
    }

    /**
     * 时间格式化
     */
    private static class DateFormat {
        /**
         * 开始时间
         */
        private Date begin;
        /**
         * 结束时间
         */
        private Date end;
        /**
         * 开始时间Str
         */
        private String beginTimeasString;
        /**
         * 结束时间Str
         */
        private String endTimeasString;

        public DateFormat(Date begin, Date end) {
            this.begin = begin;
            this.end = end;
        }

        public String getBeginTimeasString() {
            return beginTimeasString;
        }

        public String getEndTimeasString() {
            return endTimeasString;
        }

        public DateFormat invoke() {
            if (begin == null) {
                begin = new Date(-662716800000L);
            }
            if (end == null) {
                end = new Date();
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
            beginTimeasString = format.format(begin);
            endTimeasString = format.format(end);
            return this;
        }
    }
}
