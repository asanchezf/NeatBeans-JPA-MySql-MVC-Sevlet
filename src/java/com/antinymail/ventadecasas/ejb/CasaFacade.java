/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antinymail.ventadecasas.ejb;

import com.antinymail.ventadecasas.entitys.Casa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Susana
 */
@Stateless
public class CasaFacade extends AbstractFacade<Casa> {
    @PersistenceContext(unitName = "VentadeCasasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CasaFacade() {
        super(Casa.class);
    }
    
}
