package com.tramitarte.proyecto.dominio

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Documentacion(nombre: String, archivoBase64: String, tipo: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(length=50)
    var nombre: String = nombre
    @Column(length=50)
    var tipo: String = tipo
    @Column(columnDefinition = "TEXT")
    var archivoBase64: String = archivoBase64
}