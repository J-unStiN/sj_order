package org.junstin.sjorders.member.repository;


import jakarta.persistence.Inheritance;
import org.junstin.sjorders.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
