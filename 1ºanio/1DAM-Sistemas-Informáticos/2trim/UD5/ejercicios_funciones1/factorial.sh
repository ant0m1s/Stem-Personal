factorial() {
    validar_argumentos 1 $@ || return 1
    if [$1 -lt 0]; then
        echo "No se puede factorial un negativo"
        return 1
    fi
    if [$1 -eq 1 ] || [$1 -eq 0 ]; then
        echo 1
        return 0
    fi
    resultado=1
    for ((i= 2; i<= $1; i++)); do
        resultado=$(($resultado * i))
    done

    echo $resultado
}