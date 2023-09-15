package com.tramitarte.proyecto.repository

import com.tramitarte.proyecto.dominio.SolicitudTraduccion
import com.tramitarte.proyecto.dominio.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SolicitudTraduccionRepository: CrudRepository<SolicitudTraduccion, Long> {

    fun findByTraductor(traductor: Usuario?): List<SolicitudTraduccion?>

    fun deleteBySolicitante(solicitante: Usuario?)

    fun findBySolicitante(solicitante: Usuario?): SolicitudTraduccion?

    fun findBySolicitanteAndTraductor(solicitante: Usuario?, traductor: Usuario?): List<SolicitudTraduccion?>
}