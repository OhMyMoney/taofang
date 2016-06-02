package com.taofang.webapi.dao;

import com.taofang.webapi.model.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MemberMapper {

    @Select({
            "select MemberID, MemberName, Password",
            "from member",
            "where MemberName = '${memberName}'"
    })
    @ResultMap("BaseResultMap")
    List<Member> selectByMemberName(@Param("memberName") String memberName);

    @Insert({
            "insert into member(MemberName, Password, RegisterDate, LastUpdateDate)",
            "values(",
            "'${membername}', '${password}',",
            "getdate(), getdate())"
    })
    int insertMember(Member member);

    @Insert({
            "insert into member",
            "(MemberName, Password, Sex, NickName, Icon, RegisterDate, LastUpdateDate)",
            "values(",
            "'${membername}', '${password}', '${sex}', '${nickname}', '${icon}', ",
            "getdate(), getdate())"
    })
    int insertWXMember(Member member);

    @Update({
            "update member",
            "set NickName = '${nickname}',",
            "Icon = '${icon}',",
            "LastUpdateDate = getdate()",
            "where MemberName = '${membername}'",
    })
    int updateWXMember(Member member);

    @Select({
            "select MemberId, MemberName, icon, NickName",
            "from member",
            "where MemberId = ${memberId}"
    })
    @ResultMap("BaseResultMap")
    List<Member> selectByMemberId(@Param("memberId") int memberId);
}