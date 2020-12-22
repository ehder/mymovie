package com.der.mchannel.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.der.mchannel.entity.Account;

@Transactional
@Repository
public interface UserAccountRepository extends JpaRepository<Account, Integer> {

	@Query(value = "SELECT * FROM account where user_name=:username", nativeQuery = true)
	public Account findAccountByuserName(@Param("username")String name);

	@Query(value = "SELECT * FROM account where email=:email", nativeQuery = true)
	public Account findByEmail(@Param("email")String email);
	
	@Query(value = "SELECT * FROM account where user_name=:username", nativeQuery = true)
	Optional<Account> findByUserName(@Param("username")String name);

	@Override
	void delete(Account account);
	
	
	
}
