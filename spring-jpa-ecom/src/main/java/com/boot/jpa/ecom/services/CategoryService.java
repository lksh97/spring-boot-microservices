package com.boot.jpa.ecom.services;

import com.boot.jpa.ecom.entities.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {


    private EntityManager entityManager;




    public CategoryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Category> getAllCategories() {

        // code to fetch category
        // use criteria api to get all category data

        // getting criteria builder

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // getting criteria query :
        // main query ko represent karta hai
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
        // selection clause
        // root entity

        Root<Category> root = criteriaQuery.from(Category.class);


        //select * from categories where title = value order by
        //predicate :
        Predicate predicate1 = criteriaBuilder.equal(root.get("title"), "mobile phones");

        Predicate predicate2 = criteriaBuilder.equal(root.get("id"), 2);

        Predicate orPredicate3 = criteriaBuilder.or(predicate1, predicate2);
        // predicate2
        //predicate3

        Order titleOrder = criteriaBuilder.desc(root.get("title"));


        //////START
        criteriaQuery.
                select(root)
                .where(orPredicate3)
                .orderBy(titleOrder)

        ;

        // optional filtering


        ///END

        //build and execute query
        List<Category> resultList = entityManager.createQuery(criteriaQuery).getResultList();


        return resultList;
    }

}
