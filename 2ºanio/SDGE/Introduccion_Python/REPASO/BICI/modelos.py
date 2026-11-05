"""UD1 · PT1 · Clases del sistema BiciAlerta.

No escribas print() ni input() en las clases ni en sus métodos.
"""

import math

class Bicicleta:
    def __init__(self, codigo, propietario, modelo, valor, estado="ROBADA"):
        if not isinstance(codigo, str) or not codigo.strip():
            raise ValueError("El código no puede estar vacío")
        if not isinstance(propietario, str) or not propietario.strip():
            raise ValueError("El propietario no puede estar vacío")
        if not isinstance(modelo, str) or not modelo.strip():
            raise ValueError("El modelo no puede estar vacío")

        try:
            valor = float(valor)
        except (TypeError, ValueError):
            raise ValueError("El valor debe ser numérico") from None

        if not math.isfinite(valor) or valor <= 0:
            raise ValueError("El valor debe ser mayor que cero y finito")
        if not isinstance(estado, str) or estado.strip().upper() not in ("ROBADA", "RECUPERADA"):
            raise ValueError("Estado no válido")

        self.codigo = codigo.strip().upper()
        self.propietario = propietario.strip()
        self.modelo = modelo.strip()
        self.valor = valor
        self.estado = estado.strip().upper()


class RegistroRobos:
    def __init__(self):
        # El código de la bicicleta será la clave del diccionario.
        self.bicicletas = {}

    def registrar(self, bicicleta):
        """Devuelve True si se añade; False si el código está repetido."""
        if bicicleta.codigo in self.bicicletas:
            return False
        self.bicicletas[bicicleta.codigo] = bicicleta
        return True

    def listar(self):
        """Devuelve la lista completa de objetos Bicicleta."""

        return list(self.bicicletas.values())

    def recuperar(self, codigo):
        """Cambia ROBADA a RECUPERADA. Devuelve True si cambia algo."""
        # TODO: no modificar un código inexistente ni ya recuperado.
        codigo = codigo.strip().upper()
        if codigo not in self.bicicletas:
            return False
        bici = self.bicicletas[codigo]
        if bici.estado != "ROBADA":
            return False
        bici.estado = "RECUPERADA"
        return True

    def buscar(self, texto):
        """Busca por fragmento de propietario o modelo, sin importar mayúsculas."""
        # TODO
        texto = texto.strip().lower()
        resultados = []
        if not texto:
            return resultados
        
        for b in self.bicicletas.values():
            if texto in b.propietario.lower() or texto in b.modelo.lower():
                resultados.append(b)
        return resultados

    def resumen(self):
        """Devuelve un diccionario con total, robadas, recuperadas y valor_pendiente."""
        # TODO: sustituir los valores iniciales por el cálculo real.
        robadas = 0
        recuperadas = 0
        valor_pendiente = 0.0
        for b in self.bicicletas.values():
            if b.estado == "ROBADA":
                robadas += 1
                valor_pendiente += b.valor
            elif b.estado == "RECUPERADA":
                recuperadas += 1
        return {
            "total": len(self.bicicletas),
            "robadas": robadas,
            "recuperadas": recuperadas,
            "valor_pendiente": valor_pendiente,
        }
