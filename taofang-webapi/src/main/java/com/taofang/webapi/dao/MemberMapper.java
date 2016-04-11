package com.taofang.webapi.dao;

import com.taofang.webapi.model.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MemberMapper {

    @Select({
            "select MemberName, Password",
            "from member",
            "where MemberName = #{memberName,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    List<Member> selectByMemberName(@Param("memberName") String memberName);

    @Insert({
            "insert into member(MemberName, Password, RegisterDate, LastUpdateDate)",
            "values(",
            "#{membername, jdbcType=VARCHAR}, #{password, jdbcType=VARCHAR},",
            "now(), now())"
    })
    int insertMember(Member member);
}