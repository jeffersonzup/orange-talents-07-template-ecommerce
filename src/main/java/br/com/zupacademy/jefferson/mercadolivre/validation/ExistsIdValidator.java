package br.com.zupacademy.jefferson.mercadolivre.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

    private String attribute;
    private Class<?> klass;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistsId constraintAnnotation) {
        attribute = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        Query query = manager.createQuery("Select 1 from " + klass.getName() + " where " + attribute + "=:value");
        query.setParameter("value", obj);
        List<?> list = query.getResultList();
        return !list.isEmpty() || obj == null;
    }
}
