package org.junstin.sjorders.member.dto;

import lombok.Builder;
import org.junstin.sjorders.member.domain.Role;

@Builder
public record MemberCreateReqDto(
        String name,
        String email,
        String password,
        String address,
        Role role
) {
}
