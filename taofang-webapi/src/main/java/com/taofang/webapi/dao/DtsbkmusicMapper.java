package com.taofang.webapi.dao;

import com.taofang.webapi.model.Dtsbkmusic;
import org.apache.ibatis.annotations.Select;

public interface DtsbkmusicMapper {

    @Select({
            "select music from dtsbkmusic",
            "order by id desc limit 1"
    })
    Dtsbkmusic selectLast();
}