package com.santacarolina.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santacarolina.financeiro.entity.CategoriaEntity;
import com.santacarolina.financeiro.entity.UserEntity;

/**
 * CategoriaRepository
 */
@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    
    @Query("SELECT categoria FROM CategoriaEntity categoria WHERE categoria.user = :user ORDER BY categoria.numeroCategoria")
    List<CategoriaEntity> findbyUser(UserEntity user);

    Optional<CategoriaEntity> findByNomeAndUser(String nome, UserEntity user);
    Optional<CategoriaEntity> findByNumeroCategoriaAndUser(Long numero, UserEntity user);

}
