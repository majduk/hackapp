package com.google.hackathon.hackapp.data;

import com.google.hackathon.hackapp.model.Transaction;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@RequestScoped
public class TransactionListProducer {
    @Inject
    private EntityManager em;

    private List<Transaction> transactions;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.,
    // Facelets or JSP view)
    @Produces
    @Named
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void onTransactionListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Transaction transaction) {
        retrieveAllTransactionsOrderedById();
    }

    @PostConstruct
    public void retrieveAllTransactionsOrderedById() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteria = cb.createQuery(Transaction.class);
        Root<Transaction> transaction = criteria.from(Transaction.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(transaction).orderBy(cb.asc(transaction.get("id")));
        transactions = em.createQuery(criteria).getResultList();
    }
}
