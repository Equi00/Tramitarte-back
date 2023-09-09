package com.tramitarte.proyecto.repository

import com.tramitarte.proyecto.dominio.SolicitudTraduccion
import com.tramitarte.proyecto.dominio.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SolicitudTraduccionRepository: JpaRepository<SolicitudTraduccion, Long> {

    fun findByTraductor(traductor: Usuario?): List<SolicitudTraduccion?>

    fun findBySolicitante(solicitante: Usuario?): List<SolicitudTraduccion?>
}