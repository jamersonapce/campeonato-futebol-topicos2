package com.jamerson.campeonatofutebol.repository.campeonato;

import com.jamerson.campeonatofutebol.model.Campeonato;
import com.jamerson.campeonatofutebol.repository.filter.CampeonatoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CampeonatoRepositoryImpl implements CampeonatoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Campeonato> filtrar(CampeonatoFilter filter) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Campeonato> criteriaQuery = criteriaBuilder.createQuery(Campeonato.class);
        Root<Campeonato> campeonatoRoot = criteriaQuery.from(Campeonato.class);
        Predicate[] restricoes = this.criaRestricoes(filter, criteriaBuilder, campeonatoRoot);
        criteriaQuery.select(campeonatoRoot)
                .where(restricoes)
                .orderBy( criteriaBuilder.asc(campeonatoRoot.get("nome")) );
        return manager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Page<Campeonato> filtrar(CampeonatoFilter filter, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Campeonato> criteriaQuery = criteriaBuilder.createQuery(Campeonato.class);
        Root<Campeonato> campeonatoRoot = criteriaQuery.from(Campeonato.class);
        Predicate[] restricoes = this.criaRestricoes(filter, criteriaBuilder, campeonatoRoot);
        TypedQuery<Campeonato> query = manager.createQuery(criteriaQuery);
        controleDePaginacao(query, pageable);
        return new PageImpl<>( query.getResultList(), pageable, total(filter) );
    }

    private void controleDePaginacao(TypedQuery<Campeonato> query, Pageable pageable){
        Integer paginaAtual = pageable.getPageNumber();
        Integer totalObjetosPorPagina = pageable.getPageSize();
        Integer primeiroObjetoDaPagina = paginaAtual * totalObjetosPorPagina;
        query.setFirstResult(primeiroObjetoDaPagina );
        query.setMaxResults(totalObjetosPorPagina );
    }

    private Predicate[] criaRestricoes(CampeonatoFilter filter, CriteriaBuilder criteriaBuilder, Root<Campeonato> campeonatoRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if ( !StringUtils.isEmpty(filter.getNome()) ){
            predicates.add(criteriaBuilder.like(campeonatoRoot.get("nome"),
                    "%" + filter.getNome() + "%" ));
        }
        if (filter.getAno() != null) {
            predicates.add(criteriaBuilder.equal(campeonatoRoot.get("ano"), filter.getAno()));
        }
        return predicates.toArray(new Predicate[ predicates.size() ] );
    }

    private Long total(CampeonatoFilter filter) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Campeonato> rootProduto = criteriaQuery.from(Campeonato.class);
        Predicate[] predicates = criaRestricoes(filter, criteriaBuilder, rootProduto);
        criteriaQuery.where(predicates );
        criteriaQuery.select( criteriaBuilder.count(rootProduto) );
        return manager.createQuery(criteriaQuery).getSingleResult();
    }
}
