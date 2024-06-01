package com.example.proyecto.Provider

import com.example.proyecto.Domain.MasVendidos

class MasVendidosProvider {
    companion object {
        val masVendidoList = listOf<MasVendidos>(
            MasVendidos(
                    1,
                1,
                2,
                "AYARTE-001",
                "Árbol de la vida chico",
                "Es una pieza de cerámica que representa un árbol con flora y fauna proveedora de vida. Hecho de arcilla y pinturas no tóxicas.",
                "https://postimg.cc/Mnn4qJn4][img]https://i.postimg.cc/Mnn4qJn4/pa-1-arbol-vida-chico.jpg",
                35.00,
                "Activo"
            ),
            MasVendidos(
                2,
                1,
                2,
                "AYARTE-002",
                "Árbol de la vida",
                "Es una pieza de cerámica que representa un árbol con flora y fauna proveedora de vida. Hecho de arcilla y pinturas no tóxicas.",
                "https://postimg.cc/FYw6KCcK][img]https://i.postimg.cc/FYw6KCcK/pa-1-arbol-vida.jpg",
                55.00,
                "Activo"
            ),
            MasVendidos(
                3,
                1,
                1,
                "AYARTE-003",
                "Armonía Floral",
                "Camino de mesa con bordado de flores rojas y aplicación de crochet al borde.",
                "https://postimg.cc/rKnbBwwx][img]https://i.postimg.cc/rKnbBwwx/pa-armonia-floral.jpg",
                320.00,
                "Activo"
            ),
            MasVendidos(
                4,
                1,
                2,
                "AYARTE-004",
                "Bobo mensajero",
                "Corazón mensajero floreado.",
                "https://postimg.cc/F7s8CLNh][img]https://i.postimg.cc/F7s8CLNh/pa-bombo-masajero.jpg",
                10.00,
                "Activo"
            )
            ,
            MasVendidos(
                5,
                1,
                2,
                "AYARTE-005",
                "Bolso Chacana",
                "Bolso con bordado y decoraciones de chacana.",
                "https://postimg.cc/0rChCfgt][img]https://i.postimg.cc/0rChCfgt/pa-bolso-chacana.jpg",
                44.00,
                "Activo"
            )
        )
    }
}