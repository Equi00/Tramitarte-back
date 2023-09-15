package com.tramitarte.proyecto.repository

import com.tramitarte.proyecto.dominio.Etapa
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface EtapaRepository: CrudRepository<Etapa, Long> {
}