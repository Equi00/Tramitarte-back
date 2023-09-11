package com.tramitarte.proyecto.service

import com.tramitarte.proyecto.dominio.Documentacion
import com.tramitarte.proyecto.repository.DocumentacionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.print.Doc

@Service
class DocumentacionService {

    @Autowired
    lateinit var documentacionRepository: DocumentacionRepository

    fun guardar(documentacion: Documentacion): Documentacion {
        return documentacionRepository.save(documentacion)
    }

    fun modificar(id: Long, doc: String) {
        var documentacion = documentacionRepository.findById(id)
        documentacion = doc as Optional<Documentacion>
        documentacionRepository.save(documentacion.get())
    }
}