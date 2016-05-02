package com.taofang.webapi.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-02
 */
public class VCode {
    public static List<String> VCODENAMELIST = new ArrayList<>();
    static {
        VCODENAMELIST = Arrays.asList("sgpu", "uf9v", "gjve", "9bye", "vclf", "rsdf", "pwh4", "vbsv",
                "4tbs", "sl9k", "jbzx", "7l94", "jble", "5epf", "f8zp", "3cbz", "vtwl", "l7g3", "tjqq",
                "5d2y", "ps2t", "pytb", "33pp", "q2ke", "fzpa", "gtdf", "ljly", "aj7s", "2k62", "bfrz",
                "d89p", "u5a2", "j6wc", "hvy3", "lsju", "9zpe", "qcqt", "3zd6", "ze85", "ahv9", "ydgz",
                "apze", "vu55", "glsf", "wwhr", "6eqj", "ue5e", "vauc", "36qa", "c56h","c3lx", "gyd2",
                "5v4g", "bb6x", "ul3z", "ehrb", "3hra", "txwl", "4hf8", "qtqu", "ly6x", "ee9d", "x7v3",
                "d7ts", "p25u", "tv8f", "dc29", "v3ca", "ztw6", "5fkz", "uegc", "hdrq", "t9u7", "x4gd",
                "9ajw", "ubqz", "xyf7", "bg8s", "ace3", "tf8v", "f6bg", "ytcl", "3yjs", "35qj", "h3de",
                "e2dq", "g3wg", "bzyf", "tk2a", "3jq3", "5gda", "9r4v", "rur4", "ylba", "w9h9", "bl97",
                "lp3b", "u7xv", "kdxt", "jcac");
    }

    public static String getVCodeName(){
        int index = ((int)(Math.random()*10000)) % VCODENAMELIST.size();
        return VCODENAMELIST.get(index);
    }
}
