package com.taofang.webapi.dao;

import com.taofang.webapi.model.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MemberMapper {

    @Select({
            "select MemberID, MemberName, Password",
            "from member",
            "where MemberName = ${memberName}"
    })
    @ResultMap("BaseResultMap")
    List<Member> selectByMemberName(@Param("memberName") String memberName);

    @Select({
            "select MemberID",
            "from member",
            "order by MemberId desc limit 1"
    })
    Integer selectLastMemberId();

    @Insert({
            "insert into member(MemberId, MemberName, Password, RegisterDate, LastUpdateDate)",
            "values(",
            "${memberid}, ${membername}, ${password},",
            "now(), now())"
    })
    int insertMember(Member member);

    @Select({
            "select MemberId, MemberName, icon",
            "from member",
            "where MemberId = ${memberId}"
    })
    @ResultMap("BaseResultMap")
    List<Member> selectByMemberId(@Param("memberId") int memberId);
}