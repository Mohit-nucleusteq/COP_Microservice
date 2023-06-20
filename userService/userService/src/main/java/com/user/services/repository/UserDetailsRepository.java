package com.user.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.user.services.dbo.UserEntity;
import com.user.services.model.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = " SELECT new com.user.services.model.UserDetails(ue.userId, ue.userName, ue.email, ur.userRole) "
            + " from UserEntity ue "
            + " LEFT JOIN UserRoles ur on ur.userId = ue.userId "
            + " WHERE ue.email = ?1")
    UserDetails getUserDetailsByEmail(String email);
}
