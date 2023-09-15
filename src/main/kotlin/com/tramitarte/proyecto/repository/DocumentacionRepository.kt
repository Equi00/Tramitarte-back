package com.tramitarte.proyecto.repository

import com.tramitarte.proyecto.dominio.Documentacion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentacionRepository: CrudRepository<Documentacion, Long> {}